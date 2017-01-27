# Coletor de mensagens do Twitter

O sistema coleta os dados do Twitter e salva-os no banco de dados Cassandra.

### Tecnologias utilizadas no projeto:

- Linguagem Java
- Framework Spring Boot (web, social-twitter)
- Ferramenta de build Gradle
- Intellij IDEA
- Banco de dados Cassandra

### Construindo o projeto:

```sh
$ ./gradlew clean bootRepackage
```

### Iniciando o projeto:

Pré-requisitos:

- [Banco de dados Cassandra em execução](../docker/Cassandra.md)

```sh
$ java -jar build/libs/uati-collector-*.jar
```

### Aguardando o coletor inicializar

```sh
$ while ! curl -s "http://localhost:8080" &> /dev/null; do echo -ne "waiting for collector..."\\r; sleep 2; done
```

### Coletando dados de uma determinada #hashtag:

```sh
$ curl -L "http://localhost:8080/collect?hashtag=[sua_hashtag]"
```

### Coletando todas as hashtags do problema

```sh
$ ./collect-hashtags.sh
```