package com.gereciador.estabelecimento.services;

import com.gereciador.estabelecimento.controllers.dto.request.ItemPedidoRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.ItemPedidoResponseDTO;
import com.gereciador.estabelecimento.entities.ItemPedido;
import com.gereciador.estabelecimento.exceptions.NotFoundException;
import com.gereciador.estabelecimento.mapper.ItemPedidoMapper;
import com.gereciador.estabelecimento.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private ItemPedidoMapper itemPedidoMapper;

    public ItemPedidoResponseDTO save(ItemPedidoRequestDTO obj) throws NotFoundException {
        ItemPedido itemPedido = this.itemPedidoRepository.save(this.itemPedidoMapper.toEntity(List.of(obj)).getFirst());
        return this.itemPedidoMapper.toDTO(List.of(itemPedido)).getFirst();
    }

    public ItemPedidoResponseDTO update(ItemPedidoRequestDTO obj) throws NotFoundException {
        ItemPedido itemPedido = this.itemPedidoRepository.findItemByProdutoId(obj.produtoId()).orElseThrow(() -> new NotFoundException("Item Pedido não encontrado com o id do produto" + obj.produtoId()));

        if (obj.quantidade() != null) itemPedido.setQuantidade(obj.quantidade());


        ItemPedido itemPedidoSave = this.itemPedidoRepository.save(itemPedido);
        return this.itemPedidoMapper.toDTO(List.of(itemPedidoSave)).getFirst();
    }

    public void delete(Long primaryKey) throws NotFoundException {
        this.itemPedidoRepository.deleteById(primaryKey);
    }

}
