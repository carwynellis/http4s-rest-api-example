package example.http4s.api.route

import cats.effect.IO
import example.http4s.api.model.Asteroid
import example.http4s.api.route.RouteTestHelpers.ResponseSyntax._
import example.http4s.api.route.RouteTestHelpers.runRequest
import example.http4s.api.service.AsteroidService
import org.http4s.Method.GET
import org.http4s.Request
import org.http4s.Status.{BadRequest, Ok}
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.implicits.http4sLiteralsSyntax
import org.mockito.ArgumentMatchers.{eq => matches}
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should
import org.scalatestplus.mockito.MockitoSugar

import java.time.LocalDate

class AsteroidRoutesTest extends AnyFunSuiteLike with should.Matchers with MockitoSugar  {

  private val mockAsteroidService = mock[AsteroidService]

  private val underTest = new AsteroidRoutes(mockAsteroidService)
    .routes
    .orNotFound

  test("Should return matching data for valid date range") {
    val asteroids = Seq(
      Asteroid("Foo"),
      Asteroid("Bar"),
      Asteroid("Baz"),
    )

    when(mockAsteroidService.getAsteroidsForDateRange(
      matches(LocalDate.parse("2024-01-01")),
      matches(LocalDate.parse("2024-01-02")))
    ).thenReturn(IO.pure(asteroids))

    val response = runRequest(underTest, Request[IO](
      method = GET,
      uri = uri"/asteroid?from=2024-01-01&to=2024-01-02"
    ))

    response.status shouldBe Ok
    response.fromJson[Seq[Asteroid]] shouldBe asteroids
  }

  test("Should return bad request if from and to dates missing") {
    val response = runRequest(underTest, Request[IO](
      method = GET,
      uri = uri"/asteroid"
    ))

    response.status shouldBe BadRequest
  }

  test("Should return bad request if from date missing") {
    val response = runRequest(underTest, Request[IO](
      method = GET,
      uri = uri"/asteroid?to=2024-01-01"
    ))

    response.status shouldBe BadRequest
  }

  test("Should return bad request if to date missing") {
    val response = runRequest(underTest, Request[IO](
      method = GET,
      uri = uri"/asteroid?from=2024-01-01"
    ))

    response.status shouldBe BadRequest
  }

  test("Should return bad request if from date is invalid") {
    val response = runRequest(underTest, Request[IO](
      method = GET,
      uri = uri"/asteroid?from=2024-01-81&to=2024-01-02"
    ))

    response.status shouldBe BadRequest
  }

  test("Should return bad request if to date is invalid") {
    val response = runRequest(underTest, Request[IO](
      method = GET,
      uri = uri"/asteroid?from=2024-01-01&to=2024-81-02"
    ))

    response.status shouldBe BadRequest
  }

  test("Should return bad request if to date is before from date") {
    val response = runRequest(underTest, Request[IO](
      method = GET,
      uri = uri"/asteroid?from=2024-01-01&to=2023-01-02"
    ))

    response.status shouldBe BadRequest
  }

}