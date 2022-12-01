drop database cineclub;
create schema cineclub; 
use cineclub;

insert into usuario (id,email,nombre,password) values
					(1,'pacofranco45@gmail.com','Franco','1234'),
                    (2,'jucampana@alumno.unlam.edu.ar','Juan','1234'),
                    (3,'mail@mail.com','main','1234');

insert into formato (id,tipo) values
 (1,'2D'),(2,'3D'),(3,'Realidad Aumentada'); 

insert into cine (id,locacion,nombreCine) values (1,'Calle Falsa 123','CineClub');

insert into sala (id,asientosTotales,nombreSala,cine_id) values
				 (1,66,'Sala J',1),
				 (2,66,'Sala K',1),
				 (3,66,'Sala L',1);
                 
insert into cine (id,locacion,nombreCine) values (2,'Italia 913','Cine Magistral');
 
insert into sala (id,asientosTotales,nombreSala,cine_id) values                
				 (4,66,'Sala A',2),
                 (5,66,'Sala B',2),
                 (6,66,'Sala C',2);
                 
 insert into cine (id,locacion,nombreCine) values (3,'Etiopia 1865','Grand');
                  
insert into sala (id,asientosTotales,nombreSala,cine_id) values                
				 (7,66,'Sala 1',3),
                 (8,66,'Sala 2',3),
                 (9,66,'Sala 3',3);
                 
insert into cine (id,locacion,nombreCine) values (4,'Irlanda 993','Sunshine');          
      
insert into sala (id,asientosTotales,nombreSala,cine_id) values                
							(10,66,'Sala X',4),
							(11,66,'Sala Z',4),
							(12,66,'Sala Y',4);
                            
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
RUIDO narra el viaje y el dolor de una madre ante la desaparición de su hija y de su propia vida.','Ruido',2,5,2),
(27,5,'Parker Finn',105,0,'2022-12-1 00:00:00','img/peliculas/sonrie.jpg','Sosie Bacon',
'Después de ser testigo de un extraño y traumático accidente que involucró a una paciente, la Dr. Rose Cotter
 empieza a experimentar sucesos aterradores que no puede explicarse.','Sonrie',3,3),
 (28,5,' Jaume Collet-Serra',105,0,'2022-12-02 00:00:00','img/peliculas/black-adam.jpg','Dwayne Johnson',
'Casi 5,000 años después de obtener los poderes supremos de los antiguos dioses –y de ser encarcelado igual de rápido–,
 Black Adam (Johnson) se libera de su tumba terrenal','Black Adam',1,1),
 (29,3,'Chris Weitz',125,0,'2022-12-02 00:00:00','img/peliculas/crepusculo.jpg',' Kristen Stewart',
'Edward Cullen decide abandonar a Bella Swan para mantenerla alejada de los peligros del mundo vampírico. Con la ayuda de Jacob Black,
 su amigo de la infancia y miembro de la misteriosa tribu Quileute','Crepusculo',2,3),
 (30,4,'Daniel Stamm',125,0,'2022-12-02 00:00:00','img/peliculas/luz-diablo.jpg','Virginia Madsen',
'Según informes reales del Vaticano, los casos de posesión demoníaca han aumentado significativamente en los últimos años. 
','La luz del diablo',3,6),
 (31,4,'Josh Gordon',125,0,'2022-12-02 00:00:00','img/peliculas/cocodrilo.jpg','Javier Bardem',
'Basada en la serie de libros best-seller de Bernard Waber.Cuando la familia Primm se traslada a la ciudad de Nueva York.','Lilo cocodrilo',1,8),
 
 (32,4,'Ryan Coogler',125,0,'2022-12-02 00:00:00','img/peliculas/pantera-negra.jpg','Angela Bassett',
'Mientras los habitantes de Wakanda se esfuerzan embarcarse en un nuevo capítulo,
 los héroes deben unirse con la ayuda de War Dog Nakia y Everett Ross  y forjar un nuevo camino para el reino de Wakanda.','Pantera negra Wakanda por siempre',3,1),

(33,4,'Timo Vuorensola',90,0,'2022-12-02 00:00:00','img/peliculas/jeepers.jpg','Sydney Craven',
'Obligada a viajar con su novio, Laine, comienza a experimentar premoniciones asociadas con el mito urbano de The Creeper.
 ','Jeepers Creepers: La recarnacion del demonio',3,3),

  (34,4,' Néstor Mazzini',130,0,'2022-12-02 00:00:00','img/peliculas/cuando-oscurece.jpg','César Troncoso',
'Los padres de Flor están separados y ella reparte su tiempo entre ambas casas.','Cuando oscurece',5,3),
 
  (35,4,'Charlotte Wells',102,0,'2022-12-02 00:00:00','img/peliculas/aftersun.jpg','Paul Mescan',
'En un decadente complejo vacacional a fines de la década de 1990, Sophie, de 11 años, atesora el escaso tiempo junto con su cariñoso e idealista padre, Calum. ',
 'Aftersun',2,3),
 
  (36,4,'Tommy Wirkola',90,0,'2022-12-02 00:00:00','img/peliculas/noche-sin-paz.jpg',' David Harbour',
'Cuando un equipo de mercenarios irrumpe durante Nochebuena en la casa de una familia adinerada, 
tomando a todos los que están dentro como rehenes','Noche sin paz',3,4),
 
  (37,4,'James Cameron',192,0,'2022-12-02 00:00:00','img/peliculas/avatar-camino-del-agua.jpg','Sam Worthington',
'Narra la historia de la familia Sully (Jake, Neytiri y sus hijos), el peligro que los persigue,
 los esfuerzos que hacen para mantenerse a salvo.','Avatar el camino del agua',1,3),
 
  (38,4,'Luca Guadagnino',131,0,'2022-12-02 00:00:00','img/peliculas/hasta-huesos.jpg','Timothée Chalamet',
'La película, del aclamado director Luca Guadagnino, está protagonizada por Timothée Chalamet y Taylor Russell;
 y cuenta la historia de amor entre Maren, una joven que está aprendiendo a sobrevivir al margen de la sociedad.','Hasta los huesos',3,5),
 
 (39,4,' Santiago Fillol',150,0,'2022-12-02 00:00:00','img/peliculas/matadero.jpg','Julio Perillán',
'Un cineasta americano llega a la pampa argentina para rodar Matadero:
 una fábula fundacional sobre la lucha de clases entre un grupo de trabajadores asesinados por sus jefes.','Matadero',3,5),
 
 (40,4,'Fabian Forte',88,0,'2022-12-02 00:00:00','img/peliculas/legiones.jpg','German De Silva',
'Antonio Poyju es un chaman que pertenece a un linaje de sangre poderosa. Antonio se encuentra recluído en un manicomio contra su voluntad.','Legiones',3,5);

INSERT INTO `pelicula`(id,calificacion,director,duracion,enCartelera,fechaEstreno,poster,protagonista,sinopsis,titulo,clasificacionPelicula_id,genero_id) VALUES
(27,5,'Parker Finn',105,0,'2022-12-1 00:00:00','img/peliculas/sonrie.jpg','Sosie Bacon',
'Después de ser testigo de un extraño y traumático accidente que involucró a una paciente, la Dr. Rose Cotter
 empieza a experimentar sucesos aterradores que no puede explicarse.','Sonrie',3,3),
 (28,5,' Jaume Collet-Serra',105,0,'2022-12-02 00:00:00','img/peliculas/black-adam.jpg','Dwayne Johnson',
'Casi 5,000 años después de obtener los poderes supremos de los antiguos dioses –y de ser encarcelado igual de rápido–,
 Black Adam (Johnson) se libera de su tumba terrenal','Black Adam',1,1),
 (29,3,'Chris Weitz',125,0,'2022-12-02 00:00:00','img/peliculas/crepusculo.jpg',' Kristen Stewart',
'Edward Cullen decide abandonar a Bella Swan para mantenerla alejada de los peligros del mundo vampírico. Con la ayuda de Jacob Black,
 su amigo de la infancia y miembro de la misteriosa tribu Quileute','Crepusculo',2,3),
 (30,4,'Daniel Stamm',125,0,'2022-12-02 00:00:00','img/peliculas/luz-diablo.jpg','Virginia Madsen',
'Según informes reales del Vaticano, los casos de posesión demoníaca han aumentado significativamente en los últimos años. 
','La luz del diablo',3,6),
 (31,4,'Josh Gordon',125,0,'2022-12-02 00:00:00','img/peliculas/cocodrilo.jpg','Javier Bardem',
'Basada en la serie de libros best-seller de Bernard Waber.Cuando la familia Primm se traslada a la ciudad de Nueva York.','Lilo cocodrilo',1,8),
 
 (32,4,'Ryan Coogler',125,0,'2022-12-02 00:00:00','img/peliculas/pantera-negra.jpg','Angela Bassett',
'Mientras los habitantes de Wakanda se esfuerzan embarcarse en un nuevo capítulo,
 los héroes deben unirse con la ayuda de War Dog Nakia y Everett Ross  y forjar un nuevo camino para el reino de Wakanda.','Pantera negra Wakanda por siempre',3,1),

(33,4,'Timo Vuorensola',90,0,'2022-12-02 00:00:00','img/peliculas/jeepers.jpg','Sydney Craven',
'Obligada a viajar con su novio, Laine, comienza a experimentar premoniciones asociadas con el mito urbano de The Creeper.
 ','Jeepers Creepers: La recarnacion del demonio',3,3),

  (34,4,' Néstor Mazzini',130,0,'2022-12-02 00:00:00','img/peliculas/cuando-oscurece.jpg','César Troncoso',
'Los padres de Flor están separados y ella reparte su tiempo entre ambas casas.','Cuando oscurece',5,3),
 
  (35,4,'Charlotte Wells',102,0,'2022-12-02 00:00:00','img/peliculas/aftersun.jpg','Paul Mescan',
'En un decadente complejo vacacional a fines de la década de 1990, Sophie, de 11 años, atesora el escaso tiempo junto con su cariñoso e idealista padre, Calum. ',
 'Aftersun',2,3),
 
  (36,4,'Tommy Wirkola',90,0,'2022-12-02 00:00:00','img/peliculas/noche-sin-paz.jpg',' David Harbour',
'Cuando un equipo de mercenarios irrumpe durante Nochebuena en la casa de una familia adinerada, 
tomando a todos los que están dentro como rehenes','Noche sin paz',3,4),
 
  (37,4,'James Cameron',192,0,'2022-12-02 00:00:00','img/peliculas/avatar-camino-del-agua.jpg','Sam Worthington',
'Narra la historia de la familia Sully (Jake, Neytiri y sus hijos), el peligro que los persigue,
 los esfuerzos que hacen para mantenerse a salvo.','Avatar el camino del agua',1,3),
 
  (38,4,'Luca Guadagnino',131,0,'2022-12-02 00:00:00','img/peliculas/hasta-huesos.jpg','Timothée Chalamet',
'La película, del aclamado director Luca Guadagnino, está protagonizada por Timothée Chalamet y Taylor Russell;
 y cuenta la historia de amor entre Maren, una joven que está aprendiendo a sobrevivir al margen de la sociedad.','Hasta los huesos',3,5),
 
 (39,4,' Santiago Fillol',150,0,'2022-12-02 00:00:00','img/peliculas/matadero.jpg','Julio Perillán',
'Un cineasta americano llega a la pampa argentina para rodar Matadero:
 una fábula fundacional sobre la lucha de clases entre un grupo de trabajadores asesinados por sus jefes.','Matadero',3,5),
 
 (40,4,'Fabian Forte',88,0,'2022-12-02 00:00:00','img/peliculas/legiones.jpg','German De Silva',
'Antonio Poyju es un chaman que pertenece a un linaje de sangre poderosa. Antonio se encuentra recluído en un manicomio contra su voluntad.','Legiones',3,5);


 insert into etiquetapelicula (id,etiqueta_id,pelicula_id) values
 (79,4,27),
 (80,12,27),
 (81,23,27),
 (82,5,28),
 (83,20,28),
 (84,17,28),
 (85,17,29),
 (86,21,29),
 (87,20,29),
 (88,4,30),
 (89,11,30),
 (90,15,30),
 (91,5,31),
 (92,7,31),
 (93,13,31),
 (94,25,32),
 (95,2,32),
 (96,3,32),
 (97,4,33),
 (98,11,33),
 (99,15,33),
 (100,12,34),
 (101,1,34),
 (102,4,34),
 (103,21,35),
 (104,10,35),
 (105,3,35),
 (106,3,36),
 (107,22,36),
 (108,6,36),
 (109,18,37),
 (110,23,37),
 (111,20,37),
 (112,10,38),
 (113,15,38),
 (114,21,38),
 (115,14,39),
 (116,16,39),
 (117,15,39),
 (118,1,40),
 (119,4,40),
 (120,5,40);

insert into cinepelicula (id,cine_id,pelicula_id) values
(38,1,27),
(39,2,27),
(40,1,28),
(41,2,28),
(42,1,29),
(43,2,29),
(44,1,30),
(45,2,30),
(46,1,31),
(47,2,31),
(48,1,32),
(49,2,32),
(50,1,33),
(51,2,33),
(52,1,34),
(53,2,34),
(54,1,35),
(55,2,35),
(56,1,36),
(57,2,36),
(58,1,37),
(59,2,37),
(60,1,38),
(61,2,38),
(62,1,39),
(63,2,39),
(64,1,40),
(65,2,40);


/*
 insert into etiquetapelicula (id,etiqueta_id,pelicula_id) values
 (79,4,27),
 (80,12,27),
 (81,23,27),
 (82,5,28),
 (83,20,28),
 (84,17,28),
 (85,17,29),
 (86,21,29),
 (87,20,29),
 (88,4,30),
 (89,11,30),
 (90,15,30),
 (91,5,31),
 (92,7,31),
 (93,13,31),
 (94,25,32),
 (95,2,32),
 (96,3,32),
 (97,4,33),
 (98,11,33),
 (99,15,33),
 (100,12,34),
 (101,1,34),
 (102,4,34),
 (103,21,35),
 (104,10,35),
 (105,3,35),
 (106,3,36),
 (107,22,36),
 (108,6,36),
 (109,18,37),
 (110,23,37),
 (111,20,37),
 (112,10,38),
 (113,15,38),
 (114,21,38),
 (115,14,39),
 (116,16,39),
 (117,15,39),
 (118,1,40),
 (119,4,40),
 (120,5,40);
 */
insert into pelicula (id,director,duracion,fechaEstreno,poster,protagonista,sinopsis,titulo,clasificacionPelicula_id,genero_id,calificacion) values
		 (1,'Robert Zemeckis','120','2022-11-22','img/peliculas/backtothefuture.jpg','Michael J. Fox','Marty McFly, a 17-year-old high school student,
		 is accidentally sent 30 years into the past in a time-traveling DeLorean invented by his close friend,
		 the maverick scientist Doc Brown.','Back to the Future',2,1,5),
		 (2,'Steven Spielberg','120','2022-11-22','img/peliculas/raidersofthelostark.jpg','Harrison Ford','Archaeology professor Indiana Jones ventures to seize a 
		 biblical artefact known as the Ark of the Covenant. While doing so,
		 he puts up a fight against Renee and a troop of Nazis.','Indiana Jones: Raiders of the Lost Ark',2,1,5);

update pelicula set fechaEstreno = '2022-12-26' where id = 25;
update pelicula set fechaEstreno = '2022-12-26' where id = 26;

update pelicula set enCartelera = 0 where id = 1;
update pelicula set enCartelera = 0 where id = 2;
update pelicula set enCartelera = 0 where id = 3;
update pelicula set enCartelera = 0 where id = 4;
update pelicula set enCartelera = 0 where id = 5;
update pelicula set enCartelera = 0 where id = 6;
update pelicula set enCartelera = 0 where id = 7;
update pelicula set enCartelera = 0 where id = 8;
update pelicula set enCartelera = 0 where id = 9;
update pelicula set enCartelera = 0 where id = 10;
update pelicula set enCartelera = 0 where id = 11;
update pelicula set enCartelera = 0 where id = 12;
update pelicula set enCartelera = 0 where id = 13;
update pelicula set enCartelera = 0 where id = 14;
update pelicula set enCartelera = 0 where id = 15;
update pelicula set enCartelera = 0 where id = 16;
update pelicula set enCartelera = 0 where id = 17;
update pelicula set enCartelera = 0 where id = 18;
update pelicula set enCartelera = 0 where id = 19;
update pelicula set enCartelera = 0 where id = 20;
update pelicula set enCartelera = 1 where id = 21;
update pelicula set enCartelera = 1 where id = 22;
update pelicula set enCartelera = 1 where id = 23;
update pelicula set enCartelera = 1 where id = 24;
update pelicula set enCartelera = 1 where id = 25;
update pelicula set enCartelera = 1 where id = 26;

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


select * from etiquetapelicula where etiqueta_id = 16;
select * from etiquetapelicula where etiqueta_id = 17;
select * from etiquetapelicula where etiqueta_id = 18;

select * from etiquetapelicula where etiqueta_id = 19;
select * from etiquetapelicula where etiqueta_id = 20;
select * from etiquetapelicula where etiqueta_id = 10;

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

	