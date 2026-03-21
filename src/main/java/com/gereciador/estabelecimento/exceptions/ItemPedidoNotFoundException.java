package com.gereciador.estabelecimento.exceptions;

public class ItemPedidoNotFoundException extends RuntimeException{
    public ItemPedidoNotFoundException() {
    super("Item do pedido não encontrado");
  }

    public ItemPedidoNotFoundException(String message) {
    super(message);
  }
}
