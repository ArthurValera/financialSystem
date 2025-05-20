CREATE TABLE lancamentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255),
    data_vencimento DATE,
    data_pagamento DATE,
    tipo VARCHAR(50),
    observacao TEXT,
    valor DECIMAL(10, 2),
    ativo TINYINT(1) NOT NULL,

    categoria_id BIGINT,
    pessoa_id BIGINT,

    CONSTRAINT fk_lancamento_categoria FOREIGN KEY (categoria_id) REFERENCES categorias(id),
    CONSTRAINT fk_lancamento_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoas(id)
) ENGINE=InnoDB;
