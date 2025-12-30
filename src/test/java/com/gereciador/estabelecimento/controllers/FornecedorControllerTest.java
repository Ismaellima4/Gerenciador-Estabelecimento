package com.gereciador.estabelecimento.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gereciador.estabelecimento.annotations.WebMvcTestWithoutSecurity;
import com.gereciador.estabelecimento.controllers.dto.request.FornecedorRequestDTO;
import com.gereciador.estabelecimento.controllers.dto.response.FornecedorResponseDTO;
import com.gereciador.estabelecimento.services.FornecedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTestWithoutSecurity(controllers = { FornecedorController.class })
class FornecedorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private FornecedorService fornecedorService;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldCreateFornecedorSuccessfully() throws Exception {
        FornecedorRequestDTO payload = new FornecedorRequestDTO(
                "Test",
                "000.000.000.00",
                List.of()
        );

        FornecedorResponseDTO response = new FornecedorResponseDTO(
                1L,
                payload.nome(),
                payload.cnpj(),
                payload.contatos()
        );

        when(fornecedorService.save(payload)).thenReturn(response);

        mockMvc.perform(post("/fornecedores")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(payload)))

                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.nome").value(response.nome()))
                .andExpect(jsonPath("$.cnpj").value(response.cnpj()))
                .andExpect(jsonPath("$.contatos").isEmpty());
    }

    @Test
    void shouldFindByIdFornecedorSuccessfully() throws Exception{
        Long id = 1L;

        FornecedorResponseDTO response = new FornecedorResponseDTO(
                id,
                "teste",
                "000.000.000",
                List.of()
        );

        when(fornecedorService.getById(id)).thenReturn(response);

        mockMvc.perform(get("/fornecedores/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.nome").value(response.nome()))
                .andExpect(jsonPath("$.cnpj").value(response.cnpj()))
                .andExpect(jsonPath("$.contatos").isEmpty());
    }

    @Test
    void shouldFindAllFornecedorSuccessfully() throws Exception {
        FornecedorResponseDTO dto = new FornecedorResponseDTO(
                1L,
                "test1",
                "000.000.000",
                List.of()
        );
        FornecedorResponseDTO dto2 = new FornecedorResponseDTO(
                1L,
                "test2",
                "111.111.111",
                List.of()
        );

        List<FornecedorResponseDTO> dtos = Arrays.asList(dto,dto2);

        when(fornecedorService.getAll()).thenReturn(dtos);

        mockMvc.perform(get("/fornecedores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(dtos.size()))
                .andExpect(jsonPath("$[0].id").value(dto.id()))
                .andExpect(jsonPath("$[0].nome").value(dto.nome()))
                .andExpect(jsonPath("$[0].cnpj").value(dto.cnpj()))
                .andExpect(jsonPath("$[0].contatos").isEmpty())

                .andExpect(jsonPath("$[1].id").value(dto2.id()))
                .andExpect(jsonPath("$[1].nome").value(dto2.nome()))
                .andExpect(jsonPath("$[1].cnpj").value(dto2.cnpj()))
                .andExpect(jsonPath("$[1].contatos").isEmpty());
    }

    @Test
    void shouldDeleteFornecedorSuccessfully() throws Exception{
        Long id = 1L;

        mockMvc.perform(delete("/fornecedores/{}id", id))
                .andExpect(status().isNoContent());

        verify(fornecedorService, times(1)).delete(id);
    }

    @Test
    void shouldUpdateFornecedorSuccessfully() throws Exception {

        FornecedorRequestDTO update = new FornecedorRequestDTO(
                "test",
                "0000.000.000",
                List.of("900000000")
        );

        FornecedorResponseDTO response = new FornecedorResponseDTO(
                1L,
                update.nome(),
                update.cnpj(),
                update.contatos()
        );

        when(fornecedorService.update(response.id(),update)).thenReturn(response);

        mockMvc.perform(patch("/fornecedores/{id}", response.id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))

                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.nome").value(response.nome()))
                .andExpect(jsonPath("$.cnpj").value(response.cnpj()))
                .andExpect(jsonPath("$.contatos").value(response.contatos().getFirst()));


    }
}
