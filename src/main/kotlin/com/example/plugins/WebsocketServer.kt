package com.example.plugins

import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

/**
 * Starts a websocket server and listens for new messages.
 * This is a minimal example.
 */
fun Application.simpleWebsocketServer() {
    install(WebSockets)

    routing {
        webSocket("/message") {
            send("You are connected!")
            for (frame in incoming) {
                frame as? Frame.Text ?: continue
                println("Received message: ${frame.readText()}")
                // broadcast to all the other connections
            }
        }
    }
}

/**
 * More elaborate example of a chat server that keeps a list of all connected Users.
 */
fun Application.extendedWebsocketServer() {
    routing {
        val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())
        webSocket("/chat") {
            println("Adding user!")
            val thisConnection = Connection(this)
            connections += thisConnection
            try {
                send("You are connected! There are ${connections.count()} users here.")
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    val textWithUsername = "[${thisConnection.name}]: $receivedText"
                    connections.forEach {
                        it.session.send(textWithUsername)
                    }
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                println("Removing $thisConnection!")
                connections -= thisConnection
            }
        }
    }
}

class Connection(val session: DefaultWebSocketSession) {
    companion object {
        var lastId = AtomicInteger(0)
    }

    val name = "user${lastId.getAndIncrement()}"
}