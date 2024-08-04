package example.http4s.api.client.nearearthobject.model

import io.circe.Decoder
import io.circe.generic.extras.semiauto.deriveConfiguredDecoder
import CirceConfiguration.config

case class NearEarthObjectResponse(nearEarthObjects: Map[String, List[NearEarthObject]])

object NearEarthObjectResponse {
  implicit val nearEarthObjectResponseDecoder: Decoder[NearEarthObjectResponse] = deriveConfiguredDecoder[NearEarthObjectResponse]
}
