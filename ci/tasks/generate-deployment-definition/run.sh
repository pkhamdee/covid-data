#!/usr/bin/env bash

set -eu -o pipefail

shopt -s dotglob

export DIGEST=$(cat covid-data-image/digest)

sed -i.bak "s/newTag:.*$/newName: harbor.dev.pkhamdee.com/cna-covid/covid-data@${DIGEST}/g" ./app-deployment-definition/overlays/staging/kustomization.yaml
rm -f ./app-deployment-definition/overlays/staging/kustomization.yaml.bak

cd update-deployment

cp -r ../app-deployment-definition/*  .

echo ${git-email}
echo ${git-user}

git config --global user.email "${git-email}"
git config --global user.name "${git-user}"
git add -A
git commit -m "Update newtag version: ${DIGEST}"