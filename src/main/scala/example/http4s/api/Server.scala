package example.http4s.api

import cats.effect.{ExitCode, IO, IOApp}
import com.comcast.ip4s.IpLiteralSyntax
import example.http4s.api.route.AsteroidRoutes
import example.http4s.api.service.AsteroidService
import org.http4s.ember.server.EmberServerBuilder

object Server extends IOApp {

  private val service = new AsteroidRoutes(new AsteroidService())
    .routes
    .orNotFound

  def run(args: List[String]): IO[ExitCode] =
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(service)
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)

}
