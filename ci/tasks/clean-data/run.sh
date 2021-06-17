#!/usr/bin/env bash

set -eu -o pipefail

# Install vim
apk add --no-cache vim

for filename in ${DATA_INPUT_FOLDER_PATH}/*.csv; do
  vim --clean -c 'se nobomb|wq' $filename
  cp $filename ${DATA_OUTPUT_FOLDER_PATH}
done
