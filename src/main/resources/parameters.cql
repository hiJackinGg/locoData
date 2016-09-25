CREATE TABLE parameters (
id int PRIMARY KEY, 
name varchar,
alias text) ;


INSERT INTO parameters (id, alias, name) VALUES (1, 's', 'секция');
INSERT INTO parameters (id, alias, name) VALUES (2, 'd', 'время' );
INSERT INTO parameters (id, alias, name) VALUES (3, 'tb', 'temperatura v bloke BAD');
INSERT INTO parameters (id, alias, name) VALUES (4, 'Ia12', 'cula toka v dvigatele 1-2');
INSERT INTO parameters (id, alias, name) VALUES (5, 'Ia34', 'cula toka v dvigatele 3-4');
INSERT INTO parameters (id, alias, name) VALUES (6, 'Ib', 'cula toka v bataree');
INSERT INTO parameters (id, alias, name) VALUES (7, 'Uc', 'naprazenie v cetu');
INSERT INTO parameters (id, alias, name) VALUES (8, 'Ub', 'naprazenie v bataree');
INSERT INTO parameters (id, alias, name) VALUES (9, 'Ie', 'cula toka vozbuzdeniya');
INSERT INTO parameters (id, alias, name) VALUES (10, 't1', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (11, 't2', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (12, 't3', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (13, 't4', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (14, 't5', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (15, 't6', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (16, 't7', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (17, 't8', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (18, 't9', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (19, 't10', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (20, 't11', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (21, 't12', 'temperatura');
INSERT INTO parameters (id, alias, name) VALUES (22, 't13', 'temperatura м1 (dvigatelya)');
INSERT INTO parameters (id, alias, name) VALUES (23, 't14', 'temperatura м2 (dvigatelya)');
INSERT INTO parameters (id, alias, name) VALUES (24, 't15', 'temperatura м3 (dvigatelya)');
INSERT INTO parameters (id, alias, name) VALUES (25, 't16', 'temperatura м4 (dvigatelya)');

INSERT INTO parameters (id, alias, name) VALUES (26, 'BDD_param_1', 'Аварийное положение ПкД 1-2');
INSERT INTO parameters (id, alias, name) VALUES (27, 'BDD_param_2', 'Контроль цепи реверсов');
INSERT INTO parameters (id, alias, name) VALUES (28, 'BDD_param_3', 'ВПЕРЕД А (ПкР2)');
INSERT INTO parameters (id, alias, name) VALUES (29, 'BDD_param_4', 'ВПЕРЕД А (ПкР1)');
INSERT INTO parameters (id, alias, name) VALUES (30, 'BDD_param_5', 'ВПЕРЕД Б (ПкР1)');
INSERT INTO parameters (id, alias, name) VALUES (31, 'BDD_param_6', 'ВПЕРЕД Б (ПкР2)');
INSERT INTO parameters (id, alias, name) VALUES (32, 'BDD_param_7', 'Вкл.контактор К23 обм.возбуждения 1-2');
INSERT INTO parameters (id, alias, name) VALUES (33, 'BDD_param_8', 'Питание вентилей режима тормож.');
INSERT INTO parameters (id, alias, name) VALUES (34, 'BDD_param_9', 'Питание вентилей режима тяги');
INSERT INTO parameters (id, alias, name) VALUES (35, 'BDD_param_10', 'Нормальное положение ПкД 3-4');
INSERT INTO parameters (id, alias, name) VALUES (36, 'BDD_param_11', 'Нормальное положение ПкД 1-2');
INSERT INTO parameters (id, alias, name) VALUES (37, 'BDD_param_12', 'Давление в тормозной магистрали');
INSERT INTO parameters (id, alias, name) VALUES (38, 'BDD_param_13', 'Аварийное положение ПкД 3-4');
INSERT INTO parameters (id, alias, name) VALUES (39, 'BDD_param_14', 'К1 641 (КОНТАКТОРЫ)');
INSERT INTO parameters (id, alias, name) VALUES (40, 'BDD_param_15', 'Контроль тормозного переключателя ПкТ2 - Т');
INSERT INTO parameters (id, alias, name) VALUES (41, 'BDD_param_16', 'Контроль тормозного переключателя ПкТ1 - Т');
INSERT INTO parameters (id, alias, name) VALUES (42, 'BDD_param_17', 'Контроль тормозного переключателя ПкТ2 - М');
INSERT INTO parameters (id, alias, name) VALUES (43, 'BDD_param_18', 'Отключились БК');
INSERT INTO parameters (id, alias, name) VALUES (44, 'BDD_param_19', 'Контроль тормозного переключателя ПкТ1 - М');
INSERT INTO parameters (id, alias, name) VALUES (45, 'BDD_param_20', 'Вкл. контактор К23 обм. возбуждения 3-4');
INSERT INTO parameters (id, alias, name) VALUES (46, 'BDD_param_21', 'К6 642 677 (КОНТАКТОРЫ)');
INSERT INTO parameters (id, alias, name) VALUES (47, 'BDD_param_22', 'К5 641 676');
INSERT INTO parameters (id, alias, name) VALUES (48, 'BDD_param_23', 'К4 641 675');
INSERT INTO parameters (id, alias, name) VALUES (49, 'BDD_param_24', 'К3 Э567 674');
INSERT INTO parameters (id, alias, name) VALUES (50, 'BDD_param_25', 'К19 608/Э611 672');
INSERT INTO parameters (id, alias, name) VALUES (51, 'BDD_param_26', 'К18 609 671');
INSERT INTO parameters (id, alias, name) VALUES (52, 'BDD_param_27', 'К17 617 673');
INSERT INTO parameters (id, alias, name) VALUES (53, 'BDD_param_28', 'К10 616 670');
INSERT INTO parameters (id, alias, name) VALUES (54, 'BDD_param_29', 'К16 641 686');
INSERT INTO parameters (id, alias, name) VALUES (55, 'BDD_param_30', 'К15 641 685');
INSERT INTO parameters (id, alias, name) VALUES (56, 'BDD_param_31', 'К14 641 684');
INSERT INTO parameters (id, alias, name) VALUES (57, 'BDD_param_32', 'К12 641 682');
INSERT INTO parameters (id, alias, name) VALUES (58, 'BDD_param_33', 'К9 642 680');
INSERT INTO parameters (id, alias, name) VALUES (59, 'BDD_param_34', 'К8 642 679');
INSERT INTO parameters (id, alias, name) VALUES (60, 'BDD_param_35', 'К7 642 678');
INSERT INTO parameters (id, alias, name) VALUES (61, 'BDD_param_36', 'К20 641 687');
INSERT INTO parameters (id, alias, name) VALUES (62, 'BDD_param_37', 'К13 641 683');
INSERT INTO parameters (id, alias, name) VALUES (63, 'BDD_param_38', 'Давление в тормозных цилиндрах ');
INSERT INTO parameters (id, alias, name) VALUES (64, 'BDD_param_39', 'Контроль обрыва тормозной магистрали');
INSERT INTO parameters (id, alias, name) VALUES (65, 'BDD_param_40', 'Контроль экстренного торможения');
INSERT INTO parameters (id, alias, name) VALUES (66, 'BDD_param_41', 'К39 Э535 692');
INSERT INTO parameters (id, alias, name) VALUES (67, 'BDD_param_42', 'К37 Э533 691');
INSERT INTO parameters (id, alias, name) VALUES (68, 'BDD_param_43', 'К35 Э530 690');
INSERT INTO parameters (id, alias, name) VALUES (69, 'BDD_param_44', 'К34 Э532 689');
INSERT INTO parameters (id, alias, name) VALUES (70, 'BDD_param_45', 'К33 Э532 688');

