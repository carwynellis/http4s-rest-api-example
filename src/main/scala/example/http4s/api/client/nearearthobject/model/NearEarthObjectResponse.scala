package example.http4s.api.client.nearearthobject.model

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

case class NearEarthObjectResponse(nearEarthObjects: List[NearEarthObject])

object NearEarthObjectResponse {
  implicit val nearEarthObjectResponseDecoder: Decoder[NearEarthObjectResponse] = deriveDecoder[NearEarthObjectResponse]
}
