package com.gereciador.estabelecimento.mapper;

import com.gereciador.estabelecimento.controllers.dto.request.ItemPedidoRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.ItemPedidoResponseDTO;
import com.gereciador.estabelecimento.entities.ItemPedido;
import com.gereciador.estabelecimento.entities.Produto;
import com.gereciador.estabelecimento.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ItemPedidoMapper implements Mapper<List<ItemPedidoResponseDTO>, List<ItemPedidoRequestDTO>, List<ItemPedido>>{

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<ItemPedido> toEntity(List<ItemPedidoRequestDTO> dtoRequest) {
        List<Long> idsProdutos = dtoRequest.stream()
                .map(ItemPedidoRequestDTO::produtoId)
                .toList();

        List<Produto> produtos = this.produtoRepository.findAllById(idsProdutos);


        List<ItemPedido> itensPedido = produtos.stream()
                .map(produto -> {
                    ItemPedidoRequestDTO dtoCorrespondente = dtoRequest.stream()
                            .filter(dto -> dto.produtoId().equals(produto.getId()))
                            .findFirst()
                            .orElse(null);

                    if (dtoCorrespondente != null) {
                        ItemPedido itemPedido = new ItemPedido();
                        itemPedido.setProduto(produto);
                        itemPedido.setQuantidade(dtoCorrespondente.quantidade());
                        return itemPedido;
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        return itensPedido;
    }

    @Override
    public List<ItemPedidoResponseDTO> toDTO(List<ItemPedido> entities){
        return entities.stream().map(itemPedido -> new ItemPedidoResponseDTO(itemPedido.getId(), itemPedido.getProduto().getNome() ,itemPedido.getQuantidade())).toList();
    }
}
