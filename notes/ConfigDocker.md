# Ambiente de Testes
##  Configurando o Docker

### Baixar as imagens

docker pull postgres
docker pull dpage/pgadmin4
docker pull qaninja/ninjaplus-api
docker pull qaninja/ninjaplus-web

#### Criar a rede  Docker

docker network create --driver bridge skynet

#### Subir o Banco de Dados

docker run --name pgdb --network=skynet -e "POSTGRES_PASSWORD=qaninja" -p 5432:5432 -v var/lib/postgresql/data -d postgres

#### Subir o Administrador do Banco de Dados (PgAdmin)

docker run --name pgadmin --network=skynet -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=root@qaninja.io" -e "PGADMIN_DEFAULT_PASSWORD=qaninja" -d dpage/pgadmin4

#### Subir a API 

docker run --name ninjaplus-api --network=skynet -e "DATABASE=pgdb" -p 3000:3000 -d qaninja/ninjaplus-api

#### Subir a Aplicação Web

docker run --name ninjaplus-web --network=skynet -e "VUE_APP_API=http://ninjaplus-api:3000" -p 5000:5000 -d qaninja/ninjaplus-web

#### Importante:​
**Quando você reiniciar o seu computador, certifique-se que o Docker esteja online e suba containers​ novamente:**

docker start pgdb
docker start pgadmin
​docker start ninjaplus-api
​docker start ninjaplus-web

**Se alguma coisa der errado e for necessário refazer a aula, voce poderá remover os containers com os seguintes comandos:**

docker rm -f ​pgdb
docker rm -f ​pgadmin
docker rm -f ​ninjaplus-api
docker rm -f ​ninjaplus-web


___________

*A aplicação utilizada para a automação de teste é de autoria de [Fernando Papito](https://github.com/papitoio) utilizado no treinamento [Selenide: Automação de ponta a ponta em Java](https://dojo.qaninja.com.br/curso/selenide-java/).*