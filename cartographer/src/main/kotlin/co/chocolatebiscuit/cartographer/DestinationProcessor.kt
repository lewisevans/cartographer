package co.chocolatebiscuit.cartographer

import co.chocolatebiscuit.cartographer.visitor.DestinationVisitor
import co.chocolatebiscuit.cartographer.visitor.NavHostVisitor
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueParameter
import com.google.devtools.ksp.validate
import kotlin.reflect.KClass

const val GENERATED_PACKAGE = "co.chocolatebiscuit.cartographer"

internal class DestinationProcessor(
    private val environment: SymbolProcessorEnvironment,
) : SymbolProcessor {

    private val destinationVisitor = DestinationVisitor(environment.codeGenerator)
    private val hostVisitor = NavHostVisitor(environment.codeGenerator)

    private fun Resolver.findAnnotations(
        kClass: KClass<*>,
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString()
    ).filterIsInstance<KSFunctionDeclaration>()

    override fun process(resolver: Resolver): List<KSAnnotated> {
        processResolver(resolver)
        val listedFunctions: Sequence<KSFunctionDeclaration> =
            resolver.findAnnotations(Destination::class)
        if (!listedFunctions.iterator().hasNext()) return emptyList()

        // gathering the required imports
        val imports = listedFunctions.mapNotNull { it.qualifiedName?.asString() }.toSet()

        val sourceFiles = listedFunctions.mapNotNull { it.containingFile }

        val lists =
            listedFunctions.groupBy { it.annotations.first { it.shortName.asString() == "Destination" }.arguments.first { it.name?.asString() == "scope" }.value.toString() }

        val fileText = buildString {
            newLine()
            newLine()
            imports.forEach {
                append("import $it")
                newLine()
            }
            newLine()
            lists.forEach { (listName, functions) ->
                val functionNames =
                    functions.map { it.simpleName.asString() + "()" }.joinToString(", ")

                append("/** NavHost = '$listName' children: $functionNames*/")
                newLine()

            }
        }
        createFileWithText(sourceFiles, fileText, "GraphHelper")
        return (listedFunctions).filterNot { it.validate() }.toList()
    }

    private fun processResolver(resolver: Resolver): List<KSAnnotated> {
        var unresolvedSymbols: Sequence<KSAnnotated> = emptySequence()
        val annotationName = Destination::class.qualifiedName

        if (annotationName != null) {
            val resolved = resolver
                .getSymbolsWithAnnotation(annotationName).filterIsInstance<KSFunctionDeclaration>()
            val validatedSymbols = resolved.filter { it.validate() }
            if (validatedSymbols.iterator().hasNext()) {
                createNavigationHost(validatedSymbols)
            }
            validatedSymbols
                .filter {
                    true
                }
                .forEach {
                    it.accept(destinationVisitor, Unit)
                }     // 3
            unresolvedSymbols = resolved - validatedSymbols
        }
        return unresolvedSymbols.toList()
    }

    private fun createNavigationHost(validatedSymbols: Sequence<KSFunctionDeclaration>) {
        val sourceFiles = validatedSymbols.mapNotNull { it.containingFile }
        val text = buildString {
            append("package $GENERATED_PACKAGE\n\n")
            append(
                "import androidx.compose.foundation.layout.fillMaxSize\n" +
                        "import androidx.compose.runtime.Composable\n" +
                        "import androidx.compose.ui.Modifier\n" +
                        "import androidx.navigation.NavHostController\n" +
                        "import androidx.navigation.compose.NavHost\n" +
                        "import androidx.navigation.compose.rememberNavController"
            )
            newLine(2)
            append("/*THIS IS A generated NAV HOST - DO NOT EDIT*/\n")
            append("@Composable\n")
            append("fun $ROOT_NAV_HOST_NAME(\n")
            tabAppend("modifier: Modifier = Modifier,\n")
            tabAppend("startDestination: String = \"\",\n")
            tabAppend("navController: NavHostController = rememberNavController()\n")
            append(") {\n")
            tabAppend("NavHost(\n")
            tabAppend("navController = navController,\n", 2)
            tabAppend("modifier = modifier,\n", 2)
            tabAppend("startDestination = startDestination,\n", 2)
            tabAppend(") {\n")
            validatedSymbols.forEach {
                val navController: List<Arguments> =
                    createArguments(it.parameters).filter { arg -> isNavController(arg) }
                var navString = ""
                if (navController.isNotEmpty()) {
                    navString = "navController"
                }

                tabAppend("/*${it.parameters.map { parameter -> parameter.type }}*/\n", 2)
                tabAppend("${it}Route($navString)\n", 2)
            }
            tabAppend("}\n")
            append("}")
        }
        createFileWithText(sourceFiles, text, ROOT_NAV_HOST_NAME)
    }

    private fun createArguments(params: List<KSValueParameter>): List<Arguments> =
        params.map {
            val type = it.type.resolve()
            val name = type.declaration.simpleName.asString()
            val qualified = type.declaration.qualifiedName?.asString() ?: ""
            Arguments(it.toString(), name, qualified, type.isMarkedNullable)
        }

    private fun isNavController(it: Arguments): Boolean =
        it.className == "NavController" || it.className == "NavHostController"


    private fun createFileWithText(
        sourceFiles: Sequence<KSFile>,
        fileText: String,
        fileName: String,
    ) {
        val file = environment.codeGenerator.createNewFile(
            Dependencies(
                false,
                *sourceFiles.toList().toTypedArray(),
            ),
            GENERATED_PACKAGE,
            fileName
        )

        file.write(fileText.toByteArray())
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

const val ROOT_NAV_HOST_NAME = "AppNavHost"

data class Arguments constructor(
    val name: String,
    val className: String,
    val qualifiedName: String,
    val isNullable: Boolean,
)