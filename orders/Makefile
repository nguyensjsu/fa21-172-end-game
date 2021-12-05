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
	java -jar build/libs/orders-1.0.jar

# MySQL DB

mysql:
	docker run -d --network orders --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0

mysql-shell:
	docker exec -it mysql bash

# Docker

docker-build: jar
	docker build -t orders .
	docker images

orders-network:
	docker network create --driver bridge orders

docker-run: docker-build
	docker run --network orders -e "MYSQL_HOST=mysql" --name orders -td -p 8080:8080 orders

docker-clean:
	docker stop orders
	docker rm orders
	docker rmi orders

docker-shell:
	docker exec -it orders bash 

docker-push:
	docker login
	docker build -t $(account)/orders:v1 -t $(account)/orders:v1 .
	docker push $(account)/orders:v1 

# Compose

network-ls:
	docker network ls 

network-create:
	docker network create --driver bridge orders-v1_network

network-prune:
	docker network prune

compose-up:
	docker-compose up --scale orders=1 -d

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
	kubectl describe pods orders

pod-delete:
	kubectl delete -f pod.yaml

pod-shell:
	kubectl exec -it orders -- /bin/bash

pod-logs:
	kubectl logs -f orders

# Deployment

deployment-create:
	kubectl create -f deployment.yaml --save-config 

deployment-get:
	kubectl get deployments

deployment-get-pods:
	kubectl get pods -l name=orders

deployment-pod-shell:
	kubectl exec -it $(pod) -- /bin/bash

deployment-upgrade:
	kubectl apply  -f deployment.yaml

deployment-delete:
	kubectl delete deployment orders-deployment

# Service

service-create:
	kubectl create -f service.yaml

service-get:
	kubectl get services

service-get-ip:
	kubectl get service orders -o wide

service-delete:
	kubectl delete service orders

# Ingress

ingress-apply:
	kubectl apply -f ingress.yaml

ingress-ip:
	kubectl get ingress orders-ingress

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