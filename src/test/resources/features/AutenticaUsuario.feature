#language: pt
  Funcionalidade: Autenticação de usuario
    Como usuario da api
    Quero ao digitar email e senha me autenticar
    Para que o usuario possa utilizar as demais funcionalidades

    @regressivo
    Cenario: Usuario autenticado
    Dado que eu tenha os seguinte dados do usuario que ira autenticar:
      | campo         | valor                |
      | email         | duhhdm@hotmail.com   |
      | senha         | 1234                 |
    Quando eu enviar a requisição para o endpoint "/auth/login" de autenticação
    Entao o statuscode da resposta deve ser 200
    @regressivo
    Cenario: Usuario excluido
    Dado que o usuario logado com o seguinte email:
      | campo         | valor                |
      | email         | duhhdm@hotmail.com   |
      | senha         | 1234                 |
    Quando eu enviar a requisiçao com o EMAIL para o endpoint "/auth" de deleção de usuario
    Entao o status deve ser 200
