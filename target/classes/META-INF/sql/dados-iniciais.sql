insert into Perfil (id, descricao) values (1, 'adm');
insert into Perfil (id, descricao) values (2, 'user');

insert into usuario (id, nome, email, cpf, data_cadastro, perfil_id) values (1, 'Pedro Valverde das Cores', 'pedrovdc@gmail.com', '123.123.123-87', '2009-03-02', 1);
insert into usuario (id, nome, email, cpf, data_cadastro, perfil_id) values (2, 'Maria Carla de Andrade', 'mariaca@gmail.com', '123.123.123-87', '2002-06-05', 2);
insert into usuario (id, nome, email, cpf, data_cadastro, perfil_id) values (3, 'Tertuliano Carneiro Bispo', 'tertocb@gmail.com', '123.123.123-87', '2022-06-01', 2);
insert into usuario (id, nome, email, cpf, data_cadastro, perfil_id) values (4, 'Angelina Santana Apardecida da Silva e Silva', 'angelinasantanass@gmail.com', '123.123.123-87', '2021-10-08', 1);

insert into endereco (id, logradouro, bairro, numero, cidade, uf) values (1, 'Rua da Salvação', 'Liberdade', '125', 'São Paulo', 'SP');
insert into endereco (id, logradouro, bairro, numero, cidade, uf) values (2, 'Av Presidente Vargas', 'Centro', '999', 'Salvador', 'BA');

insert into usuario_endereco (usuario_id, endereco_id) values (1, 2);
