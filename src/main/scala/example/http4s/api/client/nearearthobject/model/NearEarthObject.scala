package example.http4s.api.client.nearearthobject.model

import io.circe.Decoder
import io.circe.generic.extras.semiauto.deriveConfiguredDecoder
import CirceConfiguration.config

// TODO - more data points can be added
case class NearEarthObject(
  id: String,
  name: String,
)

object NearEarthObject {
  implicit val nearEarthObjectDecoder: Decoder[NearEarthObject] = deriveConfiguredDecoder[NearEarthObject]
}
