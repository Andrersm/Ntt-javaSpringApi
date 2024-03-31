# Estágio de construção
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copia os arquivos do projeto
COPY src ./src
COPY pom.xml .

# Compila o projeto com opção para mais detalhes de erro
RUN mvn clean package -DskipTests -e


# Estágio de execução
FROM openjdk:17-slim
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
