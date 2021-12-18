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
	java -jar build/libs/spring-frontend-1.0.jar

docker-push:
	docker login
	docker build -t sjsualeinbartolome/spring-frontend:v1.0 -t sjsualeinbartolome/spring-frontend:v1.0 .
	docker push sjsualeinbartolome/spring-frontend:v1.0
docker-kong:
	docker run -d --name spring-frontend --network kong-network -td spring-frontend

docker-build: jar
	docker build -t spring-frontend .
	docker images