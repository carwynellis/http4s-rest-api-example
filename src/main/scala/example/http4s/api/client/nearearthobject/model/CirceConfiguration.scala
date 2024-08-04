package example.http4s.api.client.nearearthobject.model

import io.circe.generic.extras.Configuration

object CirceConfiguration {

  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames

}
