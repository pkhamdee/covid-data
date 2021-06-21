#!/usr/bin/env bash

set -eu -o pipefail

pushd app-src
  ./gradlew --no-daemon check --stacktrace
popd