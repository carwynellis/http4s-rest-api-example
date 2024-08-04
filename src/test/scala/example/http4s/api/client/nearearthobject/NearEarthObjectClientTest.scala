package example.http4s.api.client.nearearthobject

import example.http4s.api.client.nearearthobject.model.NearEarthObjectResponse
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should

import java.time.LocalDate

class NearEarthObjectClientTest extends AnyFunSuiteLike with should.Matchers {

  private val underTest = new NearEarthObjectClient()

  test("should return near earth objects response for a valid request") {
    val fromDate = LocalDate.parse("2024-01-01")
    val toDate = LocalDate.parse("2024-01-01")

    val result = underTest.get(fromDate, toDate)

    result shouldBe NearEarthObjectResponse(List.empty)
  }

}
