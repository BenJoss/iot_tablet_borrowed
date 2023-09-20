#引入JDK配置
FROM java:8
#发布到网上时只会把jar包和Dockerfile发布上去
RUN mkdir /opt/huachen/
COPY CALL-SERVICE-1.0.0.1-SNAPSHOT.jar /opt/huachen/callservice.jar
#地址映射
CMD ["--server.port=8081"]
#对外暴露端口(可以任意修改)
EXPOSE 8081
#执行命令
ENTRYPOINT ["java","-jar","/opt/huachen/callservice.jar"]
