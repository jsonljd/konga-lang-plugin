FROM openjdk:8-jre
MAINTAINER liangjiandong <39368574@qq.com>

COPY ./release/deploy/lib/* /app/lang/lib/
COPY ./release/deploy/conf/* /app/lang/conf/
COPY ./release/deploy/dockerstart.sh /app/lang/

WORKDIR /app/lang

VOLUME /app/assets

ENTRYPOINT ["/bin/bash","/app/lang/dockerstart.sh","start"]