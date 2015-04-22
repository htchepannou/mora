#!/bin/sh

APP=$1
USER=mora
GROUP=mora

if test ! -d "/opt/$APP"; then
  mkdir /opt/$APP
fi
if test ! -d "/opt/$APP/log"; then
  mkdir /opt/$APP/log
fi
cp ../repo/mora/$APP/target/$APP-1.0-exec.jar /opt/$APP/$APP.jar
chown -R $USER:$GROUP /opt/$APP

if test ! -f "/etc/init.d/$APP"; then
    cp ../repo/mora/$APP/etc/initd.sh /etc/init.d/$APP
fi
chmod +x /etc/init.d/$APP

#/etc/init.d/$APP restart
