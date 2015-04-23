#!/bin/sh

#
# Usage: ./install.sh <app-name> <profile>
#

if [ $# -ne 2 ];then
    echo "Install failed! Illegal number of parameters"
    echo "Usage: ./install.sh <app-name> <profile>"
    exit 1
fi

APP=$1
PROFILE=$2
APP_USER=mora
APP__GROUP=mora

# Install application
if [ ! -d "/opt/$APP" ]; then
  mkdir /opt/$APP
fi

if [ ! -d "/opt/$APP/log" ]; then
  mkdir /opt/$APP/log
fi

cp ../$APP/target/$APP-1.0-exec.jar /opt/$APP/$APP.jar

chown -R $APP_USER:$APP_GROUP /opt/$APP


# /etc/init.d
cat initd.sh | sed -e "s/__USER_ID__/$APP_USER/" | sed -e "s/__SERVICE_NAME__/${APP}/" | sed -e "s/__ACTIVE_PROFILE__/${PROFILE}/" > /etc/init.d/$APP
chmod +x /etc/init.d/$APP

/etc/init.d/$APP restart
