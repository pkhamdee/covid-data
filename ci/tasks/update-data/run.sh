#!/usr/bin/env bash

set -eu -o pipefail

git clone app-src updated-app-src

cp -r ${DATA_FOLDER_PATH}/. ${COVID_APP_RESOURCES_FOLDER_PATH}/

cd updated-app-src

git config --global user.email "svanan@pivotal.io"
git config --global user.name "CNA-CI"

git add .
git commit -m "Updated covid data"