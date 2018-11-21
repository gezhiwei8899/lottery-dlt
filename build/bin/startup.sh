#!/usr/bin/env bash

BIN_PATH=$(cd "$(dirname "$0")"; pwd)
CONF_PATH="$BIN_PATH/../conf"
LIBS_PATH="$BIN_PATH/../libs"

CLASSPATH="$CONF_PATH/:$LIBS_PATH/*"
echo 'Start the service'
java -classpath $CLASSPATH com.gezhiwe.lotterydlt.LotteryDltApplication





