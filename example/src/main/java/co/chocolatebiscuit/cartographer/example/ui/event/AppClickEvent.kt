package co.chocolatebiscuit.cartographer.example.ui.event

sealed class AppClickEvent {
    object Red: AppClickEvent()
    object Green: AppClickEvent()
    object Blue: AppClickEvent()
    object Menu: AppClickEvent()
}
