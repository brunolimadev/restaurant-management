insert into restaurant_user (name, email) values ('Bruno Lima', 'bruno@test.com.br');

insert into food_type (name) values ('JAPANESE'), ('ITALIAN'), ('BRAZILIAN'), ('MEXICAN'), ('CHINESE');

insert into restaurant (name, food_type_id) values ('Sushi Yoshi', 1), ('Mamamia Italian Food', 2), ('Churrascaria Good Grill', 3), ('TacoMex', 4), ('Chinese House', 5);

insert into restaurant_table(restaurant_id, description, number_of_seats) values (1,'Table 1', 4), (1, 'Table 2', 4), (1, 'Table 3', 4), (1, 'Table 4', 4), (1, 'Table 5', 4), (2, 'Table 1', 4), (2, 'Table 2', 4), (2, 'Table 3', 4), (2, 'Table 4', 4), (2, 'Table 5', 4), (3, 'Table 1', 4), (3, 'Table 2', 4), (3, 'Table 3', 4), (3, 'Table 4', 4), (3, 'Table 5', 4), (4, 'Table 1', 4), (4, 'Table 2', 4), (4, 'Table 3', 4), (4, 'Table 4', 4), (4, 'Table 5', 4), (5, 'Table 1', 4), (5, 'Table 2', 4), (5, 'Table 3', 4), (5, 'Table 4', 4), (5, 'Table 5', 4);

insert into address (restaurant_id, street, number, complement, neighborhood, city, state, zip_code) values (1, 'Rua dos Bobos', 0, 'Apto 101', 'Centro', 'São Paulo', 'SP', '01001-000'), (2, 'Rua dos Bobos', 0, 'Apto 102', 'Centro', 'São Paulo', 'SP', '01001-000'), (3, 'Rua dos Bobos', 0, 'Apto 103', 'Centro', 'São Paulo', 'SP', '01001-000'), (4, 'Rua dos Bobos', 0, 'Apto 104', 'Centro', 'São Paulo', 'SP', '01001-000'), (5, 'Rua dos Bobos', 0, 'Apto 105', 'Centro', 'São Paulo', 'SP', '01001000');

insert into opening_hour (restaurant_id, day_of_week, opening_time, closing_time) values (1, 1, '12:00', '22:00'), (1, 2, '12:00', '22:00'), (1, 3, '12:00', '22:00'), (1, 4, '12:00', '22:00'), (1, 5, '12:00', '22:00'), (1, 6, '12:00', '22:00'), (1, 7, '12:00', '22:00'), (2, 1, '12:00', '22:00'), (2, 2, '12:00', '22:00'), (2, 3, '12:00', '22:00'), (2, 4, '12:00', '22:00'), (2, 5, '12:00', '22:00'), (2, 6, '12:00', '22:00'), (2, 7, '12:00', '22:00'), (3, 1, '12:00', '22:00'), (3, 2, '12:00', '22:00'), (3, 3, '12:00', '22:00'), (3, 4, '12:00', '22:00'), (3, 5, '12:00', '22:00'), (3, 6, '12:00', '22:00'), (3, 7, '12:00', '22:00'), (4, 1, '12:00', '22:00'), (4, 2, '12:00', '22:00'), (4, 3, '12:00', '22:00'), (4, 4, '12:00', '22:00'), (4, 5, '12:00', '22:00'), (4,6, '12:00', '22:00'), (4, 7, '12:00', '22:00'), (5, 1, '12:00', '22:00'), (5, 2, '12:00', '22:00'), (5, 3, '12:00', '22:00'), (5, 4, '12:00', '22:00'), (5, 5, '12:00', '22:00'), (5, 6, '12:00', '22:00'), (5, 7, '12:00', '22:00');

insert into restaurant_comment (restaurant_id, user_id, comment) values (1, 1, 'Great food!'), (2, 1, 'Great food!'), (3, 1, 'Great food!'), (4, 1, 'Great food!'), (5, 1, 'Great food!');

insert into restaurant_like (restaurant_id, user_id) values (1, 1), (2, 1), (3, 1), (4, 1), (5, 1);

insert into reservation (restaurant_id, restaraunt_table_id, user_id, date, time, number_of_people) values (1, 1, 1, '2021-10-10', '12:00', 4), (2, 6, 1, '2021-10-10', '12:00', 4), (3, 11, 1, '2021-10-10', '12:00', 4), (4, 16, 1, '2021-10-10', '12:00', 4), (5, 21, 1, '2021-10-10', '12:00', 4);
