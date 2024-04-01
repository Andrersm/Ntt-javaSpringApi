
# API de Catálogo de Filmes

Esta API de Catálogo de Filmes é parte dos desafios propostos pela Academia da NTT Data de CX. Ela permite aos usuários consultar informações detalhadas sobre filmes, atores, diretores, entre outros. Atualmente, a API suporta operações de busca por nome de filme, mas futuras atualizações expandirão suas funcionalidades para incluir mais opções de busca e manipulação de dados.

## Tecnologias Utilizadas

- Spring Framework
- Spring Security com autenticação via Token JWT
- Docker para fácil distribuição e implantação

## Pré-requisitos

Para executar este projeto, você precisará ter o Docker instalado em sua máquina.

## Como Executar

1. Clone o repositório do projeto:
```
git clone https://github.com/Andrersm/Ntt-javaSpringApi.git
```
2. Navegue até o diretório do projeto clonado e execute:
```
docker-compose up --build
```
Isso iniciará a aplicação na porta 8080 e o banco de dados com dados iniciais para exploração.

## Uso

A API pode ser acessada e testada através do Swagger UI, disponível em:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Aqui, você encontrará detalhes sobre todos os endpoints disponíveis, bem como informações sobre os parâmetros de requisição e os formatos de resposta esperados.

## Testes

O projeto inclui alguns testes automatizados. A cobertura de testes será expandida em futuras atualizações.

## Contribuindo

Contribuições são bem-vindas! Sinta-se livre para abrir pull requests ou issues no GitHub para sugerir melhorias ou adicionar novas funcionalidades.

## Futuras Melhorias

- Expansão das opções de busca e manipulação de dados.
- Cobertura completa de testes.

