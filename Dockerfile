# Etapa de Build
FROM eclipse-temurin:22-jdk AS build
WORKDIR /app

# Instalar Maven via apt-get
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Copiar arquivos do projeto
COPY pom.xml .
COPY src ./src

# Compilar a aplicação
RUN mvn clean package -DskipTests

# Etapa de Runtime
FROM eclipse-temurin:22-jdk
VOLUME /tmp

# Copiar o JAR compilado para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Definir o comando de entrada
ENTRYPOINT ["java", "-jar", "/app.jar"]
