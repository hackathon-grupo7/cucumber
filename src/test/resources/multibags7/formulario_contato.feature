#language: pt

Funcionalidade: Envio de contato
  Como um consumidor do e-commerce
  João deseja entrar em contato com e-commerce
  Para solucionar dúvidas a respeito de produtos

  Cenario: Envio de contato com sucesso

    Dado que João abriu a página de contato
    E os dados informados estão corretos
    Quando ele envia aciona a opção de enviar a mensagem para o e-commerce
    Entao o sistema deve apresentar uma mensagem de envio de contato com sucesso