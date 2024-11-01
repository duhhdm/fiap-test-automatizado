# language: pt
  Funcionalidade: Cadastro de novo usuario
    Como usuario da API
  Quero cadastrar um novo usuario
  Para que o usuario possa logar na aplicação

    @padrao
    Cenario: Cadastro bem-sucedido de usuario
    Dado que eu tenha os seguintes dados do usuario:
    | campo         | valor              |
    | nome          | Eduardo            |
    | email         | duhhdm@hotmail.com |
    | senha         | 1234               |
    | role          | ADMIN              |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuario
    Entao o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "cadastro-bem-sucedido-de-usuario.json"
    Entao a resposta da requisição deve estar em conformidade com o contrato selecionado

    @padrao
    Cenario: Cadastro com erro de usuario
      Dado que eu tenha os seguintes dados do usuario:
        | campo         | valor              |
        | nome          | Eduardo            |
        | email         | ""                 |
        | senha         | 1234               |
        | role          | ADMIN              |
      Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuario
      Entao o status code da resposta deve ser 400
      E a resposta deve receber o campo email com a mensagem "must be a well-formed email address"



