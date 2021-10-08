create table cliente (
       id  serial not null,
        cpf varchar(11) not null,
        data_atualizacao date,
        data_cadastro date,
        nome varchar(150) not null,
        status boolean,
        primary key (id)
    )
 
    create table criticidade (
       id  serial not null,
        nome varchar(150) not null,
        status boolean not null,
        primary key (id)
    )
 
    create table equipamento (
       id  serial not null,
        data_atualizacao date,
        data_cadastro date,
        defeito boolean not null,
        descricao varchar(255),
        localizacao varchar(255) not null,
        nome varchar(200) not null,
        numero_patrimonio varchar(20) not null,
        numero_tag varchar(50) not null,
        operacional boolean not null,
        status boolean not null,
        id_fabricante int4,
        id_tipo_equipamento int4,
        primary key (id)
    )
 
    create table fabricante (
       id  serial not null,
        bairro varchar(255),
        cep varchar(255),
        cidade varchar(255),
        cnpj varchar(255),
        complemento varchar(255),
        data_atualizacao date not null,
        data_cadastro date not null,
        email varchar(255),
        email_responsavel varchar(150) not null,
        endereco varchar(255),
        estado varchar(255),
        nome_fantasia varchar(150) not null,
        nome_responsavel varchar(150) not null,
        numero varchar(255),
        razao_social varchar(150) not null,
        status boolean not null,
        telefone varchar(255),
        telefone_responsavel varchar(150) not null,
        uf varchar(255),
        primary key (id)
    )
 
    create table historico_equipamento (
       id  serial not null,
        data_atualizacao date,
        data_cadastro date,
        nome varchar(150) not null,
        status boolean,
        primary key (id)
    )
 
    create table historico_manutencao (
       id  serial not null,
        data_atualizacao date,
        data_cadastro date,
        nome varchar(150) not null,
        numero varchar(11) not null,
        status boolean,
        primary key (id)
    )
 
    create table manutencao (
       id  serial not null,
        data_atualizacao date,
        data_cadastro date,
        data_manutencao date,
        data_proxima_manutencao date not null,
        data_retorno_pause date,
        data_ultima_manutencao date,
        descricao_manutencao varchar(2000) not null,
        dias_finalizacao int4,
        dias_totais int4,
        nome_manutenedor varchar(150) not null,
        nome_operador varchar(150) not null,
        pause_manutencao boolean not null,
        status boolean not null,
        titulo_manutencao varchar(150) not null,
        id_criticidade int4 not null,
        id_equipamento int4 not null,
        id_tipo_manutencao int4 not null,
        primary key (id)
    )
 
    create table perfil (
       id  serial not null,
        cpf varchar(11) not null,
        data_atualizacao date,
        data_cadastro date,
        nome varchar(150) not null,
        senha varchar(255),
        status boolean,
        login varchar(255),
        primary key (id)
    )
 
    create table servico_prestado (
       id  serial not null,
        data date,
        descricao varchar(150) not null,
        valor numeric(19, 2),
        id_cliente int4,
        primary key (id)
    )
 
    create table tipo_equipamento (
       id  serial not null,
        descricao varchar(255),
        nome varchar(255),
        status boolean,
        primary key (id)
    )
 
    create table tipo_manutencao (
       id  serial not null,
        descricao varchar(255),
        nome varchar(255),
        status boolean,
        primary key (id)
    )
 
    create table usuario (
       id  serial not null,
        cpf varchar(11) not null,
        data_atualizacao date,
        data_cadastro date,
        email varchar(150) not null,
        nome_usuario varchar(150) not null,
        senha varchar(255),
        status boolean,
        login varchar(255),
        primary key (id)
    )
 
    alter table perfil 
       drop constraint if exists UK_fhj34xqt5atrdl2qxvsxivb7h
 
    alter table perfil 
       add constraint UK_fhj34xqt5atrdl2qxvsxivb7h unique (login)
 
    alter table usuario 
       drop constraint if exists UK_pm3f4m4fqv89oeeeac4tbe2f4
 
    alter table usuario 
       add constraint UK_pm3f4m4fqv89oeeeac4tbe2f4 unique (login)
 
    alter table equipamento 
       add constraint FKkiis1nlqivo1vtgvl4hif7ebb 
       foreign key (id_fabricante) 
       references fabricante
 
    alter table equipamento 
       add constraint FKskkm62i0a1xbivoftn96sys9l 
       foreign key (id_tipo_equipamento) 
       references tipo_equipamento
 
    alter table manutencao 
       add constraint FK18s58ni2qpxhd431hl1vgow7e 
       foreign key (id_criticidade) 
       references criticidade
 
    alter table manutencao 
       add constraint FKt5hvsncev53tp12sdk3o0a34w 
       foreign key (id_equipamento) 
       references equipamento
 
    alter table manutencao 
       add constraint FKtjesp8hm9ae00d5iq0t2p0apr 
       foreign key (id_tipo_manutencao) 
       references tipo_manutencao
 
    alter table servico_prestado 
       add constraint FKesbc6v6g25jp341vhqu1r42qi 
       foreign key (id_cliente) 
       references cliente