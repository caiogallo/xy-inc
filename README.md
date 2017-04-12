# xy-inc
Aplicação com a finalidade de criar um CRUD genérico para facilitar o
desenvolvimento frontend

## Aplicação
O aplicativo foi desenvolvido utilizando SpringBoot para diminuir o tempo de
configuração, o banco de dados MongoDB foi escolhido pela flexibilidade do
modelo de dados, permitindo quantidade variável de colunas. A escolha por rodar
o MongoDB no docker foi para diminuir a quantidade de configurações necessárias
para rodar o projeto.
A ferramenta de build Maven foi utilizada para construir e gerenciar as
dependências do projeto.

## Configurações

### docker
O docker foi utilizado para facilitar a instalação do mongodb, acesse a url
[docker](https://docs.docker.com/engine/installation/) para efetuar a
instalação.

### maven
Para construir a aplicação é necessário fazer o download e instalação do
aplicativo maven, [link](https://maven.apache.org/install.html)

### mongodb
Após instalar o docker, executar o comando abaixo para criar e executar o mongo no docker pela primeira
vez

```shell
docker run -p 27017:27017 --name xy-inc-mongo -d mongo
```
A imagem do docker será baixada na primeira vez que o comando **docker run** for
executado  
Para utilizar um docker já criado anteriormente, basta utilizar o comando abaixo
```shell
docker start xy-inc-mongo
```
O nome **xy-inc-mongo** é o nome do container docker que será criado na sua
máquina

## Recursos disponibilizados
A aplicação disponibiliza uma camada rest para que pode ser consumidas por
aplicações web, mobile.
As operações disponibilizadas são:

- POST:
  [http://localhost:8080/xy-inc/v1/{model-name}](http://localhost:8080/xy-inc/v1/{model-name})
   utilizado para criação de um novo registro para o modelo definido em
  {model-name}
- PUT: [http://localhost:8080/xy-inc/v1/{model-name}/{id}](http://localhost:8080/xy-inc/v1/{model-name}/{id})
   utilizado para atualização de um registro já existente no modelo {model-name}
- DELETE: [http://localhost:8080/xy-inc/v1/{model-name}/{id}](http://localhost:8080/xy-inc/v1/{model-name}/{id})
   utilizado para exclusão de um registro por id
- GET: [http://localhost:8080/xy-inc/v1/{model-name}](http://localhost:8080/xy-inc/v1/{model-name})
   utilizado para realizar uma busca de todos os registros do modelo informado
- GET: [http://localhost:8080/xy-inc/v1/{model-name}/{id}](http://localhost:8080/xy-inc/v1/{model-name}/{id})
   utilizado para realizar uma busca por id no modelo informado

## Utilizando a aplicação
Após a instalação do docker e criação e execução do container com o MongoDB, é
preciso clonar esse repositório de aplicação.  
Dentro da pasta raiz, a aplicação se encontra na subpasta xy-inc, utilize o
comando abaixo para executá-la:
```shell
mvn spring-boot:run
```

Para facilitar os testes da API Rest criei um projeto que pode ser importado
dentro do [PostMan](https://www.getpostman.com/), ela já possui exemplos de
utilização com um modelo chamado *Product*, mas pode ser testado usando outros
modelos conforme desejar.
