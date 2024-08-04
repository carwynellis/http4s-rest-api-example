package example.http4s.api.client.nearearthobject.model

import io.circe.Decoder
import io.circe.generic.extras.semiauto.deriveConfiguredDecoder
import CirceConfiguration.config

case class NearEarthObject(name: String)

object NearEarthObject {
  implicit val nearEarthObjectDecoder: Decoder[NearEarthObject] = deriveConfiguredDecoder[NearEarthObject]
}
