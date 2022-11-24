drop database cineclub;
create schema cineclub; 
use cineclub;

select * from usuario;

insert into usuario (id,email,nombre,password) values
					(1,'pacofranco45@gmail.com','Franco','1234'),
                    (2,'jucampana@alumno.unlam.edu.ar','main3','1234'),
                    (3,'mail@mail.com','main','1234'),
                    (4,'mail2@mail.com','main2','1234'),
                    (5,'admin','admin','admin');

insert into formato (id,tipo) values
 (1,'2D'),(2,'3D'),(3,'Realidad Aumentada'); 

insert into cine (id,locacion,nombreCine) values (1,'Calle Falsa 123','CineClub');

insert into sala (id,asientosTotales,nombreSala,cine_id) values
				 (1,50,'Sala J',1),
				 (2,50,'Sala K',1),
				 (3,50,'Sala L',1);
                 
insert into cine (id,locacion,nombreCine) values (2,'Italia 913','Cine Magistral');
 
insert into sala (id,asientosTotales,nombreSala,cine_id) values                
				 (4,50,'Sala A',2),
                 (5,50,'Sala B',2),
                 (6,50,'Sala C',2);
                 
 insert into cine (id,locacion,nombreCine) values (3,'Etiopia 1865','Grand');
                  
insert into sala (id,asientosTotales,nombreSala,cine_id) values                
				 (7,50,'Sala 1',3),
                 (8,50,'Sala 2',3),
                 (9,50,'Sala 3',3);
                 
insert into cine (id,locacion,nombreCine) values (4,'Irlanda 993','Sunshine');          
      
insert into sala (id,asientosTotales,nombreSala,cine_id) values                
							(10,50,'Sala X',4),
							(11,50,'Sala Z',4),
							(12,50,'Sala Y',4);
                            
insert into sala (id,asientosTotales,nombreSala,cine_id) values (13,1,'Sala Test Reserva',4);


update cine set latitud = '-34.6617', longitud = '-58.575' where id = 1;
update cine set latitud = '-34.6848', longitud = '-58.5576' where id = 2;
update cine set latitud = '-34.6743', longitud = '-58.5596' where id = 3;
update cine set latitud = '-34.6695', longitud = '-58.5613' where id = 4;


INSERT INTO genero (id,descripcion,poster)
VALUES ("1","Acción",'img/peliculas/tren-bala.jpg');
INSERT INTO genero (id,descripcion,poster)
VALUES ("2","Anime",'img/peliculas/db-super.jpg');
INSERT INTO genero (id,descripcion,poster)
VALUES ("3","Suspenso",'img/peliculas/nop.jpg');
INSERT INTO genero (id,descripcion,poster)
VALUES ("4","Comedia",'img/peliculas/30-noches-con-mi-ex.jpg');
INSERT INTO genero (id,descripcion,poster)
VALUES ("5","Drama",'img/peliculas/carajita.jpg');
INSERT INTO genero (id,descripcion,poster)
VALUES ("6","Terror",'img/peliculas/invitacion-al-infierno.jpg');
INSERT INTO genero (id,descripcion,poster)
VALUES ("7","Biografica",'img/peliculas/elvis.jpg');
INSERT INTO genero (id,descripcion,poster)
VALUES ("8","Animación",'img/peliculas/minion.jpg');

INSERT INTO clasificacionpelicula (id,descripcion)
VALUES ("1","ATP");
INSERT INTO clasificacionpelicula (id,descripcion)
VALUES ("2","+13");
INSERT INTO clasificacionpelicula (id,descripcion)
VALUES ("3","+16");
INSERT INTO clasificacionpelicula (id,descripcion)
VALUES ("4","+18");
INSERT INTO clasificacionpelicula (id,descripcion)
VALUES ("5","R");



INSERT INTO `pelicula`(id,director,duracion,fechaEstreno,poster,protagonista,sinopsis,titulo,clasificacionPelicula_id,genero_id,calificacion) VALUES
(3,'Adrian Suar',300,'2022-09-29 00:00:00','img/peliculas/30-noches-con-mi-ex.jpg','Adrian Suar',
'En esta oportunidad, Adrián Suar interpreta a El Turbo, quien luego de años de separado de La Loba,
 acepta, por pedido de la hija que tienen en común,\n       convivir durante 30 días con su ex mujer,\n
 que viene de una larga internación psiquiátrica','30 noches con mi ex',2,4,3),
(4,'Zach Cregger',103,'2022-09-29 00:00:00','img/peliculas/barbaro.jpg','Danny Chan',
'Una joven que viaja a Detroit para una entrevista de trabajo reserva alojamiento en la ciudad.\n
Pero cuando llega tarde en la noche,descubre que la casa fue reservada a dos personas simultáneamente y que hay un extraño alojándose allí',
'Barbaro',3,6,4),
(5,'Jimena Monteoliva',205,'2022-11-22 00:00:00','img/peliculas/bienvenidos-al-infierno.jpg','Constanza Cardillo',
'La joven Lucía se enamora del “Monje”, el cantante de una banda de pelilargos que emula la estética de Kiss\n
       y que se la lleva a vivir a un aguantadero.','Bienvenidos al infierno',3,6,2),
(6,'Jimena Monteoliva',89,'2022-10-20 00:00:00','img/peliculas/carajita.jpg','Ulla Prida',
'La joven Lucía se enamora del “Monje”, el cantante de una banda de pelilargos que emula la estética de Kiss\n 
      y que se la lleva a vivir a un aguantadero.','Carajita',1,5,1),
(7,'Lucas Combina',130,'2022-10-17 00:00:00','img/peliculas/crimen-argentino.jpg',
'Ulla Prid','Sara (17 años) y su niñera Yarisa (36) tienen una relación que parece trascender su clase social:
 son lo más parecido a una hija y una madre,\n 
 pero un accidente irrumpe en sus vidas y pone a prueba la inocente ilusión de que nada las separará.','Un crimen argentino',1,5,5),
(8,'Julio Chavez',82,'2022-11-12 00:00:00','img/peliculas/cuando-la-miro.jpg','Julio Chávez',
'Javier es un artista plástico que pasa sus días sin sobresaltos.\n 
      Un día emprende un extraño proyecto: filmar a su madre.','Cuando la miro',2,5,5),
(9,'Tetsuro Kodama',100,'2022-10-13 00:00:00','img/peliculas/db-super.jpg','',
'Son Goku destruyó en su momento a la Patrulla Roja. \n
       Ahora, ciertos individuos han decidido continuar con su legado y han creado a los androides definitivos: Gamma 1 y Gamma 2.',
       'Dragon ball Super Hero',2,2,4),
(10,'Fernando Sirianni',103,'2022-10-12 00:00:00','img/peliculas/el-paraiso.jpg',
'','Es la historia de Magdalena y Anna Scilko, hermanas inmigrantes que llegan de Polonia a Buenos Aires con la esperanza\n
       de un nuevo futuro.','El paraiso',5,5,3),
(11,'Baz Luhrmann',280,'2022-10-11 00:00:00','img/peliculas/elvis.jpg','Austin Butler',
'El visionario cineasta nominado al Oscar Baz Luhrmann, \n
        llega \"Elvis\", el drama de Warner Bros. Pictures, protagonizado por Austin Butler y el ganador del Oscar Tom Hanks','Elvis',2,7,2),
(12,'Rob Minkoff',97,'2022-10-29 00:00:00','img/peliculas/perro-samurai.jpg',
'','Un desventurado sabueso llamado Hank\n 
       llega a un pueblo lleno de gatos que necesitan un héroe para que los defienda contra un cruel villano\n
       que ideó un malvado plan para borrar su aldea del mapa','El perro Samurai',1,8,1),
(13,'Jessica M. Thompson',90,'2022-11-21 00:00:00','img/peliculas/invitacion-al-infierno.jpg',
'Alana Boden','Tras morir su hermana, Evie queda sin familiares, pero un análisis de ADN le revela la existencia de un primo distante.\n
       ','Invitación al infierno',3,6,2),
(14,'Lawrence Fowler',110,'2022-11-01 00:00:00','img/peliculas/jack-caja-maldita-2.jpg','Matt McClure',
'Al abrir una misteriosa caja sorpresa, una rica heredera y su devoto hijo hacen un pacto con un demonio que, \n
       si tiene éxito, curará su enfermedad terminal.','Jack en la caja maldita 2',3,6,3),
(15,'Jared Stern',105,'2022-06-01 00:00:00','img/peliculas/liga-de-supermascotas.jpg','Dwayne Johnson,',
'Krypto el superperro y Superman son mejores amigos inseparables que comparten\n 
      los mismos superpoderes y luchan juntos contra el crimen en Metropolis.','Dc la liga de supermascotas',1,8,4),
(16,'Kyle Balda',105,'2022-12-03 00:00:00','img/peliculas/minion.jpg','Steve Carell',
'Fanboy de un supergrupo de supervillanos conocido como Vicious 6,
 Gru trama un plan para volverse lo suficientemente malvado como para unirse a ellos,\n 
 con el respaldo de sus seguidores, los Minions.','Minions nace un villano',1,8,3),
(17,'Jordan Peele',130,'2022-05-22 00:00:00','img/peliculas/nop.jpg','Daniel Kaluuya',
'Dos rancheros de un remoto pueblo del interior de California hacen un descubrimiento tan insólito como escalofriante.','Nop',1,6,3),
(18,'Scott Derrickson',102,'2022-02-21 00:00:00','img/peliculas/telefono-negro.jpg','Ethan Hawke',
'En una ciudad de Colorado, en los años 70, un enmascarado secuestra a Finney Shaw, \n
       un chico tímido e inteligente de 13 años, y le encierra en un sótano insonorizado donde de nada sirven sus gritos.',
       'Telefono negro',4,6,1),
(19,'Taika Waititi',118,'2022-08-25 00:00:00','img/peliculas/thor-amor-trueno.jpg','Chris Hemsworth','\n
       El Dios del Trueno emprende un viaje que no se parece en nada a lo que se ha enfrentado hasta ahora: una búsqueda de la paz interior',
       'Thor amor y trueno',1,1,2),
(20,'Joseph Kosinski',131,'2022-05-26 00:00:00','img/peliculas/top-gun.jpg','Tom Cruise',
'\n       Después de más de 30 años de servicio como uno de los mejores aviadores de la Armada, Pete Maverick Mitchell está donde pertenece,\n
       como un valiente piloto de prueba y esquivando el avance en el rango que lo pondría en tierra','Top gun Maverick',2,1,4),
(21,'David Leitch',125,'2022-06-26 00:00:00','img/peliculas/tren-bala.jpg','Brad Pitt',
'\n       Cinco asesinos a sueldo se encuentran a bordo de un tren bala que viaja de Tokio a Morioka con unas pocas paradas intermedias',
'Tren bala',2,1,5),
(22,'Scott Mann',125,'2023-06-30 00:00:00','img/peliculas/vertigo.jpg','Jeffery Dean Morgan',
'\n       Becky y Hunters son dos mejores amigas que se proponen como reto escalar una antena de más de 600 metros de altura.','Vertigo',2,1,1),
(23,'Brendan Muldowney',125,'2022-11-30 00:00:00','img/peliculas/escalera-infierno.jpg','Abby Fitz',
'\n       Aproximadamente una semana después de que la familia Woods se muda a Xaos House, su hija Ellie desaparece durante un corte de energía',
'Escalera al infierno',2,1,3),
(24,'Sang-yong Lee',106,'2024-11-25 00:00:00','img/peliculas/fuerza-bruta.jpg','Gwi-hwa Choi',
'\n       Ma Seok-do se dirige a un país extranjero para extraditar a un sospechoso.','Don Lee fuerza bruta',4,1,2),
(25,'David Gordon Green',111,'2022-11-26 00:00:00','img/peliculas/halloween.jpg','Andi Matichak',
'\n      Cuatro años después de los sucesos de Halloween Kills: \n      La Noche Aún No Termina,
 Laurie vive con su nieta Allyson y está terminando de escribir su novela autobiográfica.','Halloween la noche final',3,6,1),
(26,'Natalia Beristáin',105,'2022-11-22 00:00:00','img/peliculas/ruido.jpg','Julieta Egurrola',
'En pleno siglo 21 y en uno de los países más violentos para con sus mujeres, 
RUIDO narra el viaje y el dolor de una madre ante la desaparición de su hija y de su propia vida.','Ruido',2,5,2);

insert into pelicula (id,director,duracion,fechaEstreno,poster,protagonista,sinopsis,titulo,clasificacionPelicula_id,genero_id,calificacion) values
		 (1,'Robert Zemeckis','120','2022-11-22','img/peliculas/backtothefuture.jpg','Michael J. Fox','Marty McFly, a 17-year-old high school student,
		 is accidentally sent 30 years into the past in a time-traveling DeLorean invented by his close friend,
		 the maverick scientist Doc Brown.','Back to the Future',2,1,5),
		 (2,'Steven Spielberg','120','2022-11-22','img/peliculas/raidersofthelostark.jpg','Harrison Ford','Archaeology professor Indiana Jones ventures to seize a 
		 biblical artefact known as the Ark of the Covenant. While doing so,
		 he puts up a fight against Renee and a troop of Nazis.','Indiana Jones: Raiders of the Lost Ark',2,1,5);

 insert into funcion (id,fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
  (99,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,2,2),
  (100,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,2,3),
  (101,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,3,4),
  (102,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,3,5),
  (103,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,4,6),
  (104,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,4,7),
  (105,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,5,8),
  (106,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,5,1),
  (107,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,6,2),
  (108,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,6,3),
  (110,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,7,4),
  (111,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,7,5),
  (112,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,8,6),
  (113,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,8,7),
  (114,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,9,8),
  (115,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,9,1),
  (116,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,10,2),
  (117,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,10,3),
  (118,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,11,4),
  (119,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,11,5),
  (120,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,12,6),
  (121,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,12,7),
  (122,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,13,8),
  (123,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,13,9),
  (124,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,14,1),
  (125,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,14,2),
  (126,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,15,3),
  (127,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,15,4),
  (128,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,16,5),
  (129,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,16,6),
  (130,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,17,7),
  (131,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,17,8),
  (132,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,18,9),
  (133,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,18,8),
  (134,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,19,7),
  (135,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,19,6),
  (136,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,20,8),
  (137,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,20,9),
  (138,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,21,8),
  (139,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,21,7),
  (140,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,22,6),
  (141,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,22,8),
  (142,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,23,9),
  (143,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,23,8),
  (144,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,24,7),
  (145,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,24,6),
  (146,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,25,7),
  (147,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,25,6),
  (148,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,26,7),
  (149,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,26,6);

insert into etiqueta (id,descripcion) values
(1,'Susto'),
(2,'Pelea'),
(3,'Fascinante'),
(4,'Tenebroso'),
(5,'Ciencia Ficción'),
(6,'Emoción'),
(7,'Animación'),
(8,'Informativa'),
(9,'Descriptiva'),
(10,'Emotivo'),
(11,'Impactante'),
(12,'Tensión'),
(13,'Para niños'),
(14,'Historica'),
(15,'Interesante'),
(16,'Optimista'),
(17,'Emocionante'),
(18,'Viaje'),
(19,'Tesoro'),
(20,'Fantasia'),
(21,'Romantico'),
(22,'Divertida'),
(23,'Inquietante'),
(24,'Adultos'),
(25,'Acción'),
(26,'Drama');  



insert into etiquetapelicula (id,etiqueta_id,pelicula_id) values
                    (1,16,1),
                    (2,17,1),
                    (3,18,1),
                    (4,19,2),
                    (5,10,2),
                    (6,20,2),
                    (7,21,3),
                    (8,15,3),
                    (9,3,3),
					(10,1,4),
                    (11,4,4),
                    (12,23,4),
                    (13,1,5),
                    (14,4,5),
                    (15,23,5),
                    (16,1,6),
                    (17,4,6),
					(18,23,6),
                    (19,8,7),
					(20,10,7),
                    (21,14,7),
                    (22,8,8),
                    (23,10,8),
                    (24,14,8),
                    (25,2,9),
                    (26,5,9),
                    (27,7,9),
                    (28,24,10),
					(29,8,10),
                    (30,9,10),
                    (31,8,11),
                    (32,3,11),
                    (33,6,11),
                    (34,13,12),
                    (35,7,12),
                    (36,22,12),
                    (37,1,13),
					(38,4,13),
					(39,23,13),
					(40,4,14),
					(41,2,14),
					(42,23,14),
					(43,7,15),
					(44,13,15),
					(45,3,15),
					(46,7,16),
					(47,13,16),
					(48,3,16),
                    (49,12,17),
					(50,1,17),
					(51,4,17),
                    (52,1,18),
					(53,12,18),
					(54,4,18),
                    (55,5,19),
					(56,20,19),
					(57,5,19),
                    (58,25,20),
					(59,2,20),
					(60,3,20),
                    (61,25,21),
					(62,23,21),
					(63,3,21),
                    (64,3,22),
					(65,23,22),
					(66,3,22),
                    (67,1,23),
					(68,4,23),
					(69,3,23),
                    (70,2,24),
					(71,15,24),
					(72,17,24),
					(73,1,25),
					(74,4,25),
					(75,12,25),
                    (76,11,25),
					(77,23,25),
					(78,26,25);

insert into valoracion(comentario, puntos, pelicula_id, usuario_id)
values ('Muy buena',4,1,1),
       ('Me gusto',3,1,2),
       ('No me gusto',2,1,3),
       ('Muy buena',5,2,1),
       ('Me gusto',3,2,2),
       ('No me gusto',1,2,3),
       ('Excelente',5,3,1),
       ('Muy buena. La recomiendo',5,3,2),
       ('No me gusto tanto',3,3,3),
       ('Muy buena',4,4,1),
       ('Me gusto',3,4,2),
       ('No me gusto',1,4,3),
       ('Muy buena',4,5,1),
       ('Me gusto',4,5,2),
       ('Excelente',5,5,3),
       ('Me encanto',4,6,1),
       ('Me gusto',3,6,2),
       ('Horrible',1,6,3),
       ('Muy buena',4,7,1),
       ('Me gusto',4,7,2),
       ('Malisima',2,7,3),
       ('Buenisima',5,8,1),
       ('Me gusto',4,8,2),
       ('Aburrida',2,8,3),
       ('La recomiendo',4,9,1),
       ('Me gusto',5,9,2),
       ('No me gusto. Esperaba mas',2,9,3),
       ('Me encanta',4,10,1),
       ('Buena peli',3,10,2),
       ('No me gusto',2,10,3),
       ('Muy buena',4,11,1),
       ('Me gusto',3,11,2),
       ('No me gusto',2,11,3),
       ('Muy buena',5,12,1),
       ('Me gusto',3,12,2),
       ('No me gusto',1,12,3),
       ('Excelente',5,13,1),
       ('Muy buena. La recomiendo',5,13,2),
       ('No me gusto tanto',3,13,3),
       ('Muy buena',4,14,1),
       ('Me gusto',3,14,2),
       ('No me gusto',1,14,3),
       ('Muy buena',4,15,1),
       ('Me gusto',4,15,2),
       ('Excelente',5,15,3),
       ('Me encanto',4,16,1),
       ('Me gusto',3,16,2),
       ('Horrible',1,16,3),
       ('Muy buena',4,17,1),
       ('Me gusto',4,17,2),
       ('Malisima',2,17,3),
       ('Buenisima',5,18,1),
       ('Me gusto',4,18,2),
       ('Aburrida',2,18,3),
       ('La recomiendo',4,19,1),
       ('Me gusto',5,19,2),
       ('No me gusto. Esperaba mas',2,19,3),
       ('Me encanta',4,20,1),
       ('Buena peli',3,20,2),
       ('No me gusto',2,20,3),
       ('Buenisima',5,21,1),
       ('Me gusto',4,21,2),
       ('Aburrida',2,21,3),
       ('La recomiendo',4,22,1),
       ('Me gusto',5,22,2),
       ('No me gusto. Esperaba mas',2,22,3),
       ('Me encanta',4,23,1),
       ('Buena peli',3,23,2),
       ('No me gusto',2,23,3);

	