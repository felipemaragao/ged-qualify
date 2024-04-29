CREATE TABLE tbl_documento (
	codigo_documento serial,
	nome VARCHAR(50) NOT NULL,
	titulo VARCHAR(50) NOT NULL,
	anexo VARCHAR(200),
	url_anexo VARCHAR(200)
);


CREATE TABLE tbl_anexo_documento (
	codigo_anexo serial,
	codigo_documento integer,
	anexo VARCHAR(200)
);

INSERT INTO tbl_documento (nome, titulo) values ('Lazer', 'Futebol');
INSERT INTO tbl_documento (nome, titulo) values ('Alimentação','Bolo');
INSERT INTO tbl_documento (nome, titulo) values ('Supermercado','Éxtra');
INSERT INTO tbl_documento (nome, titulo) values ('Farmácia','Drogasil');
INSERT INTO tbl_documento (nome, titulo) values ('Outros','Qualquer');