FROM openjdk:21-jdk-slim
COPY ./out/artifacts/ServerRifiutiVeneto_jar/ServerRifiutiVeneto.jar /app/ServerRifiutiVeneto.jar
COPY ./Regione-Veneto---Quantita-di-rifiuti-prodotti-per-tipo-e-provincia.csv /app/Regione-Veneto---Quantita-di-rifiuti-prodotti-per-tipo-e-provincia.csv
WORKDIR /app
EXPOSE 1050
CMD ["java","-jar","ServerRifiutiVeneto.jar"]