use cineclub;

insert into funcion (fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-10-26 21:00','21:00','Ingles',750.00,true,1,1,13);


select * from sala;


select * from funcion;

select * from funcion;
call EP_crearAsientosYEntradasVacias(6);

call EP_obtenerCantidadDeAsientosDeUnaFuncion(4,@cantidadACrear);
select @cantidadACrear;

select * from entrada;

select * from asiento where ocupado is true;

update asiento set ocupado = 1 where id = 100876;

drop procedure if exists EP_crearAsientosYEntradasVacias;
DELIMITER //
create procedure EP_crearAsientosYEntradasVacias(in funcion_id int)
begin
		declare contador int default 1;
        
        call EP_obtenerCantidadDeAsientosDeUnaFuncion(funcion_id,@cantidadACrear);
        
	while contador < @cantidadACrear+1
	do
	
        insert into asiento (numero,ocupado,ubicacion) values (contador,false,'A'); 
        
        set @idAsiento = (select id from asiento order by id desc limit 1);
        
        insert into entrada (funcion_id,asiento_id) values (funcion_id,@idAsiento);
        
        set @idEntrada = (select id from entrada order by id desc limit 1);
        
        update asiento set entrada_id = @idEntrada where id = @idAsiento;
        
        set contador = contador + 1;
        
	end while;
        
end//
DELIMITER ;


drop procedure if exists EP_obtenerCantidadDeAsientosDeUnaFuncion;
DELIMITER //
create procedure EP_obtenerCantidadDeAsientosDeUnaFuncion(in funcion_id int,out _asientosTotales int)
		
        select s.asientosTotales into _asientosTotales from funcion f
        left join sala s on f.sala_id = s.id 
        where f.id = funcion_id;

end//
DELIMITER ;

select s.asientosTotales from funcion f
        left join sala s on f.sala_id = s.id 
        where f.id = 1;

insert into funcion (fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-09-20','17:00','Ingles',750.00,true,1,25,9),
                    ('2022-09-19','13:00','Ingles',750.00,true,2,25,11),
                    ('2022-09-24','17:00','Castellano',1050.00,false,3,25,12),
                    ('2022-09-25','20:00','Ingles',750.00,true,2,25,10);
                    
insert into funcion (fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-10-20','17:00','Ingles',750.00,true,1,26,10),
                    ('2022-10-19','13:00','Ingles',750.00,true,2,26,10),
                    ('2022-10-25','20:00','Ingles',750.00,true,2,26,12);

insert into funcion (fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-10-20','17:00','Ingles',750.00,true,1,25,9),
                    ('2022-10-19','13:00','Ingles',750.00,true,2,25,11),
                    ('2022-10-24','17:00','Castellano',1050.00,false,3,25,12),
                    ('2022-10-25','20:00','Ingles',750.00,true,2,25,10);
                    
insert into funcion (fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					('2022-09-20','17:00','Ingles',750.00,true,1,26,10),
                    ('2022-09-19','13:00','Ingles',750.00,true,2,26,10),
                    ('2022-09-25','20:00','Ingles',750.00,true,2,26,12);

select * from funcion;

/*
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

               
                    
                    
*/   