insert into users (id, email, first_name, last_name, is_activated) values (0, 'test1234@test.com', 'UserOne', 'NameOne', true);
insert into principals (id, password, user_id) values (0, 'password2', 0)
insert into phones (id, number, phone_type, user_id) values (0, '167789987', 0, 0);
insert into phones (id, number, phone_type, user_id) values (1, '267789987', 1, 0);

insert into users (id, email, first_name, last_name, is_activated) values (1, 'test16454@test.com', 'UserTwo', 'NameTwo', true);
insert into principals (id, password, user_id) values (1, 'password1', 1)
insert into phones (id, number, phone_type, user_id) values (2, '367789987', 0, 1);
insert into phones (id, number, phone_type, user_id) values (3, '467789987', 1, 1);

insert into users (id, email, first_name, last_name, is_activated) values (2, 'test17454@test.com', 'UserThree', 'NameThree', true);
insert into principals (id, password, user_id) values (2, 'password2', 2)
insert into phones (id, number, phone_type, user_id) values (4, '567789987', 0, 2);
insert into phones (id, number, phone_type, user_id) values (5, '667789987', 2, 2);
