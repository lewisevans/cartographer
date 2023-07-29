package co.chocolatebiscuit.cartographer.visitor

import co.chocolatebiscuit.cartographer.Arguments
import co.chocolatebiscuit.cartographer.DestinationGenerator
import co.chocolatebiscuit.cartographer.GENERATED_PACKAGE
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueParameter
import com.google.devtools.ksp.symbol.KSVisitorVoid

class DestinationVisitor(
    private val codeGenerator: CodeGenerator,
) : KSVisitorVoid() {

    private val destinationGenerator: DestinationGenerator = DestinationGenerator(codeGenerator)

    /*override fun visitClassDeclaration(
        classDeclaration: KSClassDeclaration,
        data: Unit,
    ) {
        val arguments = classDeclaration.annotations.iterator().next().arguments
        val packageName = classDeclaration.packageName.asString()
        val destinationName = "${classDeclaration.simpleName.asString()}Destination"
        destinationGenerator.generate(
            classDeclaration
        )
    }*/

    override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
        val sourceFiles = sequenceOf(function.containingFile!!)
        val import = function.qualifiedName?.asString()
        val text = buildString {
            append("package $GENERATED_PACKAGE\n\n")
            append("import $import\n")
            append("import androidx.navigation.NavController\n")
            append("import androidx.navigation.NavGraphBuilder\n")
            append("import androidx.navigation.NavType\n")
            append("import androidx.navigation.compose.composable\n")
            append("import androidx.navigation.navArgument")
            newLine(2)
            append(createRoute(function))
            newLine()
            append(createNavigateTo(function))
        }
        createFileWithText(sourceFiles, text, "${function}Mediator")
    }

    private fun createRoute(it: KSFunctionDeclaration): String {
        val (navController, arguments) =
            createArguments(it.parameters).partition { isNavController(it) }

        var routeArgs = ""
        var argsList = ""
        var backArgs = ""

        if (arguments.isNotEmpty()) {
            routeArgs = buildRouteArguments(arguments)
            argsList = buildArgumentsList(arguments)
            backArgs = buildBackstackArguments(arguments)
        }

        var navControllerString = ""
        if (navController.isNotEmpty()) {
            navControllerString = "navController: NavController"
        }

        return buildString {
            append("/*")
            append(arguments)
            append("*/\n")
            append("fun NavGraphBuilder.${it}Route($navControllerString) {\n")
            tabAppend("composable(\n")
            tabAppend("route = \"${it}${routeArgs}\",\n", 2)
            if (arguments.isNotEmpty()) append(argsList)
            tabAppend(") {")
            if (arguments.isNotEmpty()) append(" backStackEntry ->\n") else newLine()
            if (arguments.isNotEmpty()) append(backArgs)
            val navArgs = it.parameters.joinToString(",")
            tabAppend("${it}($navArgs)\n", 2)
            tabAppend("}\n")
            append("}\n")
        }
    }

    private fun createNavigateTo(it: KSFunctionDeclaration): Any {
        val arguments: List<Arguments> =
            createArguments(it.parameters).filter { !isNavController(it) }
        return buildString {
            val navArgs = buildNavToArgs(arguments)
            val navParams = buildNavToParams(arguments)
            append("fun NavController.navigateTo$it($navArgs) {\n")
            tabAppend("navigate(\"${it}$navParams\")\n")
            append("}")
        }
    }

    private fun buildNavToArgs(params: List<Arguments>): String =
        buildString {
            params.forEach {
                val nullChar = if (it.isNullable) "?" else ""
                append("${it.name}: ${it.className}$nullChar,")
            }
        }

    private fun buildNavToParams(params: List<Arguments>): String =
        buildString {
            if (params.isNotEmpty()) append("?")
            params.forEachIndexed { index, item ->
                if (index > 0) append(",")
                append("${item.name}=$${item.name}")
            }
        }

    private fun isNavController(it: Arguments): Boolean =
        it.className == "NavController" || it.className == "NavHostController"

    private fun buildRouteArguments(params: List<Arguments>): String =
        buildString {
            append("?")
            params.forEachIndexed { index, param ->
                if (index > 0) append(",")
                append("${param.name}={${param.name}}")
            }
        }

    private fun buildArgumentsList(params: List<Arguments>): String =
        buildString {
            tabAppend("arguments = listOf(\n", 2)
            params.forEach {
                if (isNavController(it)) return@forEach

                val type = "${it.className}Type"

                tabAppend("navArgument(\"${it.name}\") {\n", 3)
                tabAppend("type = NavType.$type\n", 4)
                tabAppend("},\n", 3)
            }
            tabAppend(")\n", 2)
        }

    private fun buildBackstackArguments(params: List<Arguments>): String =
        buildString {
            params.forEach {
                if (isNavController(it)) return@forEach
                val default = getDefault(it.className)
                tabAppend(
                    "val ${it.name} = backStackEntry.arguments?.get${it.className}(\"${it.name}\") ?: $default\n",
                    2
                )
            }
        }

    private fun getDefault(className: String): Any {
        return when (className) {
            "Int" -> {
                "Int.MIN_VALUE"
            }

            "Bool" -> {
                "false"
            }

            "String" -> {
                """"""""
            }

            "Long" -> {
                "Long.MIN_VALUE"
            }

            else -> "StringType"
        }
    }

    private fun createFileWithText(
        sourceFiles: Sequence<KSFile>,
        fileText: String,
        fileName: String,
    ) {
        val file = codeGenerator.createNewFile(
            Dependencies(
                false,
                *sourceFiles.toList().toTypedArray(),
            ),
            GENERATED_PACKAGE,
            fileName
        )

        file.write(fileText.toByteArray())
    }

    private fun createArguments(params: List<KSValueParameter>): List<Arguments> =
        params.map {
            val type = it.type.resolve()
            val name = type.declaration.simpleName.asString()
            val qualified = type.declaration.qualifiedName?.asString() ?: ""
            Arguments(it.toString(), name, qualified, type.isMarkedNullable)
        }


    private fun StringBuilder.newLine(count: Int = 1) {
        repeat(count) {
            append("\n")
        }
    }

    private fun StringBuilder.tabAppend(
        append: String,
        count: Int = 1,
    ) {
        repeat(count) {
            append("    ")
        }
        append(append)
    }
}

