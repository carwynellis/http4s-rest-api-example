package example.http4s.api.test

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import org.scalatest.{BeforeAndAfterAll, Suite}

trait WireMockSupport extends BeforeAndAfterAll { this: Suite =>

  private val wiremockServer = new WireMockServer(wireMockConfig().dynamicPort())

  protected lazy val baseUrl = s"http://localhost:${wiremockServer.port()}"

  override def beforeAll(): Unit = {
    wiremockServer.start()
    // Ensure wiremock client also points to correct port.
    WireMock.configureFor(wiremockServer.port())
  }

  override def afterAll(): Unit = wiremockServer.stop()

}