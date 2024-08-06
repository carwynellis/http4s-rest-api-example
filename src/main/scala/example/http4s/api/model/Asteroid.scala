package example.http4s.api.model

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class Asteroid(name: String)

object Asteroid {
  implicit val asteroidCodec: Codec[Asteroid] = deriveCodec[Asteroid]
}
