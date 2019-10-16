Feature: Cadastrar uma nova conta
  Como um consumidor do e-commerce
  João deseja se cadastrar no e-commerce
  Para realizar compras

  Scenario: Cadastro de nova conta com sucesso

    Given João acessou o menu My Account
    And clicou na opção Register
    Then o sistema deve apresentar o formulário de informações pessoais
    When João preenche os dados corretamente
    Then o sistema deve criar a conta
    And redirecionar João para o dashboard

  Scenario: Falha no cadastro de nova conta

    Given João acessou o menu My Account
    And clicou na opção Register
    Then o sistema deve apresentar o formulário de informações pessoais
    When João preenche os dados incorretamente
    Then o sistema deve apresentar uma mensagem com descrição dos erros
    And identificar os campos inválidos