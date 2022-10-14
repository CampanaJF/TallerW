drop database cineclub;
create schema cineclub; 
use cineclub;

insert into usuario (email,nombre,password) values
					('pacofranco45@gmail.com','Franco','1234'),
                    ('mail@mail.com','main','1234'),
                    ('admin','admin','admin');

insert into formato (tipo) values
 ('2D'),('3D'),('Realidad Aumentada'); 
 
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


INSERT INTO genero (id,descripcion)
VALUES ("1","Acción");
INSERT INTO genero (id,descripcion)
VALUES ("2","Anime");
INSERT INTO genero (id,descripcion)
VALUES ("3","Aventuras");
INSERT INTO genero (id,descripcion)
VALUES ("4","Comedia");
INSERT INTO genero (id,descripcion)
VALUES ("5","Trama");
INSERT INTO genero (id,descripcion)
VALUES ("6","Terror");
INSERT INTO genero (id,descripcion)
VALUES ("7","Biografica");

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
INSERT INTO clasificacionpelicula (id,descripcion)
VALUES ("6","ATP");
	
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ( 'Tetsuro Kodama', '100', '2022-09-13', 
       'img/peliculas/db-super.jpg', 'Gohan', 'Son Goku destruyó en su momento a la Patrulla Roja. 
       Ahora, ciertos individuos han decidido continuar con su legado y han creado a los androides definitivos: Gamma 1 y Gamma 2.', 'Dragon ball Super Hero', '2', '2');
	
		INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Zach Cregger', '103', '2022-09-29', 
       'img/peliculas/barbaro.jpg', 'Danny Chan', 'Una joven que viaja a Detroit para una entrevista de trabajo reserva alojamiento en la ciudad.
       Pero cuando llega tarde en la noche, descubre que la casa fue reservada a dos personas simultáneamente y que hay un extraño alojándose allí', 'Barbaro', '2', '6');
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Jimena MonteOliva', '250', '2022-09-12', 
       'img/peliculas/bienvenidos-al-infierno.jpg', 'Lucia', 'Lucía vive en una cabaña en medio de un bosque con su abuela, una misteriosa anciana muda', 
       'Bienvenidos Al Infierno', '3', '6');
       
         INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Baz Luhrmann', '280', '2022-09-22', 
       'img/peliculas/elvis.jpg', 'Austin Butler', 'el visionario cineasta nominado al Oscar Baz Luhrmann, 
       llega "Elvis", el drama de Warner Bros. Pictures, protagonizado por Austin Butler y el ganador del Oscar Tom Hanks', 
       'Elvis', '2', '7');
       
       INSERT INTO `cineclub`.`pelicula` 
       (`director`, `duracion`, `fechaEstreno`, `poster`, `protagonista`, `sinopsis`, `titulo`, `clasificacionPelicula_id`, `genero_id`) 
       VALUES ('Rob Minkoff', '97', '2022-09-22', 
       'img/peliculas/perro-samurai.jpg', '', 'Un desventurado sabueso llamado Hank
       llega a un pueblo lleno de gatos que necesitan un héroe para que los defienda contra un cruel villano
       que ideó un malvado plan para borrar su aldea del mapa', 
       'El perro samurai', '1', '5');
       
                      
		insert into pelicula (director,duracion,poster,protagonista,sinopsis,titulo,clasificacionPelicula_id,genero_id) values
		 ('Robert Zemeckis','120','img/peliculas/backtothefuture.jpg','Michael J. Fox','Marty McFly, a 17-year-old high school student,
		 is accidentally sent 30 years into the past in a time-traveling DeLorean invented by his close friend,
		 the maverick scientist Doc Brown.','Back to the Future','2','3'),
		 ('Steven Spielberg','120','img/peliculas/raidersofthelostark.jpg','Harrison Ford','Archaeology professor Indiana Jones ventures to seize a 
		 biblical artefact known as the Ark of the Covenant. While doing so,
		 he puts up a fight against Renee and a troop of Nazis.','Indiana Jones: Raiders of the Lost Ark','2','3');
		 
    
 insert into cinepelicula(cine_id,pelicula_id) values
						  (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),
                          (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),
						  (3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7),
                          (4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7);

insert into funcion (fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-09-22','17:00','Japones',750.00,true,1,1,1),
                    ('2022-09-22','13:00','Japones',750.00,true,2,1,1),
                    ('2022-09-22','17:00','Castellano',1050.00,false,3,1,3),
                    ('2022-09-22','20:00','Japones',750.00,true,2,1,2);

insert into funcion (fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-09-22','17:00','Japones',750.00,true,1,1,4),
                    ('2022-09-22','13:00','Japones',750.00,true,2,1,4),
                    ('2022-09-22','17:00','Castellano',1050.00,false,3,1,5),
                    ('2022-09-22','20:00','Japones',750.00,true,2,1,6);
                    
insert into funcion (fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-09-22','17:00','Japones',750.00,true,1,1,7),
                    ('2022-09-22','13:00','Japones',750.00,true,2,1,7),
                    ('2022-09-22','17:00','Castellano',1050.00,false,3,1,8),
                    ('2022-09-22','20:00','Japones',750.00,true,2,1,9);
                    
insert into funcion (fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('22-09-22','17:00','Japones',750.00,true,1,1,10),
                    ('22-09-22','13:00','Japones',750.00,true,2,1,10),
                    ('22-09-22','17:00','Castellano',1050.00,false,3,1,11),
                    ('22-09-22','20:00','Japones',750.00,true,2,1,12);
                    
insert into funcion (fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-09-22','15:00','Japones',750.00,true,1,1,1),
                    ('2022-09-22','23:00','Japones',750.00,true,2,1,1),
                    ('2022-09-22','12:00','Castellano',1050.00,false,3,1,2),
                    ('2022-09-22','23:00','Japones',750.00,true,2,1,2);
                    
select * from funcion
left join sala on funcion.sala_id = sala.id where
pelicula_id = 1 and sala.id = 1; 

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