package com.gereciador.estabelecimento.mapper;

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
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private PedidoMapper pedidoMapper;


    @Override
    public Pagamento toEntity(PagamentoRequestDTO dtoRequest) {
        Pagamento pagamento =  new Pagamento();
        pagamento.setPedido(pedidoRepository.findById(dtoRequest.idPedido()).orElseThrow());
        pagamento.setCliente(clienteRepository.findById(dtoRequest.idCliente()).orElseThrow());
        pagamento.setData(dtoRequest.data());
        pagamento.setStatusPagamento(dtoRequest.status());
        pagamento.setTipoPagamento(dtoRequest.tipoPagamento());
        pagamento.setValor(dtoRequest.valor());
        return pagamento;
    }

    @Override
    public PagamentoResponseDTO toDTO(Pagamento entity) {
        ClienteResponseDTO clienteDTO = clienteMapper.toDTO(entity.getCliente());
        PedidoResponseDTO pedidoDTO = pedidoMapper.toDTO(entity.getPedido());
        return new PagamentoResponseDTO(pedidoDTO, entity.getValor(), entity.getData(), clienteDTO, entity.getTipoPagamento(), entity.getStatusPagamento());
    }
    
}
