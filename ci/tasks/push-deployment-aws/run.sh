#!/usr/bin/env bash

set -eu -o pipefail

# configure awscli
export AWS_ACCESS_KEY_ID=${AWS_PROVISION_CLUSTER_SA_ACCESS_KEY_ID}
export AWS_SECRET_ACCESS_KEY=${AWS_PROVISION_CLUSTER_SA_SECRET_ACCESS_KEY}

  # update kubeconfig
aws eks --region ${EKS_CLUSTER_REGION} update-kubeconfig --name ${EKS_CLUSTER_NAME}

kubectl apply -f ${DEFINITION_FILE_PATH}