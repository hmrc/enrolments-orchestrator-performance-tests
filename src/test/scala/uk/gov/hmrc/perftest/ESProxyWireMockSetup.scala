package uk.gov.hmrc.perftest

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.github.tomakehurst.wiremock.stubbing.StubMapping

trait ESProxyWireMockSetup {

  val wiremockHost: String = "localhost"
  val wiremockPort: Int = 9595
  val wireMockServer = new WireMockServer(wireMockConfig().port(wiremockPort))
  WireMock.configureFor(wiremockHost, wiremockPort)

  def mockTaxEnrolmentsES9(arn: String): StubMapping = {

    wireMockServer.start()

    def enrolmentKey = s"HMRC-AS-AGENT~AgentReferenceNumber~$arn"
    def GROUP_ID = "90ccf333-65d2-4bf2-a008-01dfca702161"

    stubFor(
      get(urlEqualTo(s"/enrolment-store-proxy/enrolment-store/enrolments/$enrolmentKey/groups?type=principal"))
        .willReturn(aResponse().withStatus(200)
          .withBody(s"""{"principalGroupIds":["$GROUP_ID"]}"""))
    )

    stubFor(
      get(urlEqualTo(s"/enrolment-store-proxy/enrolment-store/enrolments/$enrolmentKey/users"))
        .willReturn(aResponse().withStatus(204))
    )

    stubFor(
      delete(urlEqualTo(s"/enrolment-store-proxy/enrolment-store/groups/$GROUP_ID/enrolments/$enrolmentKey"))
        .willReturn(aResponse().withStatus(204))
    )

  }

}
