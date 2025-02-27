package com.gereciador.estabelecimento.mapper;

import com.gereciador.estabelecimento.controllers.dto.request.PedidoRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.PedidoResponseDTO;
import com.gereciador.estabelecimento.controllers.dto.response.ProdutoResponseDTO;
import com.gereciador.estabelecimento.entities.Pedido;
import com.gereciador.estabelecimento.entities.Produto;
import com.gereciador.estabelecimento.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper implements Mapper<PedidoResponseDTO, PedidoRequestDTO, Pedido>{

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoMapper produtoMapper;

    @Override
    public Pedido toEntity(PedidoRequestDTO dtoRequest) {
        List<Produto> produtos =  this.produtoRepository.findAllById(dtoRequest.idProdutos());
        Pedido pedido = new Pedido();
        pedido.setProdutos(produtos);
        return pedido;
    }

    @Override
    public PedidoResponseDTO toDTO(Pedido entity) {
        List<ProdutoResponseDTO> produtoResponseDTOS = entity.getProdutos().stream().map(this.produtoMapper::toDTO).toList();
        List<Long> produtosID = produtoResponseDTOS.stream().map(ProdutoResponseDTO::id).toList();
        return new PedidoResponseDTO(entity.getId(),produtosID , entity.getData(), entity.getStatusPedido(), entity.getPagamento());
    }
    
}
