package com.example.samplespringsecurityredis

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.LinkedMultiValueMap
import java.net.URI
import org.springframework.http.HttpMethod

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWorldJsonControllerTest{
    @Autowired val testRestTemplate: TestRestTemplate? = null

    @LocalServerPort
    private val port: Int = 0

    @Test
    fun `未認証 成功`() {
        val uri = URI("http://localhost:$port/pub/test")
        val responseBody = testRestTemplate!!.getForObject(uri, String::class.java)
        assertThat(responseBody).isEqualTo("pub")
    }

    @Test
    fun `未認証 失敗`() {
        val uri = URI("http://localhost:$port/prv/test")
        val result = testRestTemplate!!.getForEntity(uri, Void::class.java)
        assertThat(result.statusCode).isEqualTo(HttpStatus.UNAUTHORIZED)
    }

    @Test
    fun `認証 失敗`() {
        val uri = URI("http://localhost:$port/auth/login")

        val multiPartBody = LinkedMultiValueMap<String, Any>()
        multiPartBody.add("email", "a@a.com")
        multiPartBody.add("password", "abc")

        val req = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(multiPartBody)

        val result = testRestTemplate!!.exchange(req, Void::class.java)
        assertThat(result.statusCode).isEqualTo(HttpStatus.UNAUTHORIZED)
    }

    @Test
    fun `認証 成功`() {
        val sessionId = getSessionId()

        val uri = URI("http://localhost:$port/prv/test")

        val headers = HttpHeaders()
        headers.add("Cookie", "SESSION=$sessionId")

        val req = RequestEntity(null, headers, HttpMethod.GET, uri)

        val result = testRestTemplate!!.exchange(req, String::class.java)
        assertThat(result.body).isEqualTo("username:a@a.com")
    }

    fun getSessionId(): String{
        val uri = URI("http://localhost:$port/auth/login")

        val multiPartBody = LinkedMultiValueMap<String, Any>()
        multiPartBody.add("email", "a@a.com")
        multiPartBody.add("password", "password")

        val req = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(multiPartBody)

        val result = testRestTemplate!!.exchange(req, Void::class.java)

        val cookies = result.headers["Set-Cookie"]
        val sessionId = cookies!!.filter { it.startsWith("SESSION=")}
        return sessionId.first().split("=")[1]
    }
}
