insert into roles(id, name) values ( 1, 'ROLE_SUPERADMIN' );

insert into users(id, user_name, email, password) values(1, 'tomjop', 'tomjop@test.pl', '$2a$12$SfvYyytEl3s/0Lo.wiSjD.sK/hwQrZSoZVMPjuClMo8MguQLPctXq');

insert into users_roles values ( 1, 1 );