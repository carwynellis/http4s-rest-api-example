package example.http4s.api.model

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class Asteroid(
  id: String,
  name: String,
)

object Asteroid {

  implicit val asteroidCodec: Codec[Asteroid] = deriveCodec[Asteroid]

  // Default sort order is by name
  implicit object sortOrder extends Ordering[Asteroid] {
    override def compare(x: Asteroid, y: Asteroid): Int = x.name.compareTo(y.name)
  }

}
