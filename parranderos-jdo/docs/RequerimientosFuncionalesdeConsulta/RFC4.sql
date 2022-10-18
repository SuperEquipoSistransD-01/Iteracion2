--RFC4


--Rango de precios
select * from productos where precioUnitario<14000 and precioUnitario>5000;

--Rango de volumenes
select * from productos where volumenEmpaque<14 and volumenEmpaque>5;

--Rango de pesos
select * from productos where pesoEmpaque<14 and pesoEmpaque>3;

--vendidos por proveedor Alpina
select proveedores.nombre as proveedor, productos.* from productos, acuerdosCompra, proveedores where productos.codigo=acuerdosCOmpra.producto and acuerdosCompra.proveedor = proveedores.nit and proveedores.nombre = 'Alpina';

--Disponibles en cierta ciudad
select sucursales.ciudad as ciudad, productos.* from productos, acuerdosCompra, sucursales where productos.codigo=acuerdosCOmpra.producto and acuerdosCompra.Ciudadsucursal = sucursales.Ciudad and acuerdosCompra.direccionSucursal = sucursales.direccion  and sucursales.ciudad = 'Bogota';

--Disponibles en cierta sucursal
select sucursales.nombre as sucursal, productos.* from productos, acuerdosCompra, sucursales where productos.codigo=acuerdosCOmpra.producto and acuerdosCompra.Ciudadsucursal = sucursales.Ciudad and acuerdosCompra.direccionSucursal = sucursales.direccion  and sucursales.nombre = 'Pomona';

