# Banco de dados Cassandra

### Executando o banco de dados Cassandra para desenvolvimento:

Pré-requisitos:

- [Docker Engine instalado](https://docs.docker.com/engine/installation/)
- [Docker Compose instalado](https://docs.docker.com/compose/install/)

```sh
# Iniciando o banco de dados Cassandra
$ docker-compose up -d cassandra

# Aguardando o Cassandra inicializar,
# Executando o script.cql para criação do modelo de dados
# Entrando no terminal cqlsh do Cassandra
$ while ! docker exec -it cassandra cqlsh -f /script.cql &> /dev/null ; do \
    echo -ne 'waiting for Cassandra...'\\r; sleep 2; done ; \
  docker exec -it cassandra cqlsh

Connected to Test Cluster at 127.0.0.1:9042.
[cqlsh 5.0.1 | Cassandra 3.9 | CQL spec 3.4.2 | Native protocol v4]
Use HELP for help.
cqlsh>

# Tente executar um select nos dados de Tweet
cqlsh> select * from ks_twitter.tweet ;

 language_code | hashtag | created_at | hour_of_day | twitter_profile
---------------+---------+------------+-------------+-----------------

(0 rows)
cqlsh>
```
