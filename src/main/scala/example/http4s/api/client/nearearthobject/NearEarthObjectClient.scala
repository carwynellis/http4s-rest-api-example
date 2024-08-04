package example.http4s.api.client.nearearthobject

import cats.effect.IO
import example.http4s.api.client.nearearthobject.model.NearEarthObjectResponse
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder

import java.time.LocalDate

class NearEarthObjectClient(baseUrl: String = NearEarthObjectClient.BaseUrl) {

  private val client = EmberClientBuilder.default[IO].build

  def get(from: LocalDate, to: LocalDate): IO[NearEarthObjectResponse] =
    client.use {
      client => client.expect[NearEarthObjectResponse](s"$baseUrl?start_date=$from&end_date=$to&api_key=DEMO_KEY")
    }

}

object NearEarthObjectClient {
  // TODO - this should come from config
  val BaseUrl = "https://api.nasa.gov/neo/rest/v1/feed"
}
