package example.http4s.api.service

import cats.effect.IO
import example.http4s.api.client.nearearthobject.NearEarthObjectClient
import example.http4s.api.model.Asteroid

import java.time.LocalDate

class AsteroidService(client: NearEarthObjectClient = new NearEarthObjectClient()) {

  def getAsteroidsForDateRange(from: LocalDate, to: LocalDate): IO[Seq[Asteroid]] =
    for {
      result <- client.get(from, to)
      nearEarthObjects = result.nearEarthObjects.values.flatten
      asteroids = nearEarthObjects.map(n => Asteroid(n.name))
    } yield asteroids.toSeq

}
