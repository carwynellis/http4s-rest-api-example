package example.http4s.api.service

import example.http4s.api.model.Asteroid

import java.time.LocalDate

class AsteroidService {

  def getAsteroidsForDateRange(from: LocalDate, to: LocalDate): List[Asteroid] = List(Asteroid("foo"))

}
