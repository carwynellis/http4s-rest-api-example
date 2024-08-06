package example.http4s.api.route

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import org.http4s.{EntityDecoder, HttpApp, Request, Response}

object RouteTestHelpers {

  def runRequest(app: HttpApp[IO], request: Request[IO]): Response[IO] = app.run(request).unsafeRunSync()

  object ResponseSyntax {
    implicit class ResponseOps(val response: Response[IO]) extends AnyVal {
      def fromJson[A](implicit decoder: EntityDecoder[IO, A]): A = response.as[A].unsafeRunSync()
    }
  }

}
