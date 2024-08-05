package example.http4s.api.service

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import example.http4s.api.client.nearearthobject.NearEarthObjectClient
import example.http4s.api.client.nearearthobject.model.{NearEarthObject, NearEarthObjectResponse}
import example.http4s.api.model.Asteroid
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should
import org.scalatestplus.mockito.MockitoSugar

import java.time.LocalDate

class AsteroidServiceTest extends AnyFunSuiteLike with should.Matchers with MockitoSugar {

  private val mockClient = mock[NearEarthObjectClient]

  private val underTest = new AsteroidService(mockClient)

  test("should return a list of asteroids for a valid date range") {

    when(mockClient.get(any(), any()))
      .thenReturn(IO.pure(NearEarthObjectResponse(
        Map("2024-01-01" -> List(NearEarthObject("foo")))
      )))

    val result = underTest.getAsteroidsForDateRange(
      LocalDate.parse("2024-01-01"),
      LocalDate.parse("2024-01-01")
    ).unsafeRunSync()

    result shouldBe List(Asteroid("foo"))
  }

}
