
insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (169,	'Zucaritas',	'Kellogs',	10000,	'Caja',	10000,	1,	'gr',	10,	15,	'Cereal',	'Perecederos');

insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (170,	'Chocos',	'Kellogs',	10000,	'Caja',	10000,	1,	'gr',	10,	15,	'Cereal',	'Perecederos');

insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (171,	'Loops',	'Kellogs',	10000,	'Caja',	10000,	1,	'gr',	10,	15,	'Cereal',	'Perecederos');

insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (172,	'Don Pacho',	'Avena',	8000,	'Caja',	8000,	1,	'gr',	8,	12,	'Granos',	'Perecederos');

insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (173,	'Quaker',	'Avena',	8000,	'Caja',	8000,	1,	'gr',	8,	12,	'Granos',	'Perecederos');

insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (174,	'Nestle',	'Avena',	8000,	'Caja',	8000,	1,	'gr',	8,	12,	'Granos',	'Perecederos');

insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (175,	'Axion',	'GyM',	15000,	'Bolsa'	,15000,	1,	'ml',	15,	17,	'Jabon',	'Aseo');

insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (176,	'Coco',	'GyM',	15000,	'Bolsa'	,15000,	1,	'ml',	15,	17,	'Jabon',	'Aseo');

insert into productos (codigo, nombre,	marca,	precioUnitario,	presentacion,	precioUnidadMedida,	cantidadPresentacion,	unidadMedida,	volumenEmpaque,	pesoEmpaque, 	tipoProducto,	categoriaAlmacenamiento)
values (177,	'Azul',	'GyM',	15000,	'Bolsa'	,15000,	1,	'ml',	15,	17,	'Jabon',	'Aseo');
--Productos OK

insert into sucursales (ciudad,	direccion,	nombre,	area)
values ('Bogota',	'Calle 140',	'El mercado de Tom',	144);

insert into sucursales (ciudad,	direccion,	nombre,	area)
values ('Cali',	'La 14',	'El mercado de David',	150);

insert into sucursales (ciudad,	direccion,	nombre,	area)
values ('Bogota',	'Salitre',	'Pomona',	175);

--Sucursales OK

insert into proveedores (nit,	nombre,	calificacion)
values (7189,	'Alpina',	9);

insert into proveedores (nit,	nombre,	calificacion)
values (7190,	'KellogsCo'	,7);

insert into proveedores (nit,	nombre,	calificacion)
values (7191	,'JabonCO',	6);

--proveedores OK

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Salitre',	7191,	169,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Salitre',	7191,	170,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Salitre',	7191,	171,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Salitre',	7191,	172,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Salitre',	7191,	173,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Salitre',	7191,	174,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Salitre',	7191,	175,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Salitre',	7191,	176,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Salitre',	7191,	177,	15000,	7500,	17);


insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Cali',	'La 14',	7191,	169,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Cali',	'La 14',	7191,	170,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Cali',	'La 14',	7191,	171,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Cali',	'La 14',	7191,	172,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Cali',	'La 14',	7191,	173,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Cali',	'La 14',	7191,	174,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Cali',	'La 14',	7191,	175,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Cali',	'La 14',	7191,	176,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Cali',	'La 14',	7191,	177,	15000,	7500,	17);



insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Calle 140',	7191,	169,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Calle 140',	7191,	170,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Calle 140',	7191,	171,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Calle 140',	7191,	172,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Calle 140',	7191,	173,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Calle 140',	7191,	174,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Calle 140',	7191,	175,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Calle 140',	7191,	176,	15000,	7500,	17);

insert into AcuerdosCompra (ciudadSucursal,	direccionSucursal,	proveedor,	producto,	precioVentaProducto,	precioCompraProducto,	nivelReorden)
values ('Bogota',	'Calle 140',	7191,	177,	15000,	7500,	17);

--acuerdos OK
/*
insert into pedidos (codigo,	ciudadSucursal,	direccionSucursal,	proveedor,	producto,	fechaPedido,	diasDesdePedido,	volumenProducto,	diasParaEntrega,	llego)
values (22210,	'Bogota',	'Salitre',	7191,	171,	TO_DATE('16/10/2022', 'DD/MM/YYYY'),	3,	51,	1,	0);

insert into pedidos (codigo,	ciudadSucursal,	direccionSucursal,	proveedor,	producto,	fechaPedido,	diasDesdePedido,	volumenProducto,	diasParaEntrega,	llego)
values (2211,	'Cali',	'La 14',	7189,	170,	TO_DATE('16/10/2022', 'DD/MM/YYYY'),	4,	36,	2,	0);

insert into pedidos (codigo,	ciudadSucursal,	direccionSucursal,	proveedor,	producto,	fechaPedido,	diasDesdePedido,	volumenProducto,	diasParaEntrega,	llego)
values (2212,	'Bogota',	'Calle 140',	7189,	169,	TO_DATE('16/10/2022', 'DD/MM/YYYY'),	5,	45,	3, 1);*/

--pedidos OK

insert into bodegas (codigo,	ciudadSucursal,	direccionSucursal,	categoriaAlmacenamiento,	volumenLimite)
values (310,	'Bogota',	'Salitre',	'Aseo',	170);

insert into bodegas (codigo,	ciudadSucursal,	direccionSucursal,	categoriaAlmacenamiento,	volumenLimite)
values (311,	'Cali',	'La 14',	'Perecederos',	170);

insert into bodegas (codigo,	ciudadSucursal,	direccionSucursal,	categoriaAlmacenamiento,	volumenLimite)
values (312,	'Bogota',	'Calle 140',	'Perecederos',	170);

--Bodegas OK

insert into StockDisponible (producto,	bodega,	volumenEnBodega,	pesoEnBodega, cantidad)
values (170,	311,	36,	48, 4);

insert into StockDisponible (producto,	bodega,	volumenEnBodega,	pesoEnBodega, cantidad)
values (169,	312,	45,	50,4);

insert into StockDisponible (producto,	bodega,	volumenEnBodega,	pesoEnBodega, cantidad)
values (171,	310,	51,	60,4);

--StockDisponible OK
--Cereal
insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (410,	'Bogota',	'Calle 140',	'Cereal',	170,	200);

insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (411,	'Bogota',	'Salitre',	'Cereal',	170,	200);

insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (412,	'Cali',	'La 14',	'Cereal',	170,	200);


--Granos
insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (413,	'Cali',	'La 14',	'Granos',	170,	200);

insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (414,	'Bogota',	'Calle 140',	'Granos',	170,	200);

insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (415,	'Bogota',	'Salitre',	'Granos',	170,	200);


--Jabon
insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (416,	'Bogota',	'Salitre',	'Jabon',	170,	200);   

insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (417,	'Bogota',	'Calle 140',	'Jabon',	170,	200);   

insert into estantes (codigo,	ciudadSucursal,	direccionSucursal,	tipoProducto,	volumenLimite,	pesoLimite)
values (418,	'Cali',	'La 14',	'Jabon',	170,	200);   

--Estantes OK
--Cereales Bogota 140
insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (169,	410,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (170,	410,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (171,	410,	200,	100,	150, 20);

--Cereales Bogota Salitre
insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (169,	411,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (170,	411,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (171,	411,	200,	100,	150, 20);

--Cereales Cali La 14
insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (169,	412,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (170,	412,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (171,	412,	200,	100,	150, 20);


--Granos Cali 14
insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (172,	413,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (173,	413,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (174,	413,	200,	100,	150, 20);

--Granos Bogota 140
insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (172,	414,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (173,	414,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (174,	414,	200,	100,	150, 20);

--Granos Bogota Salitre
insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (172,	415,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (173,	415,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (174,	415,	200,	100,	150, 20);


--Jabon Bogota Salitre
insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (175,	416,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (176,	416,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (177,	416,	200,	100,	150, 20);

--Jabon Bogota 140
insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (175,	417,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (176,	417,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (177,	417,	200,	100,	150, 20);

--Jabon Cali 14
insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (175,	418,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (176,	418,	200,	100,	150, 20);

insert into enDisplay (producto,	estante,	volumenEnEstante,	pesoEnEstante,	nivelAbastecimiento, cantidad)
values (177,	418,	200,	100,	150, 20);

--EnDisplay OK

insert into usuarios (numDocumento,	nombre,	correoElectronico,	clave,	ciudadSucursal,	direccionSucursal,	Rol)
values (123456,	'Admin',	'admin',	122333,	null,	null,	'a');

insert into usuarios (numDocumento,	nombre,	correoElectronico,	clave,	ciudadSucursal,	direccionSucursal,	Rol)
values (123459,	'Gerente General', 'general',	9875,	null,	null,	'gg');

insert into usuarios (numDocumento,	nombre,	correoElectronico,	clave,	ciudadSucursal,	direccionSucursal,	Rol)
values (123457,	'David',	'daf',	12345,	'Bogota',	'Salitre',	'gs');

insert into usuarios (numDocumento,	nombre,	correoElectronico,	clave,	ciudadSucursal,	direccionSucursal,	Rol)
values (123458,	'Tom',	'tom',	6789,	'Cali',	'La 14',	'gs');

insert into usuarios (numDocumento,	nombre,	correoElectronico,	clave,	ciudadSucursal,	direccionSucursal,	Rol)
values (124444,	'OperadorBogota140',	'operador1', 1234,	'Bogota',	'Calle 140',	'o');

insert into usuarios (numDocumento,	nombre,	correoElectronico,	clave,	ciudadSucursal,	direccionSucursal,	Rol)
values (124445,	'OpBogSal',	'operador2', 1234,	'Bogota',	'Salitre',	'o');

insert into usuarios (numDocumento,	nombre,	correoElectronico,	clave,	ciudadSucursal,	direccionSucursal,	Rol)
values (124446,	'OperadorCali14',	'operador3', 1234,	'Cali',	'La 14',	'o');

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


insert into promociones (nombre, fechainicio, diasduracion, descripcion, tipo, finalizada, ciudadSucursal, direccionsucursal, producto)
values ('Promo1',	TO_DATE('17/10/2022', 'DD/MM/YYYY'),	3,	'Esta es la promo 1', '1',0, 'Bogota', 'Salitre', 169);

insert into promociones (nombre, fechainicio, diasduracion, descripcion, tipo, finalizada, ciudadSucursal, direccionsucursal, producto)
values ('Promo2',	TO_DATE('14/10/2022', 'DD/MM/YYYY'),	2,	'Esta es la promo 2', '1',1, 'Bogota', 'Calle 140', 170);

insert into promociones (nombre, fechainicio, diasduracion, descripcion, tipo, finalizada, ciudadSucursal, direccionsucursal, producto)
values ('Promo3',	TO_DATE('15/10/2022', 'DD/MM/YYYY'),	1,	'Esta es la promo 3', '1',0, 'Cali', 'La 14', 171);
--Promociones OK

insert into compras (codigo,	fecha,	CiudadSucursal,	DireccionSucursal, cliente)
values (712,	TO_DATE('15/11/2022', 'DD/MM/YYYY'),	'Bogota',	'Salitre', 12347);

insert into compras (codigo,	fecha,	CiudadSucursal,	DireccionSucursal, cliente)
values (740,	TO_DATE('15/11/2022', 'DD/MM/YYYY'),	'Bogot',	'Salite', 123);

insert into proveedores (nit,	nombre,	calificacion)
values (197	,'JabonCO',	6666);

 --Casos Prueba OK
 
 

--carritos
INSERT INTO carritos (clienteCC, ciudadSucursal, direccionSucursal, abandono) 
values (12345, 'Bogota', 'Calle 140', 0);

INSERT INTO carritos (clienteCC, ciudadSucursal, direccionSucursal, abandono) 
values (12346, 'Bogota', 'Salitre', 0);

INSERT INTO carritos (clienteCC, ciudadSucursal, direccionSucursal, abandono) 
values (12345, 'Bogota', 'Calle 140', 1);
 
INSERT INTO carritos (clienteCC, ciudadSucursal, direccionSucursal, abandono) 
values (12346, 'Bogota', 'Calle 140', 1);

INSERT INTO carritos (clienteCC, ciudadSucursal, direccionSucursal, abandono) 
values (12347, 'Bogota', 'Calle 140', 1);

INSERT INTO carritos (clienteCC, ciudadSucursal, direccionSucursal, abandono) 
values (12346, 'Bogota', 'Salitre', 1);

--EstaEnCarrito

insert into EstaEnCarrito (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad) 
values (12345, 'Bogota', 'Calle 140', 0, 169, 4);

insert into EstaEnCarrito (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad) 
values (12345, 'Bogota', 'Calle 140', 0, 170, 4);


INSERT INTO EstaEnCarrito (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad)  
values (12345, 'Bogota', 'Calle 140', 1, 169, 4);

INSERT INTO EstaEnCarrito (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad) 
values (12345, 'Bogota', 'Calle 140', 1, 170, 4);

INSERT INTO EstaEnCarrito (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad)  
values (12345, 'Bogota', 'Calle 140', 1, 172, 4);

INSERT INTO EstaEnCarrito (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad) 
values (12345, 'Bogota', 'Calle 140', 1, 177, 4);


INSERT INTO EstaEnCarrito (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad) 
values (12347, 'Bogota', 'Calle 140', 1, 169, 4);

INSERT INTO EstaEnCarrito (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad) 
values (12347, 'Bogota', 'Calle 140', 1, 170, 4);

INSERT INTO EstaEnCarrito (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad) 
values (12347, 'Bogota', 'Calle 140', 1, 172, 4);

INSERT INTO EstaEnCarrito (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad) 
values (12347, 'Bogota', 'Calle 140', 1, 177, 4);



commit;
