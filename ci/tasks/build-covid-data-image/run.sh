#!/usr/bin/env bash

set -euo pipefail

echo ${kubeconfig} > kube.config
export KUBECONFIG=kube.config

cd app-src
REVISION=$(git rev-parse HEAD)
kp -n images image patch covid-data --git-revision ${REVISION} -w