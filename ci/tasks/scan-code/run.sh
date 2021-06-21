#!/usr/bin/env bash

set -eu -o pipefail

snyk auth ${snyktoken}

cd app-src 
snyk monitor --all-projects --org=pkhamdee