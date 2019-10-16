#language: pt

Funcionalidade: Adicionar Item Carrinho
  Como um consumidor do e-commerce
  João deseja adicionar um produto ao carrinho
  Para continuar comprando

  Cenario: Adicionar item ao carrinho

    Dado que Joao esta na tela de bolsas
    Quando Joao adiciona uma bolsa ao carrinho
    Entao o carrinho deve ter 1 item
