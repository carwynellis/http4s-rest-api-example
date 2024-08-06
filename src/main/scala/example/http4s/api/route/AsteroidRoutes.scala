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
    case GET -> Root / "asteroid"
      :? FromDateQueryParameterMatcher(from)
      :? ToDateQueryParameterMatcher(to)
      :? SortQueryParameterMatcher(sort) =>
      (from, to, sort) match {
        case (Valid(fromDate), Valid(toDate), Some(Valid(sortData))) if toDate >= fromDate =>
          if (sortData) Ok(asteroidService.getSortedAsteroidsForDateRange(fromDate, toDate))
          else Ok(asteroidService.getAsteroidsForDateRange(fromDate, toDate))
        case (Valid(fromDate), Valid(toDate), None) if toDate >= fromDate =>
          Ok(asteroidService.getAsteroidsForDateRange(fromDate, toDate))
        // TODO - revise to look at invalid values and provide specific error messages for each parameter
        case _ => BadRequest("from and to date query parameters missing or invalid parameter values")
      }
    case GET -> Root / "asteroid" => BadRequest("required from and to date parameters missing")
  }

  implicit val dateQueryParameterDecoder: QueryParamDecoder[LocalDate] =
    QueryParamDecoder[String]
      .emap(l => Try(LocalDate.parse(l))
      .toEither
      .leftMap(e => ParseFailure(e.getMessage, e.getMessage)))

  implicit val sortQueryParameterDecoder: QueryParamDecoder[Boolean] =
    QueryParamDecoder[String]
      .emap(s => Try(s.toBoolean)
        .toEither
        .leftMap(e => ParseFailure(e.getMessage, e.getMessage)))

  private object FromDateQueryParameterMatcher extends ValidatingQueryParamDecoderMatcher[LocalDate]("from")
  private object ToDateQueryParameterMatcher extends ValidatingQueryParamDecoderMatcher[LocalDate]("to")
  private object SortQueryParameterMatcher extends OptionalValidatingQueryParamDecoderMatcher[Boolean]("sort")

}
