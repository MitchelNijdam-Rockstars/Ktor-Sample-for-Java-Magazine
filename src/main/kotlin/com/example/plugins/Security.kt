package com.example.plugins

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*

/**
 * Here we see basic auth in action. First we "install" the plugin, then we configure
 * it, and eventually we use it around our get endpoint.
 */
fun Application.configureSecurity() {
    install(Authentication) {
        basic("basic-auth") {
            validate { credentials ->
                // TODO: implement your own authentication mechanism
                if (credentials.name == "java" && credentials.password == "magazine") {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }

    routing {
        authenticate("basic-auth") {
            get("/authenticated") {
                call.respondText("I am authenticated!")
            }
        }
    }
}
