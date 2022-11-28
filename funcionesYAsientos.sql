use cineclub;

insert into funcion (id,fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values
					(90,'2022-11-26 21:00','21:00','Ingles',750.00,true,2,1,1),
                    (91,'2022-11-25 21:00','21:00','Ingles',750.00,false,3,1,2),
                    (92,'2022-11-24 21:00','21:00','Ingles',750.00,true,1,1,3),
                    (93,'2022-11-25 21:00','21:00','Ingles',750.00,false,1,1,4),
                    (94,'2022-11-25 21:00','17:00','Ingles',750.00,true,2,1,5),
                    (95,'2022-11-26 21:00','12:00','Ingles',750.00,true,3,1,6),
                    (96,'2022-11-25 21:00','15:00','Ingles',750.00,false,1,1,7),
                    (97,'2022-11-26 23:00','22:00','Ingles',750.00,true,2,1,8),
                    (98,'2022-11-27 23:00','22:30','Ingles',750.00,false,3,1,9),
					(190,'2022-11-25 21:00','21:00','Ingles',750.00,true,2,2,1),
                    (191,'2022-11-26 21:00','21:00','Ingles',750.00,false,3,2,2),
                    (192,'2022-11-27 21:00','21:00','Ingles',750.00,true,1,2,3),
                    (193,'2022-11-28 21:00','21:00','Ingles',750.00,false,1,2,4),
                    (194,'2022-11-26 21:00','17:00','Ingles',750.00,true,2,2,5),
                    (195,'2022-11-25 21:00','12:00','Ingles',750.00,true,3,2,6),
                    (196,'2022-11-27 21:00','15:00','Ingles',750.00,false,1,2,7),
                    (197,'2022-11-25 23:00','22:00','Ingles',750.00,true,2,2,8),
                    (198,'2022-11-25 23:00','22:30','Ingles',750.00,false,3,2,9);

/*funcion test reserva */
insert into funcion (id,fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values                    
					(888,'2022-11-29 3:00','22:30','Ingles',850.00,true,1,1,13);
                    
insert into funcion (id,fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values                    
					(889,'2022-11-29 3:00','22:30','Ingles',850.00,true,3,1,13);
                    
insert into funcion (id,fecha,horario,lenguaje,precio,subtitulos,formato_id,pelicula_id,sala_id) values                    
					(890,'2022-11-29 3:00','22:30','Ingles',850.00,false,2,1,13);
                    
call EP_crearAsientosYEntradasVacias(888);
call EP_crearAsientosYEntradasVacias(890);
call EP_crearAsientosYEntradasVacias(889);

call EP_crearAsientosYEntradasVacias(90);
call EP_crearAsientosYEntradasVacias(91);
call EP_crearAsientosYEntradasVacias(92);
call EP_crearAsientosYEntradasVacias(93);
call EP_crearAsientosYEntradasVacias(94);
call EP_crearAsientosYEntradasVacias(95);
call EP_crearAsientosYEntradasVacias(96);
call EP_crearAsientosYEntradasVacias(97);
call EP_crearAsientosYEntradasVacias(98);
call EP_crearAsientosYEntradasVacias(190);
call EP_crearAsientosYEntradasVacias(191);
call EP_crearAsientosYEntradasVacias(192);
call EP_crearAsientosYEntradasVacias(193);
call EP_crearAsientosYEntradasVacias(194);
call EP_crearAsientosYEntradasVacias(195);
call EP_crearAsientosYEntradasVacias(196);
call EP_crearAsientosYEntradasVacias(197);
call EP_crearAsientosYEntradasVacias(198);

update funcion set fecha='2022-11-11 21:00' where id = 90;
update funcion set fecha='2022-11-11 21:00' where id = 91;
update funcion set fecha='2022-11-12 21:00' where id = 92;
update funcion set fecha='2022-11-11 21:00' where id = 93;
update funcion set fecha='2022-11-13 21:00' where id = 94;
update funcion set fecha='2022-11-11 21:00' where id = 95;
update funcion set fecha='2022-11-12 21:00' where id = 96;
update funcion set fecha='2022-11-11 21:00' where id = 97;
update funcion set fecha='2022-11-11 21:00' where id = 98;

update funcion set fecha='2022-11-25 21:00' where id = 190;
update funcion set fecha='2022-11-26 21:00' where id = 191;
update funcion set fecha='2022-11-27 21:00' where id = 192;
update funcion set fecha='2022-11-11 21:00' where id = 193;
update funcion set fecha='2022-11-13 21:00' where id = 194;
update funcion set fecha='2022-11-11 21:00' where id = 195;
update funcion set fecha='2022-11-12 21:00' where id = 196;
update funcion set fecha='2022-11-11 21:00' where id = 197;
update funcion set fecha='2022-11-11 21:00' where id = 198;
update funcion set fecha='2022-11-24 21:00' where id = 7337;

                    
drop procedure if exists EP_recorrerFunciones;
DELIMITER //
create procedure EP_recorrerFunciones()
begin

	declare contador int default 190;
	
	while contador < 199
	do
		call EP_crearAsientosYEntradasVacias(contador);
	
	set contador = contador + 1;
	end while;

end//
DELIMITER ;

drop procedure if exists EP_recorrerFuncionesA;
DELIMITER //
create procedure EP_recorrerFuncionesA()
begin

	declare contador int default 90;
	
	while contador < 99
	do
		call EP_crearAsientosYEntradasVacias(contador);
	
	set contador = contador + 1;
	end while;

end//
DELIMITER ;

drop procedure if exists EP_recorrerFuncionesB;
DELIMITER //
create procedure EP_recorrerFuncionesB()
begin

	declare contador int default 99;
	
	while contador < 145
	do
		call EP_crearAsientosYEntradasVacias(contador);
	
	set contador = contador + 1;
	end while;

end//
DELIMITER ;

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

    call EP_crearCineFuncion(funcion_id);
    
end//
DELIMITER ;



drop procedure if exists EP_crearCineFuncion;
DELIMITER //
create procedure EP_crearCineFuncion(in idFuncion int)
begin

	call EP_obtenerCineDeUnaFuncion(idFuncion,@idCine);
    call EP_obtenerPeliculaDeUnaFuncion(idFuncion,@idPelicula);
    
    call EP_validarCinePelicula(@idPelicula,@idCine,@resultado);
    
    if(@resultado = 0)
    then
    insert into cinepelicula (cine_id,pelicula_id) values (@idCine,@idPelicula);
    else
    select 0;
    end if;

end//
DELIMITER ;


drop procedure if exists EP_obtenerCantidadDeAsientosDeUnaFuncion;
DELIMITER //
create procedure EP_obtenerCantidadDeAsientosDeUnaFuncion(in funcion_id int,out _asientosTotales int)
begin		
        select s.asientosTotales into _asientosTotales from funcion f
        left join sala s on f.sala_id = s.id 
        where f.id = funcion_id;

end//
DELIMITER ;

drop procedure if exists EP_obtenerCineDeUnaFuncion;
DELIMITER //
create procedure EP_obtenerCineDeUnaFuncion(in funcion_id int,out _cineId int)
begin		
        select c.id into _cineId from cine as c
		join sala as s on s.cine_id = c.id
		join funcion as f on f.sala_id = s.id
		where f.id = funcion_id;

end//
DELIMITER ;

drop procedure if exists EP_obtenerPeliculaDeUnaFuncion;
DELIMITER //
create procedure EP_obtenerPeliculaDeUnaFuncion(in funcion_id int,out _peliculaId int)
begin		
        select p.id into _peliculaId from pelicula as p
		join funcion as f on f.pelicula_id = p.id
		where f.id = funcion_id;

end//
DELIMITER ;


drop procedure if exists EP_validarCinePelicula;
DELIMITER //
create procedure EP_validarCinePelicula(in idPelicula int,in idCine int,out resultado boolean)
begin
      if exists(select id from cinepelicula
				where pelicula_id = idPelicula
				and cine_id = idCine)
      then
      set resultado = 1;
      else
      set resultado = 0;
      end if;

end//
DELIMITER ;

call EP_recorrerFunciones();