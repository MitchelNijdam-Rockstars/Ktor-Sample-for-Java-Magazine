package com.example

import com.example.plugins.customerRouting
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ customerRouting() }) {
            handleRequest(HttpMethod.Delete, "/customers/some-id").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Customer deleted!", response.content)
            }
        }
    }
}