package com.gereciador.estabelecimento.exceptions;

public class PedidoNotFoundException extends RuntimeException {
  public PedidoNotFoundException() {
    super("Pedido não encontrado");
  }
}
