# Use uma imagem JDK oficial para rodar o Spring Boot
FROM eclipse-temurin:17-jdk-alpine

# Define diretório de trabalho dentro do container
WORKDIR /app

# Copia o jar do backend para dentro do container
COPY target/apiVeiculos-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que o Spring Boot vai rodar
EXPOSE 8090

# Comando para iniciar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
