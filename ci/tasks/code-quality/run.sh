#!/usr/bin/env bash

set -eu -o pipefail

pushd app-src
  ./gradlew sonarqube  -Dsonar.projectKey=${sonar-projectKey} -Dsonar.host.url=${sonar-host-url}  -Dsonar.login=${sonar-login}
popd