# xy-inc

## Configurações

### docker

#### mongodb
executar o comando abaixo para criar e executar o mongo no docker pela primeira
vez

```shell
docker run -p 27017:27017 --name xy-inc-mongo -d mongo
```

da segunda inicialização em diante, utilize o comando abaixo
```shell
docker start xy-inc-mongo
```


