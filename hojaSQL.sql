/* Produccion */
drop database cineclub;
create schema cineclub; 
use cineclub;

insert into usuario (email,nombre,password) values
					('pacofranco45@gmail.com','Franco','1234'),
                    ('mail@mail.com','main','1234'),
                    ('admin','admin','admin');

insert into formato (tipo) values
 ('2D'),('3D'),('Realidad Aumentada'); 
 
 insert into cine (locacion,nombre) values
				  ('Calle Falsa 123','Cine Numero 1'),
				  ('Baker Street','Gran Cine');

/* Se podria estandarizar la cantidad de asientos*/
insert into sala (asientosTotales,nombreSala,cine_id) values
				 (150,'Sala A',1),
				 (100,'Sala B',2),
				 (25,'Sala 1',1),
				 (100,'Sala 2',2),
                 (100,'Sala Q',1),
                 (150,'Sala 9',2);
                 
insert into pelicula (director,poster,protagonista,sinopsis,titulo) values
 ('Robert Zemeckis','backtothefuture','Michael J. Fox','Marty McFly, a 17-year-old high school student,
 is accidentally sent 30 years into the past in a time-traveling DeLorean invented by his close friend,
 the maverick scientist Doc Brown.','Back to the Future'),
 ('Steven Spielberg','raidersofthelostark','Harrison Ford','Archaeology professor Indiana Jones ventures to seize a 
 biblical artefact known as the Ark of the Covenant. While doing so,
 he puts up a fight against Renee and a troop of Nazis.','Indiana Jones: Raiders of the Lost Ark');
 
insert into funcion (horario,lenguaje,subtitulos,cine_id,pelicula_id,formato_id,sala_id) values
					(13,'Castellano',false,1,1,1,1),
                    (13,'Ingles',true,2,1,2,1),
                    (17,'Ingles',false,2,2,2,1),
                    (17,'Castellano',false,1,2,1,2);
                    
insert into entrada (asiento,pelicula,funcion_id,usuario_id,precio) values
			(1,'Indiana Jones: Raiders of the Lost Ark',3,2,1500.00),
            (1,'Back to the Future',1,2,2000.00);
            
/* Test */            
drop database cinetest;
create schema cinetest; 
use cinetest;

insert into usuario (email,nombre,password) values
					('pacofranco45@gmail.com','Franco','1234'),
                    ('mail@mail.com','main','1234'),
                    ('admin','admin','admin');

insert into formato (tipo) values
 ('2D'),('3D'),('Realidad Aumentada'); 
 
 insert into cine (locacion,nombre) values
				  ('Calle Falsa 123','Cine Numero 1'),
				  ('Baker Street','Gran Cine');

select * from cine;

/* Se podria estandarizar la cantidad de asientos*/
insert into sala (asientosTotales,nombreSala,cine_id) values
				 (150,'Sala A',1),
				 (100,'Sala B',2),
				 (25,'Sala 1',1),
				 (100,'Sala 2',2),
                 (100,'Sala Q',1),
                 (150,'Sala 9',2);
                 
insert into pelicula (director,poster,protagonista,sinopsis,titulo) values
 ('Robert Zemeckis','backtothefuture','Michael J. Fox','Marty McFly, a 17-year-old high school student,
 is accidentally sent 30 years into the past in a time-traveling DeLorean invented by his close friend,
 the maverick scientist Doc Brown.','Back to the Future'),
 ('Steven Spielberg','raidersofthelostark','Harrison Ford','Archaeology professor Indiana Jones ventures to seize a 
 biblical artefact known as the Ark of the Covenant. While doing so,
 he puts up a fight against Renee and a troop of Nazis.','Indiana Jones: Raiders of the Lost Ark');
 
insert into funcion (horario,lenguaje,subtitulos,cine_id,pelicula_id,formato_id,sala_id) values
					(13,'Castellano',false,1,1,1,1),
                    (13,'Ingles',true,2,1,2,1),
                    (17,'Ingles',false,2,2,2,1),
                    (17,'Castellano',false,1,2,1,2);
                    
insert into entrada (asiento,pelicula,funcion_id,usuario_id,precio) values
			(1,'Indiana Jones: Raiders of the Lost Ark',3,2,1500.00),
            (1,'Back to the Future',1,2,2000.00);
            
            