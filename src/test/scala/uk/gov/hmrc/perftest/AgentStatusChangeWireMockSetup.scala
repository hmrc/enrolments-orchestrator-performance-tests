package uk.gov.hmrc.perftest

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.github.tomakehurst.wiremock.stubbing.StubMapping

trait AgentStatusChangeWireMockSetup {

  val agentStatusChangeWiremockHost: String = "localhost"
  val agentStatusChangeWiremockPort: Int = 9424
  val wireMockAgentStatusChangeServer = new WireMockServer(wireMockConfig().port(agentStatusChangeWiremockPort))

  def agentStatusChangeReturnOK: StubMapping = {
    WireMock.configureFor(agentStatusChangeWiremockHost, agentStatusChangeWiremockPort)
    wireMockAgentStatusChangeServer.start()

    stubFor(
      delete(urlEqualTo("/agent-status-change/agent/arn1225942976/terminate"))
        .willReturn(aResponse().withStatus(200))
    )
  }

}
