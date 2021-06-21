#!/usr/bin/env bash

set -euo pipefail


cat ./app-src/ci/tasks/build-covid-data-image/kubeconfig-template | sed "s/kubetoken/${kubetoken}/g" > kubeconfig

export KUBECONFIG=$(pwd)/kubeconfig

kubectl cluster-info

kubectl apply -k ./app-deployment-definition/overlays/staging