
# enrolments-orchestrator-performance-tests

Repository for holding enrolments-orchestrator performance testing journeys.

### Smoke test (Local)

It might be useful to try the journey with one user to check that everything works fine before running a full performance test
```
Simply in terminal run: 
./run-local.sh
OR run the following two lines
sm --start SECURITY_DELETE_ORCHESTRATOR_ALL -f --wait 60 --noprogress
sbt -Dperftest.runSmokeTest=true gatling:test
```

### Run a full performance test (Local)
```
sm --start SECURITY_DELETE_ORCHESTRATOR_ALL -f --wait 60 --noprogress
sbt gatling:test
```
