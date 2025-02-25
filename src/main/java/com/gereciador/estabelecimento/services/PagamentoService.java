package com.gereciador.estabelecimento.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gereciador.estabelecimento.controllers.dto.request.PagamentoRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.PagamentoResponseDTO;
import com.gereciador.estabelecimento.entities.Pagamento;
import com.gereciador.estabelecimento.mapper.PagamentoMapper;
import com.gereciador.estabelecimento.repositories.PagamentoRepository;

@org.springframework.stereotype.Service
public class PagamentoService implements Service<PagamentoResponseDTO, PagamentoRequestDTO, Long>{

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper mapper= new PagamentoMapper();
    
    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    @Transactional
    public PagamentoResponseDTO save(PagamentoRequestDTO obj) {
        Pagamento pagamento = this.pagamentoRepository.save(this.mapper.toEntity(obj));
        return this.mapper.toDTO(pagamento);
    }

    @Override
    public PagamentoResponseDTO update(Long primaryKey, PagamentoRequestDTO obj) {
        Pagamento pagamentoRequest = this.mapper.toEntity(obj);
        Pagamento pagamento = this.pagamentoRepository.findById(primaryKey).orElseThrow();

        if(pagamentoRequest.getCliente() != null){pagamento.setCliente(pagamentoRequest.getCliente());}
        if(pagamentoRequest.getData() != null){pagamento.setData(pagamentoRequest.getData());}
        if(pagamentoRequest.getPedido() != null){pagamento.setPedido(pagamentoRequest.getPedido());}
        if(pagamentoRequest.getValor() != null){pagamento.setValor(pagamentoRequest.getValor());}
        if(pagamentoRequest.getTipoPagamento() != null){pagamento.setTipoPagamento(pagamentoRequest.getTipoPagamento());}
        if(pagamentoRequest.getStatusPagamento() != null){pagamento.setStatusPagamento(pagamentoRequest.getStatusPagamento());}

        Pagamento pagamentoSaved = this.pagamentoRepository.save(pagamento);
        return this.mapper.toDTO(pagamento); 
    }

    @Override
    @Transactional
    public void delete(Long primaryKey) {
       this.pagamentoRepository.deleteById(primaryKey);
    }

    @Override
    public PagamentoResponseDTO getById(Long primaryKey) {
        Pagamento pagamento = this.pagamentoRepository.findById(primaryKey).orElseThrow();
        return this.mapper.toDTO(pagamento);
    }

    @Override
    public List<PagamentoResponseDTO> getAll() {
       List<Pagamento> pagamento = this.pagamentoRepository.findAll();
       return pagamento.stream().map(this.mapper::toDTO).toList();
    }
    
}
