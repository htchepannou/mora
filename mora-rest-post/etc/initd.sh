#!/bin/bash
#
# Startup script for a spring boot project
#
# chkconfig: - 84 16
# description: spring boot project

SERVICE_NAME=mora-rest-post
PATH_TO_JAR=/opt/mora-rest-post/mora-rest-post.jar
PID_PATH_NAME=/opt/mora-rest-post/mora-rest-post.pid
USER_ID=mora

case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            su - USER_ID -c "java -jar $PATH_TO_JAR > /dev/null 2>&1  &"
            echo $! > $PID_PATH_NAME
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
