create sequence eeportal.seq_usuario
start with 1
increment by 1
maxvalue 9999999999
no cycle;

create sequence eeportal.seq_evento
start with 1
increment by 1
maxvalue 999
no cycle;

create sequence eeportal.seq_agenda
start with 1
increment by 1
maxvalue 9999999999
no cycle;

create sequence eeportal.seq_convite
start with 1
increment by 1
maxvalue 9999999999
no cycle;


create table eeportal.usuario(
	id_usuario bigint default nextval('seq_usuario') not null,
	nome_completo varchar(100) not null,
	apelido varchar(20),
	email varchar(60),
	senha varchar(64)
);
alter table eeportal.usuario
	add constraint pk_usuario primary key (id_usuario),
	add constraint uq_usuario unique (email)
;

create table eeportal.evento(
	id_evento int default nextval('seq_evento') not null,
	usuario_inclusao bigint not null,
	nome_evento varchar(100) not null,
	data_evento date not null,
	hora_evento time not null,
	endereco_evento varchar(200) not null
);
alter table eeportal.evento
	add constraint pk_evento primary key (id_evento),
	add constraint fk_evento_usuario foreign key (usuario_inclusao) references eeportal.usuario (id_usuario)
;

create table eeportal.agenda(
	id_agenda bigint default nextval('seq_agenda') not null,
	id_usuario bigint not null,
	id_evento int not null
);
alter table eeportal.agenda
	add constraint pk_agenda primary key (id_agenda),
	add constraint fk_agenda_usuario foreign key (id_usuario) references eeportal.usuario (id_usuario),
	add constraint fk_agenda_evento foreign key (id_evento) references eeportal.evento (id_evento),
	add constraint uq_agenda unique (id_usuario, id_evento)
;

create table eeportal.convite(
	id_convite bigint default nextval('seq_convite') not null,
	id_anfitriao bigint not null,
	id_convidado bigint not null,
	id_evento int not null,
	data_convite timestamp default current_timestamp,
	status_convite smallint default 0 not null -- 0 => sem resposta; 1 => aceito; 2 => recusado
);
alter table eeportal.convite
	add constraint pk_convite primary key (id_convite),
	add constraint fk_convite_anfitriao foreign key (id_anfitriao) references eeportal.usuario (id_usuario),
	add constraint fk_convite_convidado foreign key (id_convidado) references eeportal.usuario (id_usuario),
	add constraint fk_convite_evento foreign key (id_evento) references eeportal.evento (id_evento),
	add constraint uq_convite unique (id_evento, id_anfitriao, id_convidado)
;