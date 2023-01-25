# 	:helicopter: Drone App 	:helicopter:

Projeto do MBA de Full Stack da FIAP - Turma 43scj <br>
Matéria: Containers e Virtualization

## Features

- Envio de coordenadas de um drone para uma fila (Kafka) :heavy_check_mark:
- Consumo de coordenas de um drone na fila :heavy_check_mark:
- Envio de notificação através do e-mail :heavy_check_mark:

##  Serviços

-  <a href="https://github.com/KaiqueJuvencio/fiap-drone-adm-central-app">drone-app-central</a>: Serviço central, responsável por subir os serviços do Kafka, ZooKeeper e MySql
-  <a href="https://github.com/KaiqueJuvencio/fiap-drone-app">drone-app</a>: Serviço responsável pelo envio das coordenadas do drone para a fila (producer)
-  <a href="https://github.com/KaiqueJuvencio/fiap-drone-notify-app">drone-app-notify</a>: Serviço responsável por consumir as coordenadas do drone na fila (consumer) e notificar via e-mail


## Tech Stack

<div style="display: inline_block">
    <img align="center" alt="Java" height="50" width="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg">
    <img align="center" alt="Kubernetes" height="50" width="70" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kubernetes/kubernetes-plain.svg" />
    <img align="center" alt="Docker" height="70" width="70" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" />
    <img align="center" alt="MySql" height="80" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original-wordmark.svg" /> 
    <img align="center" alt="MySql" height="80" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/apachekafka/apachekafka-original-wordmark.svg" />       
</div>
<br>
Tecnologias usadas para construir a aplicação

- Java (jdk-19.0.1) | Spring Boot, JPA, Hibernate
- Kubernetes / Minukube
- Docker / Docker Hub
- MySql
- Apache Kafka
- ZooKeeper

## Considerações

Recomendamos utilizar o driver do Hyper-V para criação do cluster Kubernetes no Minikube

- Para permitir (enable) o driver de virtualização:
```bash
Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All
```


- Vamos criar um cluster com 6GB de memória RAM e 4 cpus (recomendado)
- Para criar o cluster Kubernetes com o driver de virtualização do Hyper-V:
```bash
minikube start --memory=6144 --cpus=4 --driver=hyperv
```

## Subindo Serviços
Agora vamos vamos subir cada serviço com seu respectivo arquivo .yml

1º - Vamos rodar o Kafka, Zookeeper e o MySql
- Para isso, vamos até a pasta raiz do projeto central <a href="https://github.com/KaiqueJuvencio/fiap-drone-adm-central-app">drone-app-central</a>
- Agora vamos rodar o .yml de cada serviço
```bash
kubectl apply -f 01-zookeeper/
kubectl apply -f 02-kafka/
kubectl apply -f 03-yahoo-kafka-manager/
kubectl apply -f 04-kafka-monitor/
kubectl apply -f 05-mysql/
```
2º - Verificar se o serviços foram criados
```bash
minikube service list
```

3º - Acessando Kafka
- Pegue o ip e porta do Kakfa Manager (retornado no comando acima) e rode no navegador

4º - Vamos configurar o Kafka
- Criar um Cluster <br>
![image](https://user-images.githubusercontent.com/39711228/214446861-1225011f-3346-4aab-a691-faacd1763b4c.png)
- Conectando ao ZooKeeper <br>
-- Cluster Name: Zookeepers <br>
-- Cluster Zookeeper Hosts: zookeeper-service:2181 <br>
![image](https://user-images.githubusercontent.com/39711228/214447636-6a57dbfe-8608-4d20-a6cc-83a6f2c6495a.png)
- Vamos criar um Topic <br>
![image](https://user-images.githubusercontent.com/39711228/214448535-23dff7a4-74be-4056-a42e-67686f2c96bb.png)  <br>
- Topic Name: 43scj.drone.topic <br>
![image](https://user-images.githubusercontent.com/39711228/214448816-3666965a-161c-487c-ba76-7c8ef3c43aa0.png)



5º - Vamos rodar a primeira aplicação backend Java (drone-app)
- Para isso, vamos até a pasta raiz do projeto <a href="https://github.com/KaiqueJuvencio/fiap-drone-app">drone-app</a>
```bash
kubectl apply -f drone-app.yaml
```

6º - Vamos rodar a segunda aplicação backend Java (drone-app-notify)
- Para isso, vamos até a pasta raiz do projeto <a href="https://github.com/KaiqueJuvencio/fiap-drone-notify-app">drone-app-notify</a>
```bash
kubectl apply -f drone-app-notify.yaml
```

## Envio de coordenadas
- Vamos pegar a url da aplicação drone-app
```bash
minikube service drome-app-service --url
```
- Basta fazer um POST na url retornada, no endpoint: "/drone/send"; e enviar no body:
```bash
{
    "latitude": "10",
    "longitude": "11",
    "rastreamento_ativo": false
}
```


