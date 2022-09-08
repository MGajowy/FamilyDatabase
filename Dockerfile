FROM openjdk:11

COPY target/FamilyDatabase-*.jar app.jar

# CMD ["java" , "-jar" , "/FamilyDatabase.jar" ]
ENTRYPOINT ["java" , "-jar" , "app.jar" ]
