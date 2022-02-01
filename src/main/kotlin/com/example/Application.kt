package com.example

import com.example.plugins.configureSecurity
import com.example.plugins.customerRouting
import com.example.plugins.extendedWebsocketServer
import com.example.plugins.simpleWebsocketServer
import io.ktor.application.*

fun main(args: Array<String>): Unit =
        io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    customerRouting()
    configureSecurity()
    simpleWebsocketServer()
    extendedWebsocketServer()
}

// Above is an alternative for below main function
// Listing 1
/*
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        routing {
            get("/") {
                call.respondText("Hello World!")
            }
        }
    }.start(wait = true)
}
*/
