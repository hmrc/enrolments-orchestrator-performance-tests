#!/bin/bash
shopt -s expand_aliases

echo "Checking for custom smserver configuration folder"
if [ -z $CUSTOM_SMC_FOLDER ]; then export CUSTOM_SMC_FOLDER="$WORKSPACE/service-manager-config"
fi;

alias smc="sm --config $CUSTOM_SMC_FOLDER"

echo "Starting TAX_ENROLMENTS service profile...."
smc --start ENROLMENTS_ORCHESTRATOR AUTH DATASTREAM USER_DETAILS TAX_ENROLMENTS -f --wait 60 --noprogress
smc -s

RESULT=$?
if [ $RESULT -eq 0 ]
  then
    sbt gatling:test
  else
    echo "Could not start all the services for ENROLMENTS_ORCHESTRATOR to run. Please investigate reason for startup failure with service manager!"
    smc --stop ENROLMENTS_ORCHESTRATOR AUTH DATASTREAM USER_DETAILS TAX_ENROLMENTS
    exit $RESULT
  fi;

smc --stop ENROLMENTS_ORCHESTRATOR AUTH DATASTREAM USER_DETAILS TAX_ENROLMENTS
