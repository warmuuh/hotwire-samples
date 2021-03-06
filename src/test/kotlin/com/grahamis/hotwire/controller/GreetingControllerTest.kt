package com.grahamis.hotwire.controller

import com.grahamis.elementOfId
import com.grahamis.getResponse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import java.net.URI

@ExtendWith(SpringExtension::class)
@WebFluxTest(controllers = [GreetingController::class])
class GreetingControllerTest {
    @Autowired
    lateinit var webClient: WebTestClient

    private val path = "/greeting"
    private val personElement = "person"

    @Test
    fun withNoPersonParamShouldUseDefault() {
        val default = "world"
        val response = webClient.getResponse(URI.create(path))

        response.expectBody().elementOfId(personElement).isEqualTo(default)
    }

    @Test
    fun withPersonParamShouldUseIt() {
        val person = "foo"
        val response = webClient.getResponse(URI.create("${path}?person=${person}"))

        response.expectBody().elementOfId(personElement).isEqualTo(person)
    }
}
