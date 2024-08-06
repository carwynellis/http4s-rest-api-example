package example.http4s.api.client.nearearthobject

import cats.effect.IO
import example.http4s.api.client.nearearthobject.model.{NearEarthObject, NearEarthObjectResponse}
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.ember.client.EmberClientBuilder

import java.time.LocalDate

class NearEarthObjectClient(
  baseUrl: String = NearEarthObjectClient.BaseUrl,
  apiKey: String = NearEarthObjectClient.ApiKey) {

  private val client = EmberClientBuilder.default[IO].build

  def getForDateRange(from: LocalDate, to: LocalDate): IO[NearEarthObjectResponse] =
    client.use { client =>
      client
        .expect[NearEarthObjectResponse](s"$baseUrl/feed?start_date=$from&end_date=$to&api_key=$apiKey")
    }

  def getById(id: String): IO[NearEarthObject] =
    client.use {
      client => client.expect[NearEarthObject](s"$baseUrl/neo/$id?api_key=$apiKey")
    }

}

object NearEarthObjectClient {
  // TODO - this should come from config
  val ApiKey = "DEMO_KEY"
  val BaseUrl = "https://api.nasa.gov/neo/rest/v1"
}