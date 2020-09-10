FROM adoptopenjdk:14-jre-hotspot
RUN mkdir /opt/app
ADD target/*.jar /opt/app/timesheet.jar
CMD ["java", "-jar", "/opt/app/timesheet.jar"]
