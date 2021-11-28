package com.example.plugins

import io.ktor.auth.*
import io.ktor.util.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun Application.configureSecurity() {
    install(Authentication) {
        basic {}
    }
    routing {
        authenticate {
            get("/authenticated") {
                call.respondText("I am authenticated!")
            }
        }
    }
}
