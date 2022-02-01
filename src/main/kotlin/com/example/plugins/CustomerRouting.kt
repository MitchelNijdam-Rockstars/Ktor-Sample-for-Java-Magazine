package com.example.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * Below function extends the `Application` class, which means it has
 * access to the `routing` function.
 *
 * This is the example of Listing 3
 */
fun Application.customerRouting() {
    install(ContentNegotiation) {
        jackson()
    }

    val customerRepository = CustomerRepository()

    routing {
        route("/customers") {
            get {
                call.respond(
                    listOf(
                        Customer("example_name", 25),
                        Customer("another", 44)
                    )
                )
            }

            get("/{id}") {
                call.respond(Customer("Customer ${call.parameters["id"]}", Random.nextInt(0, 123)))
            }

            post {
                val customer = call.receive<Customer>()
                customerRepository.add(customer)
                call.respondText(
                    "Customer stored correctly",
                    status = HttpStatusCode.Created
                )
            }

            delete("/{id}") {
                println("Deleting customer ${call.parameters["id"]}")
                call.respondText("Customer deleted!")
            }
        }
    }
}

data class Customer(val name: String, val age: Int)

class CustomerRepository {
    suspend fun add(customer: Customer) {
        println("Saving customer to database (mock): $customer")
        delay(timeMillis = 1_000)
    }
}
