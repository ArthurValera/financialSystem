package com.financial.system.financial.system.model;

import com.financial.system.financial.system.dto.LancamentoCreateDTO;
import com.financial.system.financial.system.dto.LancamentoUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "lancamentos")

public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    private String observacao;
    private BigDecimal valor;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Lancamento() {
    }

    public Lancamento(LancamentoCreateDTO data, Categoria categoria, Pessoa pessoa) {
        this.descricao = data.descricao();
        this.dataVencimento = data.dataVencimento();
        this.dataPagamento = data.dataPagamento();
        this.tipo = data.tipo();
        this.observacao = data.observacao();
        this.valor = data.valor();
        this.ativo = true;
        this.categoria = categoria;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public String getObservacao() {
        return observacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public boolean getAtivo() {
        return ativo;
    }

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    public Categoria getCategoria() {
        return categoria;
    }

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void update(@Valid LancamentoUpdateDTO data, Pessoa pessoa, Categoria categoria){
        if (data.descriocao() != null) {
            this.descricao = data.descriocao();
        }
        if (data.valor() != null) {
            this.valor = data.valor();
        }
        if (data.dataVencimento() != null) {
            this.dataVencimento = data.dataVencimento();
        }
        if (data.dataPagamento() != null) {
            this.dataPagamento = data.dataPagamento();
        }
        if (data.tipo() != null) {
            this.tipo = data.tipo();
        }
        if (data.observacao() != null) {
            this.observacao = data.observacao();
        }
        if (categoria != null) {
            this.categoria = categoria;
        }
        if (pessoa != null) {
            this.pessoa = pessoa;
        }
    }

    public void delete() {
        this.ativo = false;
    }
}
