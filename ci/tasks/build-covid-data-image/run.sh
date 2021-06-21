#!/usr/bin/env bash

set -euo pipefail

cat ./app-src/ci/tasks/build-covid-data-image/kubeconfig-template | sed "s/kubetoken/${kubetoken}/g" > kubeconfig

export KUBECONFIG=$(pwd)/kubeconfig

kubectl cluster-info

cd app-src
REVISION=$(git rev-parse HEAD)

kp -n default image patch covid-data --git-revision ${REVISION} -w