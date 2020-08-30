# Ambiente de Testes
##  Configurando o Docker

### Baixar as imagens

``docker pull postgres``

``docker pull dpage/pgadmin4``

``docker pull qaninja/ninjaplus-api``

``docker pull qaninja/ninjaplus-web``

#### Criar a rede  Docker

``docker network create --driver bridge skynet``

#### Subir o Banco de Dados

``docker run --name pgdb --network=skynet -e "POSTGRES_PASSWORD=qaninja" -p 5432:5432 -v var/lib/postgresql/data -d postgres``

#### Configuração hosts para Docker Toolbox
Por padrão, o container do Docker vem configurada no IP 127.0.0.1, mas no caso de quem utiliza o Docker Toolbox o IP será o IP da máquina virtual e para isso é necessário configurar o arquivo de hosts.

* No Windows:
    Abra o arquivo hosts localizado em:
    > C:\Windows\System32\drivers\etc

    Adicione ao arquivo:

    ``192.168.99.100  pgdb ``
    
    ``192.168.99.100 pgadmin``
    
    ``192.168.99.100 ninjaplus-api``
    
    ``192.168.99.100 ninjaplus-web``

    ***Verifique o IP gerado na sua máquina Virtual.***

#### Subir o Administrador do Banco de Dados (PgAdmin)

``docker run --name pgadmin --network=skynet -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=root@qaninja.io" -e "PGADMIN_DEFAULT_PASSWORD=qaninja" -d dpage/pgadmin4``

#### Acessando PGAdmin

    URL: http://pgadmin:15432
    User: root@qaninja.io
    Senha: qaninja

Crie um servidor com:

    Nome: pgdb
    HostName: pgdb
    User: postgres
    Senha: qaninja
    
   ![](https://github.com/felurye/selenide-study/blob/master/notes/images/criandoServidorBD.jpg)
    
*Para executar o exemplo do curso, usando a aplicação NinjaPlus crie um bd com o nome **ninjaplus**.*

   ![](https://github.com/felurye/selenide-study/blob/master/notes/images/criandoBDNinjaPlus.jpg)

#### Subir a API 

``docker run --name ninjaplus-api --network=skynet -e "DATABASE=pgdb" -p 3000:3000 -d qaninja/ninjaplus-api``

#### Subir a Aplicação Web

``docker run --name ninjaplus-web --network=skynet -e "VUE_APP_API=http://ninjaplus-api:3000" -p 5000:5000 -d qaninja/ninjaplus-web``

#### Acessos ao App

Api:
    
    URL: ninjaplus-api:3000
    Doc: ninjaplus-api:3000/apidoc

Web:
   
    URL: ninjaplus-web:5000


#### Criar um usuário via api

Com o [Postman](https://www.postman.com/downloads/), ou outro software para teste de API, faça uma requisição POST:

> URL: http://ninjaplus-api:3000/user

    {
        "full_name": "Nome",
        "email": "email@exemplo.com",
        "password": "senha"
    }
    
   ![](https://github.com/felurye/selenide-study/blob/master/notes/images/requisicaoPOSTCriandoUsuario.jpg)
   
   
   ![](https://github.com/felurye/selenide-study/blob/master/notes/images/respostaRequisicaoPOSTCriandoUsuario.jpg)
   

#### Importante:
**Quando você reiniciar o seu computador, certifique-se que o Docker esteja online e suba containers​ novamente:**

``docker start pgdb``

``docker start pgadmin``

``docker start ninjaplus-api``

``docker start ninjaplus-web``

**Se alguma coisa der errado e for necessário refazer a aula, voce poderá remover os containers com os seguintes comandos:**

``docker rm -f pgdb``

``docker rm -f pgadmin``

``docker rm -f ninjaplus-api``

``docker rm -f ninjaplus-web``

___________

*A aplicação utilizada para a automação de teste é de autoria de [Fernando Papito](https://github.com/papitoio) utilizado no treinamento [Selenide: Automação de ponta a ponta em Java](https://dojo.qaninja.com.br/curso/selenide-java/).*
