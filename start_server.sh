#! /bin/sh

echo "Java Version"
java -version

echo "Start Spring Boot Application!"
CURRENT_PID=$(ps -ef | grep thtapis-0.0.1-SNAPSHOT.jar | grep java | awk '{print $2}')

echo $CURRENT_PID

if [ -z $CURRENT_PID ]; then
        echo ">현재 구동중인 어플리케이션이 없으므로 종료하지 않습니다."

else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 10
fi

echo ">어플리케이션 배포 진행!"

nohup java -jar ~/deploy/tht-apis/build/libs/tht-apis-0.0.1-SNAPSHOT.jar >> ~/deploy/logs/$(date '+%Y-%m-%d')_api.log 2>&1 &
