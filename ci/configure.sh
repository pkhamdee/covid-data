#!/usr/bin/env bash

set -eu

branch="$(git rev-parse --abbrev-ref HEAD)"
ci_dir_root="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
pipeline="covid-data"

if [[ $(lpass status -q; echo $?) != 0 ]]; then
  echo "Login with lpass first"
  exit 1
fi

if [[ "${branch}" != "covid" ]]; then
  echo "Looks like you are not on the covid branch... exiting"
  exit 1
fi

fly -t main set-pipeline -p "${pipeline}" \
    -c "$ci_dir_root"/pipeline.yml \
    --load-vars-from <(lpass show -G "cna-demo-app/cna-cloud-native-demo-app-secrets" --notes)