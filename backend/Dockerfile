# maven 镜像
FROM maven:3.8.5-openjdk-17-slim AS build

# 定义工作目录
WORKDIR /app

# 复制项目文件到工作目录
COPY pom.xml .
COPY src ./src

# maven 打包
RUN mvn package -DskipTests

# 运行应用程序
CMD ["java","-jar","/app/target/userhub-backend-0.0.1-SNAPSHOT.jar","--spring-profiles.active=prod"]