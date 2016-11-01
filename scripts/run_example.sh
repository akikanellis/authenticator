#!/bin/bash
set -e

# You can run it from any directory.
CURRENT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PROJECT_DIR="$CURRENT_DIR/.."
MODULE="examples"
JAR_PATH="$PROJECT_DIR/$MODULE/build/libs/$MODULE-all.jar"

echo "Building jar."
"$PROJECT_DIR"/gradlew -p $MODULE shadowJar

echo "Executing jar."
java -jar $JAR_PATH
