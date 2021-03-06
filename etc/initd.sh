#!/bin/bash
#
# Startup script for a spring boot project
#
# chkconfig: - 84 16
# description: spring boot project

USER_ID=__USER_ID__
SERVICE_NAME=__SERVICE_NAME__
ACTIVE_PROFILE=__ACTIVE_PROFILE__
SERVICE_DIR=/opt/$SERVICE_NAME
PATH_TO_JAR=$SERVICE_DIR/$SERVICE_NAME.jar
PID_PATH_NAME=$SERVICE_DIR/$SERVICE_NAME.pid
LOG_FILE=$SERVICE_DIR/log/$SERVICE_NAME.log

case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            su - $USER_ID -c "java -jar $PATH_TO_JAR --spring.profiles.active=$ACTIVE_PROFILE --logging.file=$LOG_FILE --spring.pidfile=$PID_PATH_NAME > /dev/null 2>&1  &"
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;

    restart)
        $0 stop
        $0 start
    ;;

    *)
        echo "Usage: $0 {start|stop|restart}"
        exit 1
esac
