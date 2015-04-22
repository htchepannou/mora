#!/bin/sh

APP=$1
JAR="$APP-1.0-exec.jar"

mkdir /opt/$APP
cp ~/repo/$APP/target/$JAR /opt/$APP/$JAR

if test ! -f "/etc/init.d/$APP"; then
    ln -s /opt/$APP/$JAR /etc/init.d/$APP
    chmod +x /etc/init.d/$APP
;fi

/etc/init.d/$APP restart

