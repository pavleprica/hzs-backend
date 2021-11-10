FROM maven:3.8.3-openjdk-17

COPY . ./

RUN mvn -B package -Dmaven.test.skip --file pom.xml

RUN mv target/hzs.todo-0.0.1-SNAPSHOT.jar ./app.jar

CMD ["java", "-jar", "./app.jar"]