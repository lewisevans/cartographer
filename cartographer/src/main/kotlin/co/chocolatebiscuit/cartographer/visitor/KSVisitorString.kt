package co.chocolatebiscuit.cartographer.visitor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSCallableReference
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSClassifierReference
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSDeclarationContainer
import com.google.devtools.ksp.symbol.KSDefNonNullReference
import com.google.devtools.ksp.symbol.KSDynamicReference
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSModifierListOwner
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSParenthesizedReference
import com.google.devtools.ksp.symbol.KSPropertyAccessor
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSPropertyGetter
import com.google.devtools.ksp.symbol.KSPropertySetter
import com.google.devtools.ksp.symbol.KSReferenceElement
import com.google.devtools.ksp.symbol.KSTypeAlias
import com.google.devtools.ksp.symbol.KSTypeArgument
import com.google.devtools.ksp.symbol.KSTypeParameter
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSValueArgument
import com.google.devtools.ksp.symbol.KSValueParameter
import com.google.devtools.ksp.symbol.KSVisitor

open class KSVisitorString() : KSVisitor<String, String> {
    override fun visitAnnotated(annotated: KSAnnotated, data: String): String = ""

    override fun visitAnnotation(annotation: KSAnnotation, data: String): String = ""

    override fun visitCallableReference(reference: KSCallableReference, data: String): String = ""

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: String): String = ""

    override fun visitClassifierReference(reference: KSClassifierReference, data: String): String = ""

    override fun visitDeclaration(declaration: KSDeclaration, data: String): String = ""

    override fun visitDeclarationContainer(
        declarationContainer: KSDeclarationContainer,
        data: String,
    ): String = ""

    override fun visitDefNonNullReference(reference: KSDefNonNullReference, data: String): String = ""

    override fun visitDynamicReference(reference: KSDynamicReference, data: String): String = ""

    override fun visitFile(file: KSFile, data: String): String = ""

    override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: String): String = ""

    override fun visitModifierListOwner(
        modifierListOwner: KSModifierListOwner,
        data: String,
    ): String = ""

    override fun visitNode(node: KSNode, data: String): String = ""

    override fun visitParenthesizedReference(
        reference: KSParenthesizedReference,
        data: String,
    ): String = ""

    override fun visitPropertyAccessor(accessor: KSPropertyAccessor, data: String): String = ""

    override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: String): String = ""

    override fun visitPropertyGetter(getter: KSPropertyGetter, data: String): String = ""

    override fun visitPropertySetter(setter: KSPropertySetter, data: String): String = ""

    override fun visitReferenceElement(element: KSReferenceElement, data: String): String = ""

    override fun visitTypeAlias(typeAlias: KSTypeAlias, data: String): String = ""

    override fun visitTypeArgument(typeArgument: KSTypeArgument, data: String): String = ""

    override fun visitTypeParameter(typeParameter: KSTypeParameter, data: String): String = ""

    override fun visitTypeReference(typeReference: KSTypeReference, data: String): String = ""

    override fun visitValueArgument(valueArgument: KSValueArgument, data: String): String = ""

    override fun visitValueParameter(valueParameter: KSValueParameter, data: String): String = ""

}