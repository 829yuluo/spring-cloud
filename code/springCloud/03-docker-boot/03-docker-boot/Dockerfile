# 基础镜像使用java8
FROM java:8

# 作者
MAINTAINER yuluo

# 将jar包添加到容器中并更名为yuluo_docker_test.jar
ADD docker-boot-0.0.1-SNAPSHOT.jar yuluo_docker_test.jar

# 运行jar包
RUN bash -c 'touch /yuluo_docker_test.jar'
ENTRYPOINT ["java", "- jar", "yuluo_docker_test.jar"]

# 暴露6001端口为微服务端口
EXPOSE 6001