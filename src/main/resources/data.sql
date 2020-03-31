insert into roles(id, name) values ( ROLE_SEQ.nextval, 'ROLE_SUPERADMIN' );

insert into users(id, user_name, email, password) values(USERS_SEQ.nextval, 'tomjop', 'tomjop@test.pl', '$2a$12$SfvYyytEl3s/0Lo.wiSjD.sK/hwQrZSoZVMPjuClMo8MguQLPctXq');

insert into users_roles(user_id, roles_id) values ( 1, 1 );