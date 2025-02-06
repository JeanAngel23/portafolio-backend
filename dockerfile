# Usar una imagen oficial de OpenJDK 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo POM y las dependencias para compilar antes de copiar el código
COPY pom.xml .
COPY src ./src

# Instalar Maven y compilar la aplicación
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Copiar el .jar generado dentro del contenedor
COPY target/jeanAngel-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
