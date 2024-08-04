package example.http4s.api.client.nearearthobject

import cats.effect.unsafe.implicits.global
import example.http4s.api.client.nearearthobject.model.{NearEarthObject, NearEarthObjectResponse}
import example.http4s.api.test.WireMockSupport
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should
import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, get, stubFor, urlEqualTo}

import java.time.LocalDate

class NearEarthObjectClientTest extends AnyFunSuiteLike with should.Matchers with WireMockSupport {

  private lazy val underTest = new NearEarthObjectClient(s"$baseUrl/neo/rest/v1/feed")

  test("should return near earth objects response for a valid request") {
    val fromDate = LocalDate.parse("2024-01-01")
    val toDate = LocalDate.parse("2024-01-01")

    stubValidResponse()

    val result = underTest.get(fromDate, toDate).unsafeRunSync()

    result shouldBe NearEarthObjectResponse(List(
      NearEarthObject("465633 (2009 JR5)"),
      NearEarthObject("(2008 QV11)")
    ))
  }

  private def stubValidResponse() = {
    stubFor(get(urlEqualTo("/neo/rest/v1/feed?start_date=2024-01-01&end_date=2024-01-01&api_key=DEMO_KEY"))
      .willReturn(
        aResponse()
          .withStatus(200)
          .withHeader("Content-type", "application/json")
          // TODO - move this into separate file
          .withBody(
            """
              |{
              |  "links": {
              |    "next": "http://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-08&end_date=2015-09-09&detailed=false&api_key=DEMO_KEY",
              |    "previous": "http://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-06&end_date=2015-09-07&detailed=false&api_key=DEMO_KEY",
              |    "self": "http://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&detailed=false&api_key=DEMO_KEY"
              |  },
              |  "element_count": 2,
              |  "near_earth_objects": {
              |    "2015-09-08": [
              |      {
              |        "links": {
              |          "self": "http://api.nasa.gov/neo/rest/v1/neo/2465633?api_key=DEMO_KEY"
              |        },
              |        "id": "2465633",
              |        "neo_reference_id": "2465633",
              |        "name": "465633 (2009 JR5)",
              |        "nasa_jpl_url": "https://ssd.jpl.nasa.gov/tools/sbdb_lookup.html#/?sstr=2465633",
              |        "absolute_magnitude_h": 20.44,
              |        "estimated_diameter": {
              |          "kilometers": {
              |            "estimated_diameter_min": 0.2170475943,
              |            "estimated_diameter_max": 0.4853331752
              |          },
              |          "meters": {
              |            "estimated_diameter_min": 217.0475943071,
              |            "estimated_diameter_max": 485.3331752235
              |          },
              |          "miles": {
              |            "estimated_diameter_min": 0.1348670807,
              |            "estimated_diameter_max": 0.3015719604
              |          },
              |          "feet": {
              |            "estimated_diameter_min": 712.0984293066,
              |            "estimated_diameter_max": 1592.3004946003
              |          }
              |        },
              |        "is_potentially_hazardous_asteroid": true,
              |        "close_approach_data": [
              |          {
              |            "close_approach_date": "2015-09-08",
              |            "close_approach_date_full": "2015-Sep-08 20:28",
              |            "epoch_date_close_approach": 1441744080000,
              |            "relative_velocity": {
              |              "kilometers_per_second": "18.1279360862",
              |              "kilometers_per_hour": "65260.5699103704",
              |              "miles_per_hour": "40550.3802312521"
              |            },
              |            "miss_distance": {
              |              "astronomical": "0.3027469457",
              |              "lunar": "117.7685618773",
              |              "kilometers": "45290298.225725659",
              |              "miles": "28142086.3515817342"
              |            },
              |            "orbiting_body": "Earth"
              |          }
              |        ],
              |        "is_sentry_object": false
              |      },
              |      {
              |        "links": {
              |          "self": "http://api.nasa.gov/neo/rest/v1/neo/3426410?api_key=DEMO_KEY"
              |        },
              |        "id": "3426410",
              |        "neo_reference_id": "3426410",
              |        "name": "(2008 QV11)",
              |        "nasa_jpl_url": "https://ssd.jpl.nasa.gov/tools/sbdb_lookup.html#/?sstr=3426410",
              |        "absolute_magnitude_h": 21.34,
              |        "estimated_diameter": {
              |          "kilometers": {
              |            "estimated_diameter_min": 0.1434019235,
              |            "estimated_diameter_max": 0.320656449
              |          },
              |          "meters": {
              |            "estimated_diameter_min": 143.4019234645,
              |            "estimated_diameter_max": 320.6564489709
              |          },
              |          "miles": {
              |            "estimated_diameter_min": 0.0891057966,
              |            "estimated_diameter_max": 0.1992466184
              |          },
              |          "feet": {
              |            "estimated_diameter_min": 470.4787665793,
              |            "estimated_diameter_max": 1052.0225040417
              |          }
              |        },
              |        "is_potentially_hazardous_asteroid": false,
              |        "close_approach_data": [
              |          {
              |            "close_approach_date": "2015-09-08",
              |            "close_approach_date_full": "2015-Sep-08 14:31",
              |            "epoch_date_close_approach": 1441722660000,
              |            "relative_velocity": {
              |              "kilometers_per_second": "19.7498128142",
              |              "kilometers_per_hour": "71099.3261312856",
              |              "miles_per_hour": "44178.3562841869"
              |            },
              |            "miss_distance": {
              |              "astronomical": "0.2591250701",
              |              "lunar": "100.7996522689",
              |              "kilometers": "38764558.550560687",
              |              "miles": "24087179.7459520006"
              |            },
              |            "orbiting_body": "Earth"
              |          }
              |        ],
              |        "is_sentry_object": false
              |      }
              |    ]
              |  }
              |}
              |""".stripMargin)
      )
    )
  }

}
