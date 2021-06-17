#!/usr/bin/env bash

set -eu -o pipefail

echo $GKE_DEPLOY_KEY > key-file.json

#gcloud auth activate-service-account ${GKE_DEPLOY_SA_EMAIL} --key-file=key-file.json
#gcloud container clusters get-credentials ${GKE_CLUSTER_NAME} --zone=${GKE_CLUSTER_ZONE} --project=${GKE_PROJECT_ID}

kubectl apply -f ${DEFINITION_FILE_PATH}