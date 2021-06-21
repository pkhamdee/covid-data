#!/usr/bin/env bash

set -euo pipefail

echo ${kubetoken} 

cat ./app-src/ci/tasks/build-covid-data-image/kubeconfig-template | sed "s/kubetoken/${kubetoken}/g" > kubeconfig

cat ./kubeconfig

export KUBECONFIG=./kubeconfig

kubectl cluster-info

# cd app-src
# REVISION=$(git rev-parse HEAD)
# echo ${REVISION}
# kp -n images image patch covid-data --git-revision ${REVISION} -w