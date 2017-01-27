# Agregador de mensagens do Twitter

O sistema coleta os dados do banco de dados Cassandra, faz agregações e salva-os no cache Redis.

### Tecnologias utilizadas no projeto:

- Linguagem Scala
- Spark Hadoop
- Ferramenta de build Gradle
- Intellij IDEA
- Banco de dados Cassandra
- Cache Redis

### Construindo o projeto:

```sh
$ ./gradlew shadowJar
```

### Agregando dados coletados:

Pré-requisitos:

- [Banco de dados Cassandra em execução](../docker/Cassandra.md)
- [Cache Redis em execução](../docker/Redis.md)

```sh
$ ../spark-hadoop/spark-2.1.0-bin-hadoop2.7/bin/spark-submit --class io.github.netoht.uati.aggregator.UatiAggregatorApplication build/libs/uati-aggregator*.jar
```
