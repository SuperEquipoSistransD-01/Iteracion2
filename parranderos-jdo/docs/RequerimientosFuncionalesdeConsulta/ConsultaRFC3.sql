--Ejemplo del RFC3 con la sucursal de ciudad Bogota y direccion Salitre

with filtro1 as (select * from estantes where ciudadSucursal = 'Bogota' and direccionSucursal = 'Salitre'),
filtro2 as (select * from filtro1 inner join  Endisplay on codigo=estante)

select (select sum(volumenenestante) from filtro2)/volumenLimite as IndiceOcupacion from filtro2 ; 




with filtro1 as (select * from bodegas where ciudadSucursal = 'Bogota' and direccionSucursal = 'Salitre'),
filtro2 as (select * from filtro1 inner join  StockDisponible on bodega=codigo)

select (select sum(volumenenbodega) from filtro2)/volumenLimite as IndiceOcupacion from filtro2 ; 


