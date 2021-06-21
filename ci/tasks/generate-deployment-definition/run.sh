#!/usr/bin/env bash

set -eu -o pipefail

shopt -s dotglob

export DIGEST=$(cat covid-data-image/digest)

sed -i.bak "s/dotnet-api:.*$/dotnet-api:${VERSION}/g" ./app-deployment-definition/overlays/staging/kustomization.yaml
rm -f ./app-deployment-definition/overlays/staging/kustomization.yaml

cd update-deployment

cp -r ../app-deployment-definition/*  .

git config --global user.email "${GIT_EMAIL}"
git config --global user.name "${GIT_NAME}"
git fetch
git add -A
git commit -m "Update newtag version: ${VERSION}"