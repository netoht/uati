# Cache Redis

### Executando o cache Redis para desenvolvimento:

Pré-requisitos:

- [Docker Engine instalado](https://docs.docker.com/engine/installation/)
- [Docker Compose instalado](https://docs.docker.com/compose/install/)

```sh
# Iniciando o cache Redis
docker-compose up -d redis

# Aguardando o Redis inicializar,
# Entrando no terminal redis-cli do Redis
$ while ! nc -z localhost 6379 &> /dev/null ; do \
    echo -ne "waiting for Redis..."\\r; sleep 2; done ; \
  docker exec -it redis redis-cli

# Tente executar a listagem de todas as chaves no cache
127.0.0.1:6379> keys *
(empty list or set)
```
