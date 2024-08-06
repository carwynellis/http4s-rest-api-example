package example.http4s.api.service

import cats.effect.IO
import example.http4s.api.client.nearearthobject.NearEarthObjectClient
import example.http4s.api.client.nearearthobject.model.NearEarthObject
import example.http4s.api.model.Asteroid

import java.time.LocalDate

class AsteroidService(client: NearEarthObjectClient = new NearEarthObjectClient()) {

  def getAsteroidsForDateRange(from: LocalDate, to: LocalDate): IO[Seq[Asteroid]] =
    for {
      result <- client.getForDateRange(from, to)
      nearEarthObjects = result.nearEarthObjects.values.flatten
      asteroids = nearEarthObjects.map(nearEarthObjectToAsteroid)
    } yield asteroids.toSeq

  def getSortedAsteroidsForDateRange(from: LocalDate, to: LocalDate): IO[Seq[Asteroid]] =
    getAsteroidsForDateRange(from, to).map(_.sorted)

  def getAsteroidById(id: String): IO[Asteroid] =
    for {
      result <- client.getById(id)
      asteroid = nearEarthObjectToAsteroid(result)
    } yield asteroid

  // TODO - factor this out - more relevant for complex models
  private def nearEarthObjectToAsteroid(n: NearEarthObject) = Asteroid(n.id, n.name)
}
