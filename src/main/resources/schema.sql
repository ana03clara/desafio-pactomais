CREATE TABLE IF NOT EXISTS correntista (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    documento VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS conta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(255),
    saldo DOUBLE,
    correntista_id BIGINT,
    CONSTRAINT fk_conta_correntista
    FOREIGN KEY (correntista_id)
    REFERENCES correntista(id)
    );

CREATE TABLE IF NOT EXISTS conta_corrente (
    id BIGINT PRIMARY KEY,
    limite DOUBLE,
    CONSTRAINT fk_conta_corrente_conta
    FOREIGN KEY (id)
    REFERENCES conta(id)
    );

CREATE TABLE IF NOT EXISTS conta_poupanca (
    id BIGINT PRIMARY KEY,
    CONSTRAINT fk_conta_poupanca_conta
    FOREIGN KEY (id)
    REFERENCES conta(id)
    );

CREATE TABLE IF NOT EXISTS transacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(255),
    valor DOUBLE,
    data DATETIME,
    conta_id BIGINT,
    CONSTRAINT fk_transacao_conta
    FOREIGN KEY (conta_id)
    REFERENCES conta(id)
    );