package uk.gov.hmrc.perftest

import java.nio.charset.StandardCharsets.UTF_8
import java.util.Base64

import io.gatling.core.Predef._
import io.gatling.http.Predef.{HttpHeaderNames, http, status}
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait UrlHelper extends ServicesConfiguration with ESProxyWireMockSetup {

  val TOKEN: String = s"Basic ${Base64.getEncoder.encodeToString("username:password".getBytes(UTF_8))}"
  val AUTHORIZATION_HEADER: Map[String, String] = Map((HttpHeaderNames.Authorization, TOKEN))
  val ARN = "arn1225942976"

  def callDeletePoint(): HttpRequestBuilder = {

    mockTaxEnrolmentsES9(ARN)
    http(s"Call the orchestrator for $ARN")
      .delete(deletePoint(ARN))
      .headers(AUTHORIZATION_HEADER)
      .check(status is 200)
  }

  def deletePoint(arn: String): String = {
    baseUrlFor("enrolments-orchestrator") + s"/enrolments-orchestrator/agents/$arn"
  }

}
