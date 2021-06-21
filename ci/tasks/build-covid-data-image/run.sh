#!/usr/bin/env bash

set -euo pipefail

echo ${kubetoken} 

cat ./kubeconfig-template | sed "s/{{{kube-token}}/${kubetoken}/g" > kubeconfig

cat ./kubeconfig

export KUBECONFIG=./kubeconfig

kubectl cluster-info

# cd app-src
# REVISION=$(git rev-parse HEAD)
# echo ${REVISION}
# kp -n images image patch covid-data --git-revision ${REVISION} -w