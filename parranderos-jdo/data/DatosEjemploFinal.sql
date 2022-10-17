insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (169,	'Zucaritas',	'Kellogs',	10000,	'Caja',	10000,	1,	'gr',	10,	15,	'Cereal',	'Perecederos');

insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (170,	'Don Pacho',	'Avena',	8000,	'Caja',	8000,	1,	'gr',	8,	12,	'Granos',	'Perecederos');

insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (171,	'Axion',	'GyM',	15000,	'Bolsa'	,15000,	1,	'ml',	15,	17,	'Jabon',	'Aseo');

--Productos OK

insert into sucursales (ciudad,	direccion,	nombre,	area)
values ('Bogota',	'Calle 140',	'El mercado de Tom',	144);

insert into sucursales (ciudad,	direccion,	nombre,	area)
values ('Cali',	'La 14',	'El mercado de David',	150);

insert into sucursales (ciudad,	direccion,	nombre,	area)
values ('Bogota',	'Salitre',	'Pomona',	175);

--Sucursales OK

insert into proveedores (nit,	nombre,	calificacion)
values (189,	'Alpina',	9);

insert into proveedores (nit,	nombre,	calificacion)
values (190,	'KellogsCo'	,7);

insert into proveedores (nit,	nombre,	calificacion)
values (191	,'JabonCO',	6);

--proveedores OK

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Salitre',	191,	171,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Cali',	'La 14',	189,	170,	8000,	4000,	12);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Calle 140',	189,	169,	10000,	5000,	15);

--acuerdos OK

insert into pedidos (codigo,	ciudadSucursal,	direccionSucursal,	proveedor,	producto,	fechaPedido,	diasDesdePedido,	volumenProducto,	diasParaEntrega,	llego)
values (210,	'Bogota',	'Salitre',	191,	171,	TO_DATE('16/10/2022', 'DD/MM/YYYY'),	3,	51,	1,	0);

insert into pedidos (codigo,	ciudadSucursal,	direccionSucursal,	proveedor,	producto,	fechaPedido,	diasDesdePedido,	volumenProducto,	diasParaEntrega,	llego)
values (211,	'Cali',	'La 14',	189,	170,	TO_DATE('16/10/2022', 'DD/MM/YYYY'),	4,	36,	2,	0);

insert into pedidos (codigo,	ciudadSucursal,	direccionSucursal,	proveedor,	producto,	fechaPedido,	diasDesdePedido,	volumenProducto,	diasParaEntrega,	llego)
values (212,	'Bogota',	'Calle 140',	189,	169,	TO_DATE('16/10/2022', 'DD/MM/YYYY'),	5,	45,	3, 1);

--pedidos OK

insert into bodegas (codigo,	ciudadSucursal,	direccionSucursal,	categoriaAlmacenamiento,	volumenLimite)
values (310,	'Bogota',	'Salitre',	'Aseo',	170);

insert into bodegas (codigo,	ciudadSucursal,	direccionSucursal,	categoriaAlmacenamiento,	volumenLimite)
values (311,	'Cali',	'La 14',	'Perecederos',	170);

insert into bodegas (codigo,	ciudadSucursal,	direccionSucursal,	categoriaAlmacenamiento,	volumenLimite)
values (312,	'Bogota',	'Calle 140',	'Perecederos',	170);

--Bodegas OK

insert into StockDisponible (producto,	bodega,	volumenEnBodega,	pesoEnBodega)
values (170,	311,	36,	48);

insert into StockDisponible (producto,	bodega,	volumenEnBodega,	pesoEnBodega)
values (169,	312,	45,	50);

insert into StockDisponible (producto,	bodega,	volumenEnBodega,	pesoEnBodega)
values (171,	310,	51,	60);

--StockDisponible OK

insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (410,	'Bogota',	'Calle 140',	'Cereal',	170,	200);

insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (411,	'Cali',	'La 14',	'Granos',	170,	200);

insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (412,	'Bogota',	'Salitre',	'Jabon',	170,	200);   

--Estantes OK

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento)
values (170,	411,	200,	100,	150);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento)
values (169,	410,	200,	100,	150);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento)
values (171,	412,	200,	100,	150);

--EnDisplay OK

insert into usuarios (numDocumento,	nombre,	correoElectronico,	clave,	ciudadSucursal,	direccionSucursal,	Rol)
values (123456,	'Admin',	'admin',	122333,	null,	null,	'a');

insert into usuarios (numDocumento,	nombre,	correoElectronico,	clave,	ciudadSucursal,	direccionSucursal,	Rol)
values (123459,	'Gerente General', 'general',	9875,	null,	null,	'gg');

insert into usuarios (numDocumento,	nombre,	correoElectronico,	clave,	ciudadSucursal,	direccionSucursal,	Rol)
values (123457,	'David',	'daf',	12345,	'Bogota',	'Salitre',	'gs');

insert into usuarios (numDocumento,	nombre,	correoElectronico,	clave,	ciudadSucursal,	direccionSucursal,	Rol)
values (123458,	'Tom',	'tom',	6789,	'Cali',	'La 14',	'gs');

--Usuarios OK

insert into clientes (numDocumento,	tipoDocumento,	nombre,	correoElectronico,	clave)
values (12345,	'cc',	'Cliente1',	'sdf',	11);

insert into clientes (numDocumento,	tipoDocumento,	nombre,	correoElectronico,	clave)
values (12346, 'cc',	'Cliente2',	'casd',	22);

insert into clientes (numDocumento,	tipoDocumento,	nombre,	correoElectronico,	clave)
values (12347,	'cc',	'Cliente3',	'dasf',	33);

--Clientes OK

insert into ClientesSucursales (ciudadSucursal, direccionSucursal, cliente)
values ('Bogota',	'Calle 140',	12345);

insert into ClientesSucursales (ciudadSucursal, direccionSucursal, cliente)
values ('Bogota',	'Salitre',	12346);

insert into ClientesSucursales (ciudadSucursal, direccionSucursal, cliente)
values ('Cali',	'La 14',	12347);

--ClientesSucursal OK

insert into compras(codigo,	fecha,	CiudadSucursal,	DireccionSucursal, cliente)
values (710,	TO_DATE('17/10/2022', 'DD/MM/YYYY'),	'Bogota',	'Calle 140', 12345);

insert into compras(codigo,	fecha,	CiudadSucursal,	DireccionSucursal, cliente)
values (711,	TO_DATE('16/10/2022', 'DD/MM/YYYY'),	'Cali',	'La 14', 12346);

insert into compras(codigo,	fecha,	CiudadSucursal,	DireccionSucursal, cliente)
values (712,	TO_DATE('15/12/2022', 'DD/MM/YYYY'),	'Bogota',	'Salitre', 12347);

--compras OK

insert into CantProductosComprados (Compra,	Producto,	CantProductos)
values (710,	169,	1);

insert into CantProductosComprados (Compra,	Producto,	CantProductos)
values (710,	170,	2);

insert into CantProductosComprados (Compra,	Producto,	CantProductos)
values (712,	171,	3);

insert into CantProductosComprados (Compra,	Producto,	CantProductos)
values (711,	169,	3);

--CantProductosCompradosOK



