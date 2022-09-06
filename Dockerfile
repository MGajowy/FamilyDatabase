FROM openjdk:11

COPY target/FamilyDatabase-*.jar /FamilyDatabase.jar

CMD ["java" , "-jar" , "/FamilyDatabase.jar" ]
