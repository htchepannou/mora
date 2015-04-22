#!/bin/sh

APP=$1
JAR="$APP-1.0-exec.jar"

if test ! -d "/opt/$APP"; then
  mkdir /opt/$APP
fi
cp ~/repo/mora/$APP/target/$JAR /opt/$APP/$JAR

if test ! -f "/etc/init.d/$APP"; then
    ln -s ~/repo/mora/$APP/etc/initd.sh /etc/init.d/$APP
    chmod +x /etc/init.d/$APP
fi

#/etc/init.d/$APP restart
