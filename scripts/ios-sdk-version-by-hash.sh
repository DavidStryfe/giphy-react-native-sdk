#!/bin/bash
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd)"

. "$SCRIPT_DIR/ios-sdk-conf.sh"

while read -r line; do
  if [[ "$line" =~ $PODSPEC_SDK_VERSION_REGEX ]]; then
    echo "${BASH_REMATCH[3]}"
    break
  fi
done <<<"$(git show "$1":"$PODSPEC")"
