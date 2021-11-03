create table cliente (id serial not null,
cpf varchar(11) not null,
data_atualizacao date,
data_cadastro date,
nome varchar(150) not null,
status boolean,
id_user int8,
primary key (id));

create table criticidade (id serial not null,
nome varchar(150) not null,
status boolean not null,
primary key (id));

create table equipamento (id serial not null,
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
id_user int8,
primary key (id));

create table fabricante (id serial not null,
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
primary key (id));

create table historico_equipamento (id serial not null,
data_atualizacao date,
data_cadastro date,
nome varchar(150) not null,
status boolean,
id_user int8,
primary key (id));

create table historico_manutencao (id serial not null,
data_atualizacao date,
data_cadastro date,
nome varchar(150) not null,
numero varchar(11) not null,
status boolean,
id_user int8,
primary key (id));

create table manutencao (id serial not null,
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
id_user int8,
primary key (id));

create table refresh_token (id int8 not null,
expiry_dt timestamp not null,
refresh_count int8,
token varchar(255) not null,
user_device_id int8 not null,
primary key (id));

create table roles (id bigserial not null,
name varchar(60),
primary key (id));

create table servico_prestado (id serial not null,
data date,
data_atualizacao date,
data_cadastro date,
descricao varchar(150) not null,
preco varchar(255),
status boolean not null,
id_cliente int4,
id_user int8,
primary key (id));

create table tipo_equipamento (id serial not null,
descricao varchar(255),
nome varchar(255),
status boolean,
primary key (id));

create table tipo_manutencao (id serial not null,
descricao varchar(255),
nome varchar(255),
status boolean,
primary key (id));

create table user_roles (user_id int8 not null,
role_id int8 not null,
primary key (user_id,
role_id));

create table user_device (id int8 not null,
device_id varchar(255) not null,
device_type varchar(255),
is_refresh_active boolean,
user_id int8 not null,
primary key (id));

create table users (id int8 not null,
active boolean not null,
cpf varchar(11),
data_atualizacao date,
data_cadastro date,
email varchar(255),
last_name varchar(255),
name varchar(255),
password varchar(255) not null,
primary key (id));

alter table refresh_token drop constraint UK_8ogx3ejsbfbf2xsgl4758otrm;

alter table refresh_token add constraint UK_8ogx3ejsbfbf2xsgl4758otrm unique (user_device_id);

alter table refresh_token drop constraint UK_r4k4edos30bx9neoq81mdvwph;

alter table refresh_token add constraint UK_r4k4edos30bx9neoq81mdvwph unique (token);

alter table users drop constraint UK6dotkott2kjsp8vw4d0m25fb7;

alter table users add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email);

alter table users drop constraint UK_7kqluf7wl0oxs7n90fpya03ss;

alter table users add constraint UK_7kqluf7wl0oxs7n90fpya03ss unique (cpf);

create sequence refresh_token_seq start 1 increment 1;

create sequence user_device_seq start 1 increment 1;

create sequence user_seq start 1 increment 50;

alter table cliente add constraint FKs3ilie2cb4jwyaohl61ida3q9 foreign key (id_user) references users;

alter table equipamento add constraint FKkiis1nlqivo1vtgvl4hif7ebb foreign key (id_fabricante) references fabricante;

alter table equipamento add constraint FKskkm62i0a1xbivoftn96sys9l foreign key (id_tipo_equipamento) references tipo_equipamento;

alter table equipamento add constraint FKh7tighqxdcnks532gajb027lh foreign key (id_user) references users;

alter table historico_equipamento add constraint FKkah9lsgpjp239k7na16jbd3wf foreign key (id_user) references users;

alter table historico_manutencao add constraint FKfrkh9wbyxbsm8jy72x57mggnl foreign key (id_user) references users;

alter table manutencao add constraint FK18s58ni2qpxhd431hl1vgow7e foreign key (id_criticidade) references criticidade;

alter table manutencao add constraint FKt5hvsncev53tp12sdk3o0a34w foreign key (id_equipamento) references equipamento;

alter table manutencao add constraint FKtjesp8hm9ae00d5iq0t2p0apr foreign key (id_tipo_manutencao) references tipo_manutencao;

alter table manutencao add constraint FK6iudx9t6xb8bs5gk62pok0ngl foreign key (id_user) references users;

alter table refresh_token add constraint FKr92opronarwe7pn1c41621grv foreign key (user_device_id) references user_device;

alter table servico_prestado add constraint FKesbc6v6g25jp341vhqu1r42qi foreign key (id_cliente) references cliente;

alter table servico_prestado add constraint FK1726dj7wcc2ayghtgn1gpx7a9 foreign key (id_user) references users;

alter table user_roles add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 foreign key (role_id) references roles;

alter table user_roles add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users;

alter table user_device add constraint FKnnbvbh186cbm7wqvp89ffsfqg foreign key (user_id) references users;