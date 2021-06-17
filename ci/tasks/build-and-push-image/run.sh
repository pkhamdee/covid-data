#!/usr/bin/env bash

set -eu -o pipefail

pushd app-src/covid
  ./gradlew jib \
    -Djib.from.image=${JIB_BASE_IMAGE} \
    -Djib.to.image=${JIB_TARGET_IMAGE}
popd