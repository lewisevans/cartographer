package co.chocolatebiscuit.cartographer

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Destination(
    val scope: String = "",
)
