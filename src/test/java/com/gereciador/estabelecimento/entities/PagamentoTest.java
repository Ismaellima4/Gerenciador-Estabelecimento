package com.gereciador.estabelecimento.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoTest {

    @Test
    void calcValorFinal() {
        Produto produto = new Produto();
        produto.setPreco(BigDecimal.valueOf(12));
        produto.setQuantidade(12);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);

        Pedido pedido = new Pedido();
        pedido.setItensPedido(List.of(itemPedido));

        itemPedido.setPedido(pedido);

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.calcValorFinal();

        assertEquals(pagamento.getValor(), BigDecimal.valueOf(12));
    }
}