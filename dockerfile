# Usar una versión válida de Maven para construir la aplicación
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar y descargar dependencias antes de copiar el código fuente
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente y compilar la aplicación
COPY src ./src
RUN mvn clean package -DskipTests

# Usar una imagen más ligera de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=build /app/target/jeanAngel-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto donde correrá la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]


