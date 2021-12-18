all: clean

clean:
	gradle clean
	rm -rf bin

compile:
	gradle build

test:
	gradle test

jar: 
	gradle build -x test
	gradle bootJar

run: jar
	echo Starting Spring at:  http://localhost:8080
	java -jar build/libs/spring-payments-1.0.jar

docker-push:
	docker login
	docker build -t sjsualeinbartolome/spring-payments:v4.0 -t sjsualeinbartolome/spring-payments:v4.0 .
	docker push sjsualeinbartolome/spring-payments:v4.0
docker-kong:
	docker run -d --name spring-payments --network kong-network -td spring-payments

docker-build: jar
	docker build -t spring-payments .
	docker images