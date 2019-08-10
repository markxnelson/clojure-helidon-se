#!/bin/bash
GRAALVM_HOME=/home/mark/graalvm-ee-19.1.1
JAVA_HOME=/home/mark/graalvm-ee-19.1.1
PATH=$JAVA_HOME/bin:$PATH

function status () {
  echo -e "\e[1m$1\e[0m"
}

status 'Build the uberjar...'
lein uberjar

status 'Run the application with tracing agent...'
rm -rf config-dir trace-file.json
sh -c 'java \
  -agentlib:native-image-agent=trace-output=$PWD/trace-file.json \
  -jar target/uberjar/clojure-se-0.1.0-SNAPSHOT-standalone.jar' & 
PID=$!
echo "PID=$PID"

sleep 4
status 'Make a few client requests...'
curl http://localhost:8080/bob
curl http://localhost:8080/dave
sleep 4

status 'Kill the application...'
sleep 4
ps | grep $PID
trap "kill $PID" INT
wait

status 'Create native image configuration files...'
native-image-configure \
  generate \
  --trace-input=$PWD/trace-file.json \
  --output-dir=$PWD/config-dir

status 'Build native image...'
native-image \
  --allow-incomplete-classpath \
  --no-fallback \
  --report-unsupported-elements-at-runtime \
  -H:+ReportExceptionStackTraces \
  -H:ConfigurationFileDirectories=$PWD/config-dir \
  -jar target/uberjar/clojure-se-0.1.0-SNAPSHOT-standalone.jar 
