package example.http4s.api.route

import cats.data.Validated.Valid
import cats.effect.IO
import cats.syntax.all._
import example.http4s.api.service.AsteroidService
import org.http4s.Method.GET
import org.http4s.circe.CirceEntityCodec.circeEntityEncoder
import org.http4s.dsl.io._
import org.http4s.{HttpRoutes, ParseFailure, QueryParamDecoder}

import java.time.LocalDate
import scala.math.Ordered.orderingToOrdered
import scala.util.Try

class AsteroidRoutes(asteroidService: AsteroidService) {

  def routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "asteroid" :? FromDateQueryParameterMatcher(from) :? ToDateQueryParameterMatcher(to) =>
      (from, to) match {
        case (Valid(fromDate), Valid(toDate)) =>
          if (toDate >= fromDate) Ok(asteroidService.getAsteroidsForDateRange(fromDate, toDate))
          else BadRequest("to date must not be before from date")
        case _ => BadRequest("from and to date parameters missing or invalid")
      }
    case GET -> Root / "asteroid" => BadRequest("required from and to date parameters missing")
  }

  implicit val dateQueryParameterDecoder: QueryParamDecoder[LocalDate] =
    QueryParamDecoder[String]
      .emap(l => Try(LocalDate.parse(l))
      .toEither
      .leftMap(e => ParseFailure(e.getMessage, e.getMessage)))

  private object FromDateQueryParameterMatcher extends ValidatingQueryParamDecoderMatcher[LocalDate]("from")
  private object ToDateQueryParameterMatcher extends ValidatingQueryParamDecoderMatcher[LocalDate]("to")

}
