#!/usr/bin/env bash

set -eu -o pipefail

APP_IMAGE_DIGEST=`cat ${APP_IMAGE_DIGEST_FILE}`

cat ${DEPLOYMENT_DEFINITION_TEMPLATE_FILE} | sed "s/{{DIGEST}}/$APP_IMAGE_DIGEST/g" > deployment-definition/deployment-definition.yaml