package co.chocolatebiscuit.cartographer

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import kotlin.reflect.KClass

class DestinationGenerator(
    private val codeGenerator: CodeGenerator
) {
    fun generate(
        classDeclaration: KSClassDeclaration,
    ) {
        val packageName = classDeclaration.packageName.asString()  //1
        val factoryName = "${classDeclaration.simpleName.asString()}Destination"  //2
        /*val fragmentClass = classDeclaration.asType(emptyList()).toTypeName(TypeParameterResolver.EMPTY)  //3*/
        //TODO: code generation logic
    }
}