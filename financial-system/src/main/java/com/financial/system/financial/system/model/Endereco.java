package com.financial.system.financial.system.model;

import com.financial.system.financial.system.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    @NotNull
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoDTO data) {

        this.logradouro = data.logradouro();

        this.bairro = data.bairro();

        this.cep = data.cep();

        this.uf = data.uf();

        this.cidade = data.cidade();

        this.numero = data.numero();

        this.complemento = data.complemento();
    }

    public void atualiza(EnderecoDTO data) {
        if (data.logradouro() != null){
            this.logradouro = data.logradouro();
        }if (data.bairro() != null){
            this.bairro = data.bairro();
        }if (data.cep() != null){
            this.cep = data.cep();
        }if (data.uf() != null){
            this.uf = data.uf();
        }if (data.cidade() != null){
            this.cidade = data.cidade();
        }if (data.numero() != null){
            this.numero = data.numero();
        }if (data.complemento() != null){
            this.complemento = data.complemento();
        }
    }
}
