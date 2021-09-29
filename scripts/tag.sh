#! /bin/sh

version=$(./gradlew :sdk:properties | grep '^version:' | sed -e 's/^version: \(.*\)$/v\1/')

# When GH Actions checks out the repo it doesn't pull tags
echo "Fetching tags"
git fetch --tags

echo "Checking for ${version}"

if git show-ref --tags $version --quiet; then
  echo "Tag exists"
else
  echo "Tagging with ${version}"
  git tag $version
fi
