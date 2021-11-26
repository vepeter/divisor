FROM adoptopenjdk/openjdk11:alpine-jre
LABEL PROJECT_NAME=divisor
VOLUME /tmp

# Fix for alpine openjdk not including fonts and font configuration
RUN apk add --no-cache fontconfig ttf-dejavu && fc-cache -f && \
	rm -rf /var/cache/apk/*

EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} divisor.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/divisor.jar"]