use cineclub;

drop procedure if exists EP_ocuparAsientos;
DELIMITER //
create procedure EP_ocuparAsientos(in funcion_id int)
begin
		declare contador int default 1;
        
        call EP_obtenerCantidadDeAsientosDeUnaFuncion(funcion_id,@cantidadACrear);
        
	while contador < @cantidadACrear+1
	do
	
        insert into asiento (numero,ocupado,ubicacion) values (contador,1,'A'); 
        
        set @idAsiento = (select id from asiento order by id desc limit 1);
        
        insert into entrada (funcion_id,asiento_id,usuario_id) values (funcion_id,@idAsiento,3);
        
        set @idEntrada = (select id from entrada order by id desc limit 1);
        
        update asiento set entrada_id = @idEntrada where id = @idAsiento;
        
        set contador = contador + 1;
        
	end while;

    call EP_crearCineFuncion(funcion_id);
    
end//
DELIMITER ;

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
	
        insert into asiento (numero,ocupado,ubicacion) values (contador,false,'A 1'); 
        
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

