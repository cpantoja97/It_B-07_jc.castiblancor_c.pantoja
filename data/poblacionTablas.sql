------------------------------------------------------------------------------
--------------------------- Poblar Tabla Roles. 1.----------------------------
------------------------------------------------------------------------------

------------------------------------------------------------------------------
----------------------------- Insertar 6 roles.-------------------------------
------------------------------------------------------------------------------

insert into ROLUSUARIO (ID_ROL,NOMBRE) values(1,'Afiliado');
insert into ROLUSUARIO (ID_ROL,NOMBRE) values(2,'Medico');
insert into ROLUSUARIO (ID_ROL,NOMBRE) values(3,'Recepcionista');
insert into ROLUSUARIO (ID_ROL,NOMBRE) values(4,'Administrador');
insert into ROLUSUARIO (ID_ROL,NOMBRE) values(5,'Gerente');
insert into ROLUSUARIO (ID_ROL,NOMBRE) values(6,'Organizador campania');

------------------------------------------------------------------------------
------------------------------ Poblar Tabla IPS. 2.---------------------------
------------------------------------------------------------------------------

------------------------------------------------------------------------------
----------------------------- Insertar 2 IPS.---------------------------------
------------------------------------------------------------------------------

insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(1,'IPSColombia','Calle 102 # 35-20');
insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(2,'IPSAndes','Calle 19 # 1-69');

------------------------------------------------------------------------------
--------------------------- Poblar Tabla SERVICIOS. 3.------------------------
------------------------------------------------------------------------------
------------------------------------------------------------------------------
--------------------------- Insertar 8 Tipos de servicio.-----------------------------
------------------------------------------------------------------------------
INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(1,'Consulta general');
INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(2,'Urgencias');
INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(3,'Consulta especialista');
INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(4,'Exámenes diagnósticos');
INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(5,'Hospitalización');
INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(6,'Terapias');
INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(7,'Procedimientos médicos especializados');
INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(8,'Consulta de control');


------------------------------------------------------------------------------
--------------------------- Insertar 4 servicios.-----------------------------
------------------------------------------------------------------------------

insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(1,'Urgencias', 2);
insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(2,'Consulta General', 1);
insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(3,'Oftalmologia', 4);
insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(4,'UCI', 5);

------------------------------------------------------------------------------
--------------------------- Poblar Tabla Usuarios. 4.-------------------------
------------------------------------------------------------------------------

------------------------------------------------------------------------------
--------------------------- Insertar 100 afiliados.---------------------------
------------------------------------------------------------------------------

insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (1, 'TI', 'rgariff0@yahoo.co.jp', 'Rick Gariff', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (2, 'CC', 'hcabrera1@fc2.com', 'Hunt Cabrera', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (3, 'CC', 'rheeran2@google.co.jp', 'Rory Heeran', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (4, 'CC', 'cbuesnel3@jigsy.com', 'Cad Buesnel', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (5, 'CC', 'karndt4@sourceforge.net', 'Kippy Arndt', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (6, 'CC', 'lhiskey5@mail.ru', 'Lilli Hiskey', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (7, 'TI', 'tjedrzejewski6@parallels.com', 'Thain Jedrzejewski', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (8, 'CC', 'scasin7@google.fr', 'Sybil Casin', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (9, 'CC', 'jhollyer8@purevolume.com', 'Jeni Hollyer', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (10, 'CC', 'cpassman9@sogou.com', 'Caterina Passman', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (11, 'CC', 'dlimberta@auda.org.au', 'Delora Limbert', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (12, 'TI', 'dgoorb@examiner.com', 'Drucy Goor', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (13, 'CC', 'cpischofc@blogger.com', 'Cecelia Pischof', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (14, 'TI', 'sdowlesd@washington.edu', 'Stafani Dowles', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (15, 'CC', 'rpatee@blogger.com', 'Romonda Pate', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (16, 'CC', 'whargeyf@loc.gov', 'Wesley Hargey', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (17, 'TI', 'aswiffeng@mayoclinic.com', 'Alanah Swiffen', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (18, 'TI', 'eleallh@cam.ac.uk', 'Elena Leall', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (19, 'TI', 'mwinshipi@google.nl', 'Maxwell Winship', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (20, 'TI', 'arosendalej@tripod.com', 'Alisander Rosendale', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (21, 'CC', 'glittrikk@berkeley.edu', 'Guillema Littrik', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (22, 'TI', 'phoulahanl@hibu.com', 'Philis Houlahan', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (23, 'CC', 'bspollenm@nbcnews.com', 'Brandea Spollen', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (24, 'CC', 'tdilsn@usgs.gov', 'Twila Dils', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (25, 'TI', 'csilvero@merriam-webster.com', 'Christophorus Silver', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (26, 'CC', 'rpolendinep@friendfeed.com', 'Retha Polendine', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (27, 'TI', 'mkinghamq@bizjournals.com', 'Mercy Kingham', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (28, 'CC', 'kmckaner@adobe.com', 'Klemens McKane', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (29, 'TI', 'pquinbys@dedecms.com', 'Pansie Quinby', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (30, 'TI', 'cbiffent@github.com', 'Cahra Biffen', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (31, 'CC', 'hlebanu@reuters.com', 'Hayden Leban', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (32, 'CC', 'aivashinnikovv@huffingtonpost.com', 'Abagail Ivashinnikov', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (33, 'TI', 'bdefrainew@cbsnews.com', 'Benni Defraine', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (34, 'CC', 'mellensx@msu.edu', 'Myron Ellens', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (35, 'TI', 'ebithelly@ucoz.com', 'Erick Bithell', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (36, 'CC', 'rhafnerz@t.co', 'Ritchie Hafner', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (37, 'CC', 'lwrigglesworth10@instagram.com', 'Lavina Wrigglesworth', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (38, 'TI', 'bmarcinkus11@vkontakte.ru', 'Barris Marcinkus', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (39, 'CC', 'rwitt12@issuu.com', 'Reine Witt', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (40, 'CC', 'iburnand13@issuu.com', 'Isadora Burnand', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (41, 'CC', 'edruhan14@cbslocal.com', 'Elfie Druhan', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (42, 'TI', 'aoverbury15@digg.com', 'Allyn Overbury', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (43, 'CC', 'dbonefant16@sun.com', 'Deeanne Bonefant', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (44, 'CC', 'dslesser17@surveymonkey.com', 'Dede Slesser', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (45, 'CC', 'cpottes18@reverbnation.com', 'Carleton Pottes', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (46, 'TI', 'esackey19@sciencedirect.com', 'Eloisa Sackey', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (47, 'CC', 'mcarrett1a@geocities.com', 'Marianna Carrett', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (48, 'CC', 'agerauld1b@prlog.org', 'Aharon Gerauld', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (49, 'TI', 'dplanks1c@blinklist.com', 'Dorthy Planks', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (50, 'CC', 'sgaydon1d@cargocollective.com', 'Selena Gaydon', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (51, 'TI', 'sshermore1e@ucla.edu', 'Shelley Shermore', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (52, 'TI', 'wcrackett1f@canalblog.com', 'Warner Crackett', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (53, 'TI', 'tcopplestone1g@artisteer.com', 'Thedrick Copplestone', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (54, 'CC', 'mtritton1h@rakuten.co.jp', 'Micheline Tritton', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (55, 'CC', 'lhagston1i@census.gov', 'Lenee Hagston', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (56, 'TI', 'hagge1j@mtv.com', 'Hurleigh Agge', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (57, 'TI', 'gleither1k@phoca.cz', 'Gretel Leither', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (58, 'CC', 'cdominick1l@globo.com', 'Corabella Dominick', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (59, 'TI', 'dbaldacchino1m@sciencedirect.com', 'Dyna Baldacchino', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (60, 'TI', 'rnapoli1n@chron.com', 'Rana Napoli', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (61, 'TI', 'cdanneil1o@telegraph.co.uk', 'Catharina Danneil', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (62, 'TI', 'skubatsch1p@51.la', 'Steward Kubatsch', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (63, 'CC', 'sparchment1q@bloglovin.com', 'Star Parchment', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (64, 'TI', 'hyounge1r@desdev.cn', 'Hilly Younge', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (65, 'TI', 'hkastel1s@shinystat.com', 'Harrietta Kastel', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (66, 'TI', 'ldowall1t@ed.gov', 'Luciana Dowall', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (67, 'CC', 'rmorl1u@cnet.com', 'Rafaelita Morl', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (68, 'CC', 'sreith1v@zimbio.com', 'Starla Reith', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (69, 'CC', 'lfullicks1w@ox.ac.uk', 'Leanna Fullicks', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (70, 'TI', 'ldutnall1x@weebly.com', 'Lucky Dutnall', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (71, 'CC', 'fle1y@google.pl', 'Faunie Le Surf', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (72, 'TI', 'arampley1z@alibaba.com', 'Alyson Rampley', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (73, 'TI', 'mtrenear20@e-recht24.de', 'Mead Trenear', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (74, 'TI', 'dlinsay21@nydailynews.com', 'Dianemarie Linsay', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (75, 'CC', 'icoghlin22@blogger.com', 'Inglis Coghlin', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (76, 'TI', 'kstannett23@quantcast.com', 'Katrinka Stannett', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (77, 'TI', 'kades24@arizona.edu', 'Katy Ades', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (78, 'CC', 'hkennford25@cpanel.net', 'Humfried Kennford', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (79, 'TI', 'njakubowsky26@list-manage.com', 'Normie Jakubowsky', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (80, 'TI', 'lokell27@bbc.co.uk', 'Letisha Okell', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (81, 'TI', 'lchattey28@php.net', 'Leicester Chattey', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (82, 'TI', 'jguinnane29@addthis.com', 'Judy Guinnane', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (83, 'TI', 'hchidzoy2a@mail.ru', 'Hugibert Chidzoy', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (84, 'TI', 'dsnoad2b@samsung.com', 'Drugi Snoad', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (85, 'CC', 'bluggar2c@ebay.com', 'Brent Luggar', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (86, 'CC', 'dfriary2d@shinystat.com', 'Dorena Friary', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (87, 'TI', 'jzorer2e@odnoklassniki.ru', 'Jo Zorer', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (88, 'TI', 'wrickardes2f@epa.gov', 'Wanids Rickardes', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (89, 'TI', 'fmecco2g@photobucket.com', 'Florry Mecco', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (90, 'TI', 'igirard2h@tamu.edu', 'Ibby Girard', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (91, 'TI', 'dsiney2i@washingtonpost.com', 'Dennie Siney', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (92, 'CC', 'cpear2j@usgs.gov', 'Corene Pear', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (93, 'TI', 'mlummasana2k@guardian.co.uk', 'Marisa Lummasana', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (94, 'TI', 'bcomar2l@yahoo.com', 'Berta Comar', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (95, 'CC', 'dfaulder2m@icq.com', 'Dewain Faulder', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (96, 'CC', 'lcardus2n@usnews.com', 'Liza Cardus', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (97, 'TI', 'pnoyes2o@nsw.gov.au', 'Parrnell Noyes', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (98, 'TI', 'zusherwood2p@ebay.com', 'Zebedee Usherwood', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (99, 'TI', 'mlipp2q@statcounter.com', 'Michaella Lipp', 1);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (100, 'CC', 'sbriddle2r@bing.com', 'Saunderson Briddle', 1);

-------------------------------------------------------------------------------
----------------------------- Insertar 20 Medicos.-----------------------------
-------------------------------------------------------------------------------

insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (101, 'CC', 'kcottam0@yelp.com', 'Kingsley Cottam', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (102, 'CC', 'cstannard1@163.com', 'Cynthie Stannard', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (103, 'CC', 'jcromblehome2@opensource.org', 'Joan Cromblehome', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (104, 'CC', 'tchevins3@icq.com', 'Thaddus Chevins', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (105, 'CC', 'scaroline4@foxnews.com', 'Sara-ann Caroline', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (106, 'CC', 'hbuntain5@amazonaws.com', 'Harley Buntain', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (107, 'CC', 'cpiniur6@fema.gov', 'Consolata Piniur', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (108, 'CC', 'knorthedge7@xrea.com', 'Kanya Northedge', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (109, 'CC', 'abembridge8@unblog.fr', 'Avril Bembridge', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (110, 'CC', 'rkummerlowe9@berkeley.edu', 'Ruddie Kummerlowe', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (111, 'CE', 'rhedona@msu.edu', 'Ranna Hedon', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (112, 'CC', 'clanyonb@google.pl', 'Cassaundra Lanyon', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (113, 'CC', 'jscinic@elegantthemes.com', 'Jacki Scini', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (114, 'CC', 'mjeavond@cdc.gov', 'Madalyn Jeavon', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (115, 'CC', 'kmarchmente@livejournal.com', 'Kelly Marchment', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (116, 'CE', 'mvasyaevf@tmall.com', 'Marya Vasyaev', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (117, 'CC', 'cshimukg@creativecommons.org', 'Clint Shimuk', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (118, 'CC', 'amcgaffeyh@tripadvisor.com', 'Annalee McGaffey', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (119, 'CC', 'fpoyzeri@ameblo.jp', 'Friedrich Poyzer', 2);
insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) values (120, 'CE', 'prawsthornej@state.gov', 'Prue Rawsthorne', 2);

-------------------------------------------------------------------------------
-------------------------- Insertar 6 recepcionistas.--------------------------
-------------------------------------------------------------------------------

insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(121,'CC', 'Golden420@gmail.com', 'Zuko Manuel', 3);
insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(122,'CC',  'elRobotDelFuturo@gmail.com', 'Laura Perez', 3);
insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(123,'CC', 'SusanaOria@gmail.com', 'Susana Ramirez', 3);
insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(124,'CC', 'ninjaRomero@gmail.com', 'Emilio Romero', 3);
insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(125,'CC', 'a.espinoza@gmail.com', 'Anamaria Espinoza', 3);
insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(126,'CC', 'Lucumi@gmail.com', 'Luciana Romero',5);

-------------------------------------------------------------------------------
------------------------------- Insertar 1 Gerente.----------------------------
-------------------------------------------------------------------------------

insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(127,'CC', 'p.casas@gmail.com', 'Pablo Casas', 4);

-------------------------------------------------------------------------------
---------------------- Insertar 1 Administrador de datos.----------------------
-------------------------------------------------------------------------------

insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(128,'CC', 'genesis@hotmail.com', 'Laura Perez',5);

-------------------------------------------------------------------------------
---------------------- Insertar 1 Organizador de Campaña.----------------------
-------------------------------------------------------------------------------

insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(129,'CC', 'k.duque@gmail.com', 'Kevin Duque',6);

------------------------------------------------------------------------------
--------------------------- Poblar Tabla Medicos. 5.--------------------------
------------------------------------------------------------------------------

insert into MEDICOS (NUMDOC, REGMEDICO) values (101, 101);
insert into MEDICOS (NUMDOC, REGMEDICO) values (102, 102);
insert into MEDICOS (NUMDOC, REGMEDICO) values (103, 103);
insert into MEDICOS (NUMDOC, REGMEDICO) values (104, 104);
insert into MEDICOS (NUMDOC, REGMEDICO) values (105, 105);
insert into MEDICOS (NUMDOC, REGMEDICO) values (106, 106);
insert into MEDICOS (NUMDOC, REGMEDICO) values (107, 107);
insert into MEDICOS (NUMDOC, REGMEDICO) values (108, 108);
insert into MEDICOS (NUMDOC, REGMEDICO) values (109, 109);
insert into MEDICOS (NUMDOC, REGMEDICO) values (110, 110);
insert into MEDICOS (NUMDOC, REGMEDICO) values (111, 111);
insert into MEDICOS (NUMDOC, REGMEDICO) values (112, 112);
insert into MEDICOS (NUMDOC, REGMEDICO) values (113, 113);
insert into MEDICOS (NUMDOC, REGMEDICO) values (114, 114);
insert into MEDICOS (NUMDOC, REGMEDICO) values (115, 115);
insert into MEDICOS (NUMDOC, REGMEDICO) values (116, 116);
insert into MEDICOS (NUMDOC, REGMEDICO) values (117, 117);
insert into MEDICOS (NUMDOC, REGMEDICO) values (118, 118);
insert into MEDICOS (NUMDOC, REGMEDICO) values (119, 119);
insert into MEDICOS (NUMDOC, REGMEDICO) values (120, 120);

------------------------------------------------------------------------------
-------------------------- Poblar Tabla Afiliados. 6.-------------------------
------------------------------------------------------------------------------

insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (1, TO_DATE('22/06/1959', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (2, TO_DATE('28/11/2013', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (3, TO_DATE('10/05/1972', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (4, TO_DATE('08/06/1962', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (5, TO_DATE('04/03/2013', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (6, TO_DATE('19/10/1968', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (7, TO_DATE('30/03/1952', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (8, TO_DATE('21/10/1993', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (9, TO_DATE('28/03/2014', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (10, TO_DATE('24/08/2006', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (11, TO_DATE('17/12/1959', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (12, TO_DATE('25/07/1959', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (13, TO_DATE('29/03/1984', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (14, TO_DATE('29/03/2000', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (15, TO_DATE('08/12/2015', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (16, TO_DATE('19/04/1990', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (17, TO_DATE('12/05/1957', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (18, TO_DATE('24/07/1990', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (19, TO_DATE('01/07/1965', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (20, TO_DATE('05/11/2018', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (21, TO_DATE('05/03/1985', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (22, TO_DATE('25/12/1982', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (23, TO_DATE('02/06/2002', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (24, TO_DATE('11/03/1968', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (25, TO_DATE('25/03/1951', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (26, TO_DATE('18/03/1983', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (27, TO_DATE('05/03/2003', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (28, TO_DATE('15/12/1970', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (29, TO_DATE('25/06/2014', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (30, TO_DATE('25/06/1959', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (31, TO_DATE('04/03/1950', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (32, TO_DATE('24/02/2004', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (33, TO_DATE('22/02/1963', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (34, TO_DATE('15/05/1976', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (35, TO_DATE('08/03/1990', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (36, TO_DATE('13/08/1981', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (37, TO_DATE('06/06/1951', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (38, TO_DATE('29/10/1960', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (39, TO_DATE('28/07/1960', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (40, TO_DATE('18/10/1999', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (41, TO_DATE('03/09/1995', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (42, TO_DATE('20/06/1989', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (43, TO_DATE('09/02/1953', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (44, TO_DATE('17/02/1989', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (45, TO_DATE('30/07/1982', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (46, TO_DATE('26/05/1981', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (47, TO_DATE('06/02/2015', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (48, TO_DATE('19/08/2003', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (49, TO_DATE('05/11/2016', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (50, TO_DATE('26/05/1990', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (51, TO_DATE('02/05/1993', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (52, TO_DATE('30/10/1987', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (53, TO_DATE('17/07/1961', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (54, TO_DATE('14/09/2016', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (55, TO_DATE('26/10/2016', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (56, TO_DATE('06/12/1994', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (57, TO_DATE('22/10/1950', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (58, TO_DATE('15/05/1984', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (59, TO_DATE('10/12/1974', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (60, TO_DATE('22/11/1994', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (61, TO_DATE('19/06/1991', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (62, TO_DATE('20/10/1988', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (63, TO_DATE('22/08/2016', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (64, TO_DATE('13/07/1998', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (65, TO_DATE('03/04/1968', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (66, TO_DATE('17/09/1980', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (67, TO_DATE('21/04/1986', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (68, TO_DATE('14/12/2002', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (69, TO_DATE('19/06/1984', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (70, TO_DATE('12/02/1973', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (71, TO_DATE('29/08/1999', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (72, TO_DATE('01/11/1968', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (73, TO_DATE('16/07/2008', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (74, TO_DATE('17/06/2015', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (75, TO_DATE('14/03/1967', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (76, TO_DATE('03/12/2004', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (77, TO_DATE('13/07/1954', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (78, TO_DATE('02/09/1956', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (79, TO_DATE('02/03/1968', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (80, TO_DATE('31/10/1964', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (81, TO_DATE('11/10/2002', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (82, TO_DATE('07/04/1967', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (83, TO_DATE('29/12/1959', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (84, TO_DATE('07/12/2005', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (85, TO_DATE('31/05/1961', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (86, TO_DATE('10/12/1977', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (87, TO_DATE('12/05/1993', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (88, TO_DATE('01/05/2007', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (89, TO_DATE('05/06/1998', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (90, TO_DATE('02/11/2004', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (91, TO_DATE('17/11/1955', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (92, TO_DATE('15/10/1990', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (93, TO_DATE('26/03/2008', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (94, TO_DATE('30/03/1952', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (95, TO_DATE('16/07/1992', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (96, TO_DATE('20/01/2008', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (97, TO_DATE('27/02/2002', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (98, TO_DATE('11/12/2007', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (99, TO_DATE('09/11/1976', 'DD/MM/YYYY') );
insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (100, TO_DATE('16/06/1978', 'DD/MM/YYYY') );

------------------------------------------------------------------------------
----------------------- Poblar Tabla Recepcionistas. 7.-----------------------
------------------------------------------------------------------------------

insert into RECEPCIONISTAS (NUMDOC, ID_IPS) values (121,1);
insert into RECEPCIONISTAS (NUMDOC, ID_IPS) values (122,1);
insert into RECEPCIONISTAS (NUMDOC, ID_IPS) values (123,1);
insert into RECEPCIONISTAS (NUMDOC, ID_IPS) values (124,2);
insert into RECEPCIONISTAS (NUMDOC, ID_IPS) values (125,2);
insert into RECEPCIONISTAS (NUMDOC, ID_IPS) values(126,2);

------------------------------------------------------------------------------
------------------------ Poblar Tabla SERVICIOSIPS. 7.------------------------
------------------------------------------------------------------------------

insert into SERVICIOSIPS (ID_IPS, ID_SERVICIO, CAPACIDAD, HORARIOINICIO, HORARIOFIN) values(1,1,4,TO_DATE('1900-01-01 2:00','YYYY-MM-DD HH24:MI'),TO_DATE('1900-01-01 23:59','YYYY-MM-DD HH24:MI')); -- EPS 1 servicio Urgencias
insert into SERVICIOSIPS (ID_IPS, ID_SERVICIO, CAPACIDAD, HORARIOINICIO, HORARIOFIN) values(2,1,400,TO_DATE('1900-01-01 0:01','YYYY-MM-DD HH24:MI'),TO_DATE('1900-01-01 23:59','YYYY-MM-DD HH24:MI')); -- EPS 2 servicio Urgencias
insert into SERVICIOSIPS (ID_IPS, ID_SERVICIO, CAPACIDAD, HORARIOINICIO, HORARIOFIN) values(1,2,75,TO_DATE('1900-01-01 6:30','YYYY-MM-DD HH24:MI'),TO_DATE('1900-01-01 18:00','YYYY-MM-DD HH24:MI')); -- EPS 1 servicio Consulta general
insert into SERVICIOSIPS (ID_IPS, ID_SERVICIO, CAPACIDAD, HORARIOINICIO, HORARIOFIN) values(2,2,50,TO_DATE('1900-01-01 8:00','YYYY-MM-DD HH24:MI'),TO_DATE('1900-01-01 16:00','YYYY-MM-DD HH24:MI')); -- EPS 2 servicio Consulta general
insert into SERVICIOSIPS (ID_IPS, ID_SERVICIO, CAPACIDAD, HORARIOINICIO, HORARIOFIN) values(1,4,50,TO_DATE('1900-01-01 2:00','YYYY-MM-DD HH24:MI'),TO_DATE('1900-01-01 23:59','YYYY-MM-DD HH24:MI')); -- EPS 1 servicio UCI
insert into SERVICIOSIPS (ID_IPS, ID_SERVICIO, CAPACIDAD, HORARIOINICIO, HORARIOFIN) values(2,4,120,TO_DATE('1900-01-01 0:01','YYYY-MM-DD HH24:MI'),TO_DATE('1900-01-01 23:59','YYYY-MM-DD HH24:MI')); -- EPS 2 servicio UCI
insert into SERVICIOSIPS (ID_IPS, ID_SERVICIO, CAPACIDAD, HORARIOINICIO, HORARIOFIN) values(2,3,20,TO_DATE('1900-01-01 7:00','YYYY-MM-DD HH24:MI'),TO_DATE('1900-01-01 17:00','YYYY-MM-DD HH24:MI')); -- EPS 2 servicio Oftalmologia

------------------------------------------------------------------------------
--------------------- Poblar Tabla MEDICOSABSCRITOS. 9------------------------
------------------------------------------------------------------------------

insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(101,1);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(102,1);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(103,1);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(104,1);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(105,1);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(106,1);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(107,1);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(108,1);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(109,1);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(110,1);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(111,2);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(112,2);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(113,2);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(114,2);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(115,2);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(116,2);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(117,2);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(118,2);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(119,2);
insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(120,2);

------------------------------------------------------------------------------
--------------------- Poblar Tabla SERVICIOSMEDICOS. 10-----------------------
------------------------------------------------------------------------------

insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(101,1, 'Urgenciologo');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(102,1, 'Urgenciologo');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(103,1, 'Urgenciologo');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(104,2, 'Medico General');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(105,2, 'Medico General');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(106,2, 'Medico General');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(107,4, 'Medicina');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(108,4, 'Medicina');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(109,4, 'Medicina');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(110,1, 'Urgenciologo');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(111,1, 'Urgenciologo');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(112,1, 'Urgenciologo');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(113,1, 'Urgenciologo');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(114,2, 'Medico General');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(115,2, 'Medico General');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(116,2, 'Medico General');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(117,4, 'Medicina');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(118,4, 'Medicina');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(119,3,'Oftalmologo');
insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(120,3,'Oftalmologo');

------------------------------------------------------------------------------
------------------------- Poblar Tabla RECETAS 11. ---------------------------
------------------------------------------------------------------------------

insert into RECETAS (ID_RECETA, ID_MEDICO, ID_AFILIADO) values(1,104, 1);

------------------------------------------------------------------------------
----------------------- Poblar Tabla MEDICAMENTOS 12.  -----------------------
------------------------------------------------------------------------------

insert into MEDICAMENTOS (ID_MEDICAMENTO, NOMBRE, DESCRIPCION) values(1,'Acetaminofen', 'Pastilla que cura todo.');

------------------------------------------------------------------------------
----------------------- Poblar Tabla ITEMSRECETA 13. -------------------------
------------------------------------------------------------------------------

insert into ITEMSRECETA (ID_RECETA, ID_MEDICAMENTO, CANTIDAD, INDICACIONES) values(1,1,12, 'Tomar por 4 dias cada 8 horas.');

------------------------------------------------------------------------------
------------------------ Poblar Tabla ORDENES 14. ----------------------------
------------------------------------------------------------------------------

insert into ORDENES (ID, ID_SERVICIO, ID_MEDICO, ID_AFILIADO) values(1001, 3,119, 1);
insert into ORDENES (ID, ID_SERVICIO, ID_MEDICO, ID_AFILIADO) values(1002, 3,119, 2);
insert into ORDENES (ID, ID_SERVICIO, ID_MEDICO, ID_AFILIADO) values(1003, 3,119, 3);
insert into ORDENES (ID, ID_SERVICIO, ID_MEDICO, ID_AFILIADO) values(1004, 3,119, 4);
insert into ORDENES (ID, ID_SERVICIO, ID_MEDICO, ID_AFILIADO) values(1005, 3,119, 5);
insert into ORDENES (ID, ID_SERVICIO, ID_MEDICO, ID_AFILIADO) values(1006, 3,119, 6);
insert into ORDENES (ID, ID_SERVICIO, ID_MEDICO, ID_AFILIADO) values(1007, 3,120, 8);
insert into ORDENES (ID, ID_SERVICIO, ID_MEDICO, ID_AFILIADO) values(1008, 3,120, 9);
insert into ORDENES (ID, ID_SERVICIO, ID_MEDICO, ID_AFILIADO) values(1009, 3,120, 2);
insert into ORDENES (ID, ID_SERVICIO, ID_MEDICO, ID_AFILIADO) values(1010, 3,120, 1);

------------------------------------------------------------------------------
-------------------------- RESERVASERVICIO 15. -------------------------------
------------------------------------------------------------------------------

insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(3,1,2,TO_DATE('2019-10-29 12:40:00','YYYY-MM-DD HH24:MI:SS'));
insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(3,1,2,TO_DATE('2019-10-29 12:20:00','YYYY-MM-DD HH24:MI:SS'));
insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(3,1,2,TO_DATE('2019-10-29 12:00:00','YYYY-MM-DD HH24:MI:SS'));
insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(3,2,2, TO_DATE('12/02/2018', 'DD/MM/YYYY'));
insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(3,3,2, TO_DATE('08/03/2018', 'DD/MM/YYYY'));
insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(3,4,2, TO_DATE('09/04/2018', 'DD/MM/YYYY'));
insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(3,5,2, TO_DATE('25/05/2019', 'DD/MM/YYYY'));
insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(3,8,2, TO_DATE('01/06/2019', 'DD/MM/YYYY'));
insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(3,9,2, TO_DATE('07/07/2018', 'DD/MM/YYYY'));
insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(3,2,2, TO_DATE('29/08/2019', 'DD/MM/YYYY'));
insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(3,1,2, TO_DATE('05/09/2019', 'DD/MM/YYYY'));

------------------------------------------------------------------------------
------------------------- PRESTACIONSERVICIO 16. -----------------------------
------------------------------------------------------------------------------

insert into PRESTACIONSERVICIO (NUMDOC, ID_SERVICIO, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(1,3,2,TO_DATE('2019-10-29 12:00:00','YYYY-MM-DD HH24:MI:SS'),124);
insert into PRESTACIONSERVICIO (NUMDOC, ID_SERVICIO, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(1,3,2,TO_DATE('2019-10-29 12:20:00','YYYY-MM-DD HH24:MI:SS'),124);
insert into PRESTACIONSERVICIO (NUMDOC, ID_SERVICIO, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(1,3,2,TO_DATE('2019-10-29 12:40:00','YYYY-MM-DD HH24:MI:SS'),124);
insert into PRESTACIONSERVICIO (NUMDOC, ID_SERVICIO, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(2,3,2,TO_DATE('12/02/2018', 'DD/MM/YYYY'),124);
insert into PRESTACIONSERVICIO (NUMDOC, ID_SERVICIO, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(3,3,2,TO_DATE('08/03/2018', 'DD/MM/YYYY'),124);
insert into PRESTACIONSERVICIO (NUMDOC, ID_SERVICIO, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(4,3,2,TO_DATE('09/04/2018', 'DD/MM/YYYY'),125);
insert into PRESTACIONSERVICIO (NUMDOC, ID_SERVICIO, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(8,3,2,TO_DATE('01/06/2019', 'DD/MM/YYYY'),125);
insert into PRESTACIONSERVICIO (NUMDOC, ID_SERVICIO, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(9,3,2,TO_DATE('07/07/2018', 'DD/MM/YYYY'),126);
insert into PRESTACIONSERVICIO (NUMDOC, ID_SERVICIO, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(2,3,2,TO_DATE('29/08/2019', 'DD/MM/YYYY'),126);
insert into PRESTACIONSERVICIO (NUMDOC, ID_SERVICIO, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(1,3,2,TO_DATE('05/09/2019', 'DD/MM/YYYY'),126);
insert into PRESTACIONSERVICIO (NUMDOC, ID_SERVICIO, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(1,1,2,TO_DATE('2019-11-01 12:40:00','YYYY-MM-DD HH24:MI:SS'),124);

------------------------------------------------------------------------------
------------------------- INHABILITACION 17. -----------------------------
------------------------------------------------------------------------------
insert into INHABILITACION (SERVICIO, IPS, FECHAINICIO, FECHAFIN) VALUES(1, 1, TO_DATE('15/11/2018','DD/MM/YYYY'), TO_DATE('18/11/2018','DD/MM/YYYY'));


commit;
