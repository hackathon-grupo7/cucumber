Feature: Login

  Scenario: Login com Sucesso

    Given João está na tela de Login
    And os dados de login informados estão corretos
    When ele aciona ação de sign in no e-commerce
    Then o sistema deve apresentar a tela de dashboard do cliente

  Scenario: Falha no Login

    Given João está na tela de Login
    And os dados informados estão incorretos
    When ele aciona ação de sign in no e-commerce
    Then o sistema deve apresentar uma mensagem de erro