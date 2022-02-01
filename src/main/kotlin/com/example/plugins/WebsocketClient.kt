package com.example.plugins

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Use below code in another project where you want to send messages to the websocket server.
 * This is added as example. Reads messages from console input and sends it to the /chat endpoint.
 *
 * See for working example: https://github.com/ktorio/ktor-websockets-chat-sample/tree/final
 */
private fun startWebsocketClient() {
    println("Starting websocket client")
    val client = HttpClient {
        install(WebSockets)
    }
    runBlocking {
        client.webSocket(path = "/chat") {
            val messageOutputRoutine = launch {
                for (message in incoming) {
                    message as? Frame.Text ?: continue
                    println("New message: ${message.readText()}")
                }
            }
            val userInputRoutine = launch {
                while (true) {
                    val message = readLine() ?: ""
                    send(message)
                }
            }

            userInputRoutine.join()
            messageOutputRoutine.cancelAndJoin()
        }
    }
    client.close()
}