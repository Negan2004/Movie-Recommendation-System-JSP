FROM eclipse-temurin:11-jdk

WORKDIR /app

COPY MovieRecommendationSystem.war app.war

EXPOSE 8080

CMD ["java", "-jar", "app.war"]
