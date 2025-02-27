package com.gereciador.estabelecimento.mapper;

import com.gereciador.estabelecimento.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gereciador.estabelecimento.controllers.dto.request.PagamentoRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.ClienteResponseDTO;
import com.gereciador.estabelecimento.controllers.dto.response.PagamentoResponseDTO;
import com.gereciador.estabelecimento.controllers.dto.response.PedidoResponseDTO;
import com.gereciador.estabelecimento.entities.Pagamento;
import com.gereciador.estabelecimento.repositories.ClienteRepository;
import com.gereciador.estabelecimento.repositories.PedidoRepository;

@Component
public class PagamentoMapper implements Mapper<PagamentoResponseDTO, PagamentoRequestDTO, Pagamento> {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final ClienteMapper clienteMapper;
    private final PedidoMapper pedidoMapper;

    @Autowired
    public PagamentoMapper(ClienteRepository clienteRepository, PedidoRepository pedidoRepository, ClienteMapper clienteMapper, PedidoMapper pedidoMapper) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.clienteMapper = clienteMapper;
        this.pedidoMapper = pedidoMapper;
    }


    @Override
    public Pagamento toEntity(PagamentoRequestDTO dtoRequest) throws NotFoundException {
        Pagamento pagamento =  new Pagamento();
        pagamento.setPedido(this.pedidoRepository.findById(dtoRequest.idPedido()).orElseThrow(() -> new NotFoundException("Pedido com ID" + dtoRequest.idPedido())));
        pagamento.setCliente(this.clienteRepository.findById(dtoRequest.idCliente()).orElse(null));
        pagamento.setStatusPagamento(dtoRequest.status());
        return pagamento;
    }

    @Override
    public PagamentoResponseDTO toDTO(Pagamento entity) {
        ClienteResponseDTO clienteDTO = this.clienteMapper.toDTO(entity.getCliente());
        PedidoResponseDTO pedidoDTO = this.pedidoMapper.toDTO(entity.getPedido());
        return new PagamentoResponseDTO(pedidoDTO.id(), entity.getValor(), entity.getData(), clienteDTO.id(), entity.getTipoPagamento(), entity.getStatusPagamento());
    }
    
}
