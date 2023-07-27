package com.example.core.common.network

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val tiflyDispatcher: TiflyDispatchers)

enum class TiflyDispatchers {
    Default,
    IO,
}