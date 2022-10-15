
drop database cineclub;
create schema cineclub; 
use cineclub;

/*
select * from cine;

select * from cinepelicula;
*/


insert into usuario (email,nombre,password) values
					('pacofranco45@gmail.com','Franco','1234'),
                    ('mail@mail.com','main','1234'),
                    ('admin','admin','admin');

insert into formato (tipo) values
 ('2D'),('3D'),('Realidad Aumentada'); 

 select *
 from formato;
 insert into cine (locacion,nombre) values
				  ('Calle Falsa 123','Cine Numero 1'),
				  ('Baker Street','Gran Cine');
select *
from pelicula;
/* Se podria estandarizar la cantidad de asientos*/

insert into sala (asientosTotales,nombreSala,cine_id) values
				 (150,'Sala A',1),
				 (100,'Sala B',2),
				 (25,'Sala 1',1),
				 (100,'Sala 2',2),
                 (100,'Sala Q',1),
                 (150,'Sala 9',2);
                 select *
                from sala;
 
 /*                
insert into pelicula (director,poster,protagonista,sinopsis,titulo) values
 ('Robert Zemeckis','backtothefuture','Michael J. Fox','Marty McFly, a 17-year-old high school student,
 is accidentally sent 30 years into the past in a time-traveling DeLorean invented by his close friend,
 the maverick scientist Doc Brown.','Back to the Future'),
 ('Steven Spielberg','raidersofthelostark','Harrison Ford','Archaeology professor Indiana Jones ventures to seize a 
 biblical artefact known as the Ark of the Covenant. While doing so,
 he puts up a fight against Renee and a troop of Nazis.','Indiana Jones: Raiders of the Lost Ark');
 */
 
insert into funcion (horario,lenguaje,subtitulos,cine_id,pelicula_id,formato_id,sala_id) values
					(13,'Castellano',false,1,3,1,1),
                    (13,'Ingles',true,2,4,3,1),
                    (17,'Ingles',false,2,5,2,1),
                    (17,'Castellano',false,1,6,1,2);
                    
                    select *
                    from funcion;
                    
insert into entrada (asiento,pelicula,funcion_id,usuario_id,precio) values
			(1,'30 noches con mi ex',3,2,1500.00),
            (1,'Bienvenidos al infierno',1,2,2000.00);
       
	

    
    INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ( 'Adrian Suar', '300', '2022-09-29', 
       'img/peliculas/30-noches-con-mi-ex.jpg', 'Adrian Suar', 'En esta oportunidad, Adrián Suar interpreta a El Turbo, quien luego de años de separado de La Loba, acepta, por pedido de la hija que tienen en común,
       convivir durante 30 días con su ex mujer,
       que viene de una larga internación psiquiátrica', '30 noches con mi ex', '2', '4');
    
    INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Zach Cregger', '103', '2022-09-29', 
       'img/peliculas/barbaro.jpg', 'Danny Chan', 'Una joven que viaja a Detroit para una entrevista de trabajo reserva alojamiento en la ciudad.
       Pero cuando llega tarde en la noche, descubre que la casa fue reservada a dos personas simultáneamente y que hay un extraño alojándose allí', 'Barbaro', '3', '6');
    
    INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Jimena Monteoliva', '205', '2022-07-22', 
       'img/peliculas/bienvenidos-al-infierno.jpg', 'Constanza Cardillo', 'La joven Lucía se enamora del “Monje”, el cantante de una banda de pelilargos que emula la estética de Kiss
       y que se la lleva a vivir a un aguantadero.', 'Bienvenidos al infierno', '3', '6');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Jimena Monteoliva', '89', '2022-07-20', 
       'img/peliculas/carajita.jpg', 'Ulla Prida', 'La joven Lucía se enamora del “Monje”, el cantante de una banda de pelilargos que emula la estética de Kiss
       y que se la lleva a vivir a un aguantadero.', 'Carajita', '1', '5');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Lucas Combina', '130', '2022-08-17', 
       'img/peliculas/crimen-argentino.jpg', 'Ulla Prid', 'Sara (17 años) y su niñera Yarisa (36) tienen una relación que parece trascender su clase social: son lo más parecido a una hija y una madre,
       pero un accidente irrumpe en sus vidas y pone a prueba la inocente ilusión de que nada las separará.', 'Un crimen argentino', '1', '5');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Julio Chavez', '82', '2022-07-12', 
       'img/peliculas/cuando-la-miro.jpg', 'Julio Chávez','Javier es un artista plástico que pasa sus días sin sobresaltos.
       Un día emprende un extraño proyecto: filmar a su madre.', 'Cuando la miro', '2', '5');
    
     INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ( 'Tetsuro Kodama', '100', '2022-09-13', 
       'img/peliculas/db-super.jpg', '', 'Son Goku destruyó en su momento a la Patrulla Roja. 
       Ahora, ciertos individuos han decidido continuar con su legado y han creado a los androides definitivos: Gamma 1 y Gamma 2.', 'Dragon ball Super Hero', '2', '2');
    
	 INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Fernando Sirianni', '103', '2022-05-12', 
       'img/peliculas/el-paraiso.jpg', '','Es la historia de Magdalena y Anna Scilko, hermanas inmigrantes que llegan de Polonia a Buenos Aires con la esperanza
       de un nuevo futuro.', 'El paraiso', '5', '5');
    
    
	   INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ( 'Baz Luhrmann', '280', '2022-10-11', 
       'img/peliculas/elvis.jpg', 'Austin Butler', 'El visionario cineasta nominado al Oscar Baz Luhrmann, 
        llega "Elvis", el drama de Warner Bros. Pictures, protagonizado por Austin Butler y el ganador del Oscar Tom Hanks', 'Elvis', '2', '7');
       
		
        INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Rob Minkoff', '97', '2022-09-29', 
       'img/peliculas/perro-samurai.jpg', '', 'Un desventurado sabueso llamado Hank
        llega a un pueblo lleno de gatos que necesitan un héroe para que los defienda contra un cruel villano
        que ideó un malvado plan para borrar su aldea del mapa', 'El perro Samurai', '1', '8');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Jessica M. Thompson', '90', '2022-09-21', 
       'img/peliculas/invitacion-al-infierno.jpg','Alana Boden','Tras morir su hermana, Evie queda sin familiares, pero un análisis de ADN le revela la existencia de un primo distante.
       ',  'Invitación al infierno', '3', '6');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Lawrence Fowler', '110', '2022-09-01', 
       'img/peliculas/jack-caja-maldita-2.jpg', 'Matt McClure','Al abrir una misteriosa caja sorpresa, una rica heredera y su devoto hijo hacen un pacto con un demonio que, 
       si tiene éxito, curará su enfermedad terminal.', 'Jack en la caja maldita 2', '3', '6');
	   
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Jared Stern', '105', '2022-06-01', 
       'img/peliculas/liga-de-supermascotas.jpg', 'Dwayne Johnson,','Krypto el superperro y Superman son mejores amigos inseparables que comparten
       los mismos superpoderes y luchan juntos contra el crimen en Metropolis.', 'Dc la liga de supermascotas', '1', '8');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Kyle Balda', '105', '2022-06-03', 
       'img/peliculas/minion.jpg', 'Steve Carell','Fanboy de un supergrupo de supervillanos conocido como Vicious 6, Gru trama un plan para volverse lo suficientemente malvado como para unirse a ellos,
       con el respaldo de sus seguidores, los Minions.', 'Minions nace un villano', '1', '8');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Jordan Peele', '130', '2022-05-22', 
       'img/peliculas/nop.jpg', 'Daniel Kaluuya','Dos rancheros de un remoto pueblo del interior de California hacen un descubrimiento tan insólito como escalofriante.',
       'Nop', '1', '6');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Scott Derrickson', '102', '2022-02-21', 
       'img/peliculas/telefono-negro.jpg','Ethan Hawke','En una ciudad de Colorado, en los años 70, un enmascarado secuestra a Finney Shaw, 
       un chico tímido e inteligente de 13 años, y le encierra en un sótano insonorizado donde de nada sirven sus gritos.', 'Telefono negro', '4', '6');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Taika Waititi', '118', '2022-08-25', 
       'img/peliculas/thor-amor-trueno.jpg', 'Chris Hemsworth','
       El Dios del Trueno emprende un viaje que no se parece en nada a lo que se ha enfrentado hasta ahora: una búsqueda de la paz interior',
       'Thor amor y trueno', '1', '1');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Joseph Kosinski', '131', '2022-05-26', 
       'img/peliculas/top-gun.jpg', 'Tom Cruise','
       Después de más de 30 años de servicio como uno de los mejores aviadores de la Armada, Pete Maverick Mitchell está donde pertenece,
       como un valiente piloto de prueba y esquivando el avance en el rango que lo pondría en tierra',
       'Top gun Maverick', '2', '1');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('David Leitch', '125', '2022-06-26', 
       'img/peliculas/tren-bala.jpg', 'Brad Pitt','
       Cinco asesinos a sueldo se encuentran a bordo de un tren bala que viaja de Tokio a Morioka con unas pocas paradas intermedias',
       'Tren bala', '2', '1');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Scott Mann', '125', '2022-06-30', 
       'img/peliculas/vertigo.jpg', 'Jeffery Dean Morgan','
       Becky y Hunters son dos mejores amigas que se proponen como reto escalar una antena de más de 600 metros de altura.',
       'Vertigo', '2', '1');

	INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Brendan Muldowney', '125', '2022-11-30', 
       'img/peliculas/escalera-infierno.jpg', 'Abby Fitz','
       Aproximadamente una semana después de que la familia Woods se muda a Xaos House, su hija Ellie desaparece durante un corte de energía',
       'Escalera al infierno', '2', '1');
       
        INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Sang-yong Lee', '106', '2022-11-25', 
       'img/peliculas/fuerza-bruta.jpg', 'Gwi-hwa Choi','
       Ma Seok-do se dirige a un país extranjero para extraditar a un sospechoso.',
       'Don Lee fuerza bruta', '4', '1');
       
        INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('David Gordon Green', '111', '2022-11-26', 
       'img/peliculas/halloween.jpg', 'Andi Matichak','
      Cuatro años después de los sucesos de Halloween Kills: 
      La Noche Aún No Termina, Laurie vive con su nieta Allyson y está terminando de escribir su novela autobiográfica.',
       'Halloween la noche final', '3', '6');
       
        INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Natalia Beristáin', '105', '2022-11-22', 
       'img/peliculas/ruido.jpg', 'Julieta Egurrola','En pleno siglo 21 y en uno de los países más violentos para con sus mujeres, RUIDO narra el viaje y el dolor de una madre ante la desaparición de su hija y de su propia vida.',
       'Ruido', '2', '5');
         
         select *
         from pelicula;
         
         
INSERT INTO genero (id,descripcion)
VALUES ("1","Acción");
INSERT INTO genero (id,descripcion)
VALUES ("2","Anime");
INSERT INTO genero (id,descripcion)
VALUES ("3","Suspenso");
INSERT INTO genero (id,descripcion)
VALUES ("4","Comedia");
INSERT INTO genero (id,descripcion)
VALUES ("5","Drama");
INSERT INTO genero (id,descripcion)
VALUES ("6","Terror");
INSERT INTO genero (id,descripcion)
VALUES ("7","Biografica");
INSERT INTO genero (id,descripcion)
VALUES ("8","Animación");

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

select *
from pelicula;

insert into cine (locacion,nombreCine) values ('Calle Falsa 123','CineClub');

insert into sala (asientosTotales,nombreSala,cine_id) values
				 (150,'Sala J',1),
				 (100,'Sala K',1),
				 (25,'Sala L',1);
 
  insert into cine (locacion,nombreCine) values ('Italia 913','Cine Magistral');
 
insert into sala (asientosTotales,nombreSala,cine_id) values                
				 (100,'Sala A',2),
                 (100,'Sala B',2),
                 (25,'Sala C',2);
                 
 insert into cine (locacion,nombreCine) values ('Etiopia 1865','Grand');
                  
insert into sala (asientosTotales,nombreSala,cine_id) values                
				 (100,'Sala 1',3),
                 (100,'Sala 2',3),
                 (25,'Sala 3',3);
                 
insert into cine (locacion,nombreCine) values ('Irlanda 993','Sunshine');          
      
insert into sala (asientosTotales,nombreSala,cine_id) values                
							(100,'Sala X',4),
							(25,'Sala Z',4),
							(150,'Sala Y',4);

select * from pelicula;

 insert into cinepelicula(cine_id,pelicula_id) values
						  (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),
                          (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),
						  (3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7),
                          (4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7);

insert into funcion (horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-09-22 17:00','Japones',750.00,true,1,1,1),
                    ('2022-09-22 13:00','Japones',750.00,true,2,1,1),
                    ('2022-09-22 17:00','Castellano',1050.00,false,3,1,3),
                    ('2022-09-22 20:00','Japones',750.00,true,2,1,2);

insert into funcion (horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-09-22 17:00','Japones',750.00,true,1,1,4),
                    ('2022-09-22 13:00','Japones',750.00,true,2,1,4),
                    ('2022-09-22 17:00','Castellano',1050.00,false,3,1,5),
                    ('2022-09-22 20:00','Japones',750.00,true,2,1,6);
                    
insert into funcion (horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-09-22 17:00','Japones',750.00,true,1,1,7),
                    ('2022-09-22 13:00','Japones',750.00,true,2,1,7),
                    ('2022-09-22 17:00','Castellano',1050.00,false,3,1,8),
                    ('2022-09-22 20:00','Japones',750.00,true,2,1,9);
                    
insert into funcion (horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-09-22 17:00','Japones',750.00,true,1,1,10),
                    ('2022-09-22 13:00','Japones',750.00,true,2,1,10),
                    ('2022-09-22 17:00','Castellano',1050.00,false,3,1,11),
                    ('2022-09-22 20:00','Japones',750.00,true,2,1,12);
                    
insert into funcion (horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-09-22 15:00','Japones',750.00,true,1,1,1),
                    ('2022-09-22 23:00','Japones',750.00,true,2,1,1),
                    ('2022-09-22 12:00','Castellano',1050.00,false,3,1,2),
                    ('2022-09-22 23:00','Japones',750.00,true,2,1,2);
                    
select * from funcion
left join sala on funcion.sala_id = sala.id where
pelicula_id = 2 and sala.id = 1; 

select * from pelicula p
join clasificacionPelicula c on p.clasificacionPelicula_id = c.id;

select * from pelicula p
join genero g on p.genero_id = g.id
where g.id = 2;
          
select count(*) from funcion
join sala on funcion.sala_id = sala.id
join asiento on sala.id = asiento.sala_id
where funcion.id = 1 and asiento.ocupado = 1;	

select count(*) from entrada
join funcion on entrada.funcion_id = funcion.id 
join asiento on entrada.asiento_id = asiento.id	
where funcion.id = 1 and asiento.ocupado = 1;	  


select * from entrada;   

select * from funcion;

delete from entrada where id is not null; 

