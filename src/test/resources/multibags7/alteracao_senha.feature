Feature: Alteração de senha
  Como um usuário do E-Commerce
  Laerth deseja alterar a senha de sua conta

  Scenario: Senha alterada com sucesso

    Given Sabrina está autenticado em sua conta
    And está na página de troca de senhas
    When ela preenche o formulário de alteração de senhas com dados válidos
    Then o sistema deve apresentar uma mensagem de senha alterada com sucesso
    And a senha deve estar alterada
