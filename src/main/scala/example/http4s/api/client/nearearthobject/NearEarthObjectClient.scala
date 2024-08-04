package example.http4s.api.client.nearearthobject

import example.http4s.api.client.nearearthobject.model.NearEarthObjectResponse

import java.time.LocalDate

class NearEarthObjectClient {

  def get(from: LocalDate, to: LocalDate): NearEarthObjectResponse = NearEarthObjectResponse(List.empty)

}
