FROM maven:3.8.3-openjdk-17

ARG spring_profile
ARG db_host
ARG db_username
ARG db_password

ENV SPRING_PROFILES_ACTIVE=$spring_profile
ENV DATABASE_HOST=$db_host
ENV DATABASE_USERNAME=$db_username
ENV DATABASE_PASSWORD=$db_password

COPY . ./

RUN mvn -B package -Dmaven.test.skip --file pom.xml

RUN mv target/hzs.todo-0.0.1-SNAPSHOT.jar ./app.jar

CMD ["java", "-jar", "./app.jar"]