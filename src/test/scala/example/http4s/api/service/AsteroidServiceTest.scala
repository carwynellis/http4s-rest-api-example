package example.http4s.api.service

import example.http4s.api.model.Asteroid
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should

import java.time.LocalDate

class AsteroidServiceTest extends AnyFunSuiteLike with should.Matchers {

  private val underTest = new AsteroidService

  test("should return a list of asteroids for a valid date range") {
    val result = underTest.getAsteroidsForDateRange(LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-01"))
    result shouldBe List(Asteroid("foo"))
  }

}
