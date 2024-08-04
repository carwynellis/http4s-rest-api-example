package example.http4s.api.client.nearearthobject

import example.http4s.api.client.nearearthobject.model.NearEarthObjectResponse

import java.time.LocalDate

class NearEarthObjectClient(baseUrl: String = NearEarthObjectClient.BaseUrl) {

  def get(from: LocalDate, to: LocalDate): NearEarthObjectResponse = NearEarthObjectResponse(List.empty)

}

object NearEarthObjectClient {
  // TODO - this should come from config
  val BaseUrl = "https://api.nasa.gov/neo/rest/v1/feed"
}
