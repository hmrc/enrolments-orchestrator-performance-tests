package uk.gov.hmrc.perftest

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner

trait AgentTerminationES9Simulation extends PerformanceTestRunner with UrlHelper {

  setup("AgentTerminationES9S", "AgentTerminationES9S").withRequests(callDeletePoint)

}
