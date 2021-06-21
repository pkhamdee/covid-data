#!/usr/bin/env bash

set -euo pipefail

echo ${kube-token} 

cat ./kubeconfig-template | sed "s/{{{kube-token}}/$kube-token/g" > kube.config

cat ./kube.config

export KUBECONFIG=./kube.config

kubectl cluster-info

# cd app-src
# REVISION=$(git rev-parse HEAD)
# echo ${REVISION}
# kp -n images image patch covid-data --git-revision ${REVISION} -w