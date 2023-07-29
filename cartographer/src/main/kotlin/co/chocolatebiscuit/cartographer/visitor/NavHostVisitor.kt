package co.chocolatebiscuit.cartographer.visitor

import com.google.devtools.ksp.processing.CodeGenerator

class NavHostVisitor(
    private val codeGenerator: CodeGenerator,
) : KSVisitorString() {

}