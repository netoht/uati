# API fornecedora de dados consolidados

O sistema consulta o cache Redis e retorna para o usuário os dados solicitados.

### Tecnologias utilizadas no projeto:

- NodeJS
- Framework Hapi (web api)
- Ferramenta de build Yarn
- Visual Studio Code
- Cache Redis

### Construindo o projeto:

Pré-requisitos:

- [NodeJS instalado](https://nodejs.org/en/)
- [Yarn instalado](https://yarnpkg.com/en/docs/install)
- [Cache Redis em execução](../docker/Redis.md)

### Construindo o projeto:

```sh
$ yarn --production
```

### Iniciando o projeto:

```sh
$ yarn start
```

### Executando consultas para as perguntas:

```sh
# Quais são os 5 (cinco) usuários, da amostra coletada,
# que possuem mais seguidores?
$ curl -L "http://localhost:8081/top_twitter_profile"

# Considerando apenas as postagens em Português (lang=pt), 
# qual o total para cada uma das #tag solicitadas?
$ curl -L "http://localhost:8081/hashtag_pt"

# Qual o total de postagens, agrupadas por hora do dia 
# (independentemente da #hashtag)?
$ curl -L "http://localhost:8081/tweet_by_hour"
```