all: clean

clean:
	gradle clean

compile:
	gradle build

test:
	gradle test

jar: 
	gradle build -x test
	gradle bootJar

run: jar
	echo Starting Spring at:  http://localhost:8080
	java -jar build/libs/users.jar

# MySQL DB

mysql:
	docker run -d --network users --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0

mysql-shell:
	docker exec -it mysql bash

# Docker

docker-build: jar
	docker build -t users .
	docker images

users-network:
	docker network create --driver bridge users

docker-run: docker-build
	docker run --network users -e "MYSQL_HOST=mysql" --name users -td -p 8080:8080 users

docker-clean:
	docker stop users
	docker rm users
	docker rmi users

docker-shell:
	docker exec -it users bash 

docker-push:
	docker login
	docker build -t $(account)/users:v1 -t $(account)/users:v1 .
	docker push $(account)/users:v1 

# Compose

network-ls:
	docker network ls 

network-create:
	docker network create --driver bridge users-v1_network

network-prune:
	docker network prune

compose-up:
	docker-compose up --scale users=1 -d

compose-down:
	docker-compose down 	

lb-stats:
	echo "user = admin | password = admin"
	open http://localhost:1936

lb-test:
	open http://localhost


# Pod

pod-run:
	kubectl apply -f pod.yaml

pod-list:
	kubectl get pods

pod-desc:
	kubectl describe pods users

pod-delete:
	kubectl delete -f pod.yaml

pod-shell:
	kubectl exec -it users -- /bin/bash

pod-logs:
	kubectl logs -f users

# Deployment

deployment-create:
	kubectl create -f deployment.yaml --save-config 

deployment-get:
	kubectl get deployments

deployment-get-pods:
	kubectl get pods -l name=users

deployment-pod-shell:
	kubectl exec -it $(pod) -- /bin/bash

deployment-upgrade:
	kubectl apply  -f deployment.yaml

deployment-delete:
	kubectl delete deployment users-deployment

# Service

service-create:
	kubectl create -f service.yaml

service-get:
	kubectl get services

service-get-ip:
	kubectl get service users -o wide

service-delete:
	kubectl delete service users

# Ingress

ingress-apply:
	kubectl apply -f ingress.yaml

ingress-ip:
	kubectl get ingress users-ingress

# Jumpbox

jumpbox:
	docker run --network test --name jumpbox -t -d ubuntu

shell:
	docker exec -it jumpbox bash 

clean:
	docker stop jumpbox
	docker rm jumpbox

jumpbox-create:
	kubectl create -f jumpbox.yaml

jumpbox-get:
	kubectl get pod jumpbox

jumpbox-shell:
	kubectl exec  -it jumpbox -- /bin/bash

jumpbox-delete:
	kubectl delete pod jumpbox

jumpbox-tools:
	apt-get update
	apt-get install curl
	apt-get install iputils-ping