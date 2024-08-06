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
      // TODO - factor out the conversion when more fields are taken from the response
      asteroids = nearEarthObjects.map(n => Asteroid(n.id, n.name))
    } yield asteroids.toSeq

  def getSortedAsteroidsForDateRange(from: LocalDate, to: LocalDate): IO[Seq[Asteroid]] =
    getAsteroidsForDateRange(from, to).map(_.sorted)

}
