#!/usr/bin/env bash

set -eu -o pipefail

pushd app-src/covid
  ./gradlew --no-daemon check --stacktrace
popd