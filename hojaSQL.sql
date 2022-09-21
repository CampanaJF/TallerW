/*drop database cineclub;
create schema cineclub; */
use cineclub;

insert into usuario (email,nombre,password) values
					('pacofranco45@gmail.com','Franco','1234'),
                    ('mail@mail.com','main','1234'),
                    ('admin','admin','admin');

select * from usuario;	


insert into tiposala (tipo) values ('2D'),('3D'),('Realidad Aumentada'); /* Se podria estandarizar la cantidad de asientos por tipo de sala */

insert into sala (asientosTotales,cine,nombre,tipoSala_id) values
				 (150,'Cine Numero 1','Sala A',1),
				 (100,'Cine Numero 1','Sala B',2),
				 (25,'Segunda','Sala 1',3),
				 (100,'Segunda','Sala 2',2),
                 (100,'El Tercero','Sala Q',2),
                 (150,'El Tercero','Sala 9',1);
                 
insert into pelicula (director,poster,protagonista,sinopsis,titulo) values
 ('Robert Zemeckis','backtothefuture','Michael J. Fox','Marty McFly, a 17-year-old high school student,
 is accidentally sent 30 years into the past in a time-traveling DeLorean invented by his close friend,
 the maverick scientist Doc Brown.','Back to the Future'),
 ('Steven Spielberg','raidersofthelostark','Harrison Ford','Archaeology professor Indiana Jones ventures to seize a 
 biblical artefact known as the Ark of the Covenant. While doing so,
 he puts up a fight against Renee and a troop of Nazis.','Indiana Jones: Raiders of the Lost Ark');
 
insert into funcion (lenguaje,pelicula_id,sala_id) values
					('Castellano',1,3),
                    ('Ingles',1,2),
                    ('Ingles',2,5),
                    ('Castellano',2,6);
                    
insert into entrada (asiento,pelicula,sala,funcion_id,usuario_id,precio) values
			(1,'Indiana Jones: Raiders of the Lost Ark','Sala Q',3,2,1500.00),
            (1,'Back to the Future','Sala 1',1,2,2000.00);
            
            
