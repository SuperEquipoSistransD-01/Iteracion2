

CREATE TABLE Productos
(
    codigo numeric(10),
    nombre varchar(20),
    marca varchar(20),
    precioUnitario numeric(20,2),
    presentacion varchar(20),
    precioUnidadMedida numeric(20,2),
    cantidadPresentacion numeric(4),
    unidadMedida varchar(2),
    volumenEmpaque numeric(20,2),
    pesoEmpaque numeric(20,2),
    tipoProducto varchar(20),
    categoriaAlmacenamiento varchar(20),
    
    PRIMARY KEY (codigo),
    CHECK (nombre is not null),
    CHECK (marca is not null),
    CHECK (precioUnitario is not null and precioUnitario > 0),
    CHECK (presentacion is not null),
    CHECK (precioUnidadMedida is not null and precioUnidadMedida > 0),
    CHECK (cantidadPresentacion is not null and cantidadPresentacion > 0),
    CHECK (unidadMedida is not null and unidadMedida in ('gr','ml')),
    CHECK (volumenEmpaque is not null and volumenEmpaque > 0),
    CHECK (pesoEmpaque is not null and pesoEmpaque > 0),
    CHECK (tipoProducto is not null),
    CHECK (categoriaAlmacenamiento is not null)
);


CREATE TABLE Sucursales
(
    ciudad varchar(20),
    direccion varchar(20),
    nombre varchar(20),
    area numeric(20,2),
    
    PRIMARY KEY(ciudad, direccion),
    CHECK(nombre is not null),
    CHECK(area is not null and area > 0)
);
COMMIT;


CREATE TABLE Proveedores
(
    nit numeric(10),
    nombre varchar(20),
    calificacion numeric(3,1),
    
    PRIMARY KEY(nit),
    CHECK(nombre is not null),
    CHECK(10 > calificacion  and calificacion > 0)
);
COMMIT;


CREATE TABLE AcuerdosCompra
( 
    ciudadSucursal, direccionSucursal,
    proveedor REFERENCES Proveedores,
    producto REFERENCES Productos,
    precioCompraProducto numeric(20,2),
    precioVentaProducto numeric(20,2),
    nivelReorden numeric(20,2),
    
    PRIMARY KEY (ciudadSucursal, direccionSucursal, proveedor, producto),
    FOREIGN KEY (ciudadSucursal, direccionSucursal) REFERENCES Sucursales,
    CHECK (precioCompraProducto > 0),
    CHECK (precioVentaProducto > 0)
);
COMMIT;


CREATE TABLE Pedidos
(
    codigo numeric(10),
    ciudadSucursal, direccionSucursal, proveedor, producto,
    fechaPedido date,
    diasDesdePedido numeric(3),
    volumenProducto numeric(20,2),
    diasParaEntrega numeric(3),
    llego numeric(1),
    
    PRIMARY KEY (codigo),
    FOREIGN KEY (ciudadSucursal, direccionSucursal, proveedor, producto) REFERENCES AcuerdosCompra,
    CHECK (ciudadSucursal is not null),
    CHECK (direccionSucursal is not null),
    CHECK (proveedor is not null),
    CHECK (producto is not null),
    CHECK (fechaPedido is not null),
    CHECK (diasDesdePedido is not null and diasDesdePedido >= 0),
    CHECK (volumenProducto is not null and volumenProducto > 0),
    CHECK (diasParaEntrega is not null and diasParaEntrega > 0),
    CHECK (llego = 0 or llego = 1)
);
COMMIT;


CREATE TABLE Bodegas
(
    codigo numeric(10),
    ciudadSucursal,direccionSucursal,
    categoriaAlmacenamiento varchar(20),
    capacidad numeric(20,2),
    
    PRIMARY KEY (codigo),
    FOREIGN KEY (ciudadSucursal,direccionSucursal) REFERENCES Sucursales,
    CHECK (categoriaAlmacenamiento is not null),
    CHECK (capacidad is not null and capacidad > 0)
);
COMMIT;


CREATE TABLE Estantes
(
    codigo numeric(10),
    ciudadSucursal,direccionSucursal,
    tipoProducto varchar(20),
    capacidad numeric(20,2),
    
    PRIMARY KEY (codigo),
    FOREIGN KEY (ciudadSucursal,direccionSucursal) REFERENCES Sucursales,
    CHECK (tipoProducto is not null),
    CHECK (capacidad is not null and capacidad > 0)
);
COMMIT;


CREATE TABLE EnDisplay
(
    producto REFERENCES Productos,
    estante REFERENCES Estantes,
    nivelAbastecimiento numeric(20,2),
    cantidad numeric(20,2),
    espacioDisponible numeric(20, 2),
    
    PRIMARY KEY (producto, estante),
    CHECK(cantidad is not null and cantidad >= 0),
    CHECK(nivelAbastecimiento is not null and nivelAbastecimiento >= 0),
    CHECK(espacioDisponible is not null and espacioDisponible >= 0)

);
COMMIT;


CREATE TABLE StockDisponible
(
    producto REFERENCES Productos,
    bodega REFERENCES Bodegas,
    cantidad numeric(20,2),
    espacioDisponible numeric(20, 2),
    
    PRIMARY KEY (producto, bodega),
    CHECK(cantidad is not null and cantidad >= 0),
    CHECK(espacioDisponible is not null and espacioDisponible >= 0)
);
COMMIT;



CREATE TABLE Clientes
(
   numDocumento numeric(10),
   tipoDocumento varchar(3),
   nombre varchar(20),
   correoElectronico varchar(30),
   clave varchar(30),
   
   PRIMARY KEY (numDocumento),
   CHECK (tipoDocumento is not null and tipoDocumento in ('cc','ti', 'nit')),
   CHECK (nombre is not null),
   CHECK (correoElectronico is not null),
   CHECK (clave is not null)
);
COMMIT;


CREATE TABLE ClientesSucursales
(
   ciudadSucursal, direccionSucursal,
   cliente REFERENCES clientes,
   
   FOREIGN KEY (ciudadSucursal, direccionSucursal) REFERENCES Sucursales,
   PRIMARY KEY (ciudadSucursal, direccionSucursal, cliente)
);
COMMIT;


CREATE TABLE Usuarios
(
   numDocumento numeric(10),
   nombre varchar(20),
   correoElectronico varchar(30),
   clave varchar(30),
   rol varchar(2),
   ciudadSucursal, direccionSucursal,
   
   PRIMARY KEY (numDocumento),
   CHECK (rol is not null and rol in  ('a', 'gg', 'gs', 'o', 'c')),
   CHECK (nombre is not null),
   CHECK (correoElectronico is not null),
   CHECK (clave is not null),
   FOREIGN KEY (ciudadSucursal, direccionSucursal) REFERENCES Sucursales
);
COMMIT;



CREATE TABLE Compras
(
   codigo numeric(10),
   fecha date,
   ciudadSucursal, direccionSucursal,
   cliente REFERENCES Clientes,
    
    PRIMARY KEY (codigo),
    FOREIGN KEY (ciudadSucursal, direccionSucursal) REFERENCES Sucursales,
    CHECK(fecha is not null),
    CHECK(ciudadSucursal is not null),
    CHECK(direccionSucursal is not null),
    CHECK(cliente is not null)
);
COMMIT;


CREATE TABLE CantProductosComprados
(
   compra REFERENCES Compras,
   producto REFERENCES Productos,
   cantProductos numeric(10,2),
    
    PRIMARY KEY (compra, producto),
    CHECK(cantProductos is not null and cantProductos > 0)
);
COMMIT;


create table Promociones
(
nombre varchar(20),
fechaInicio date,
diasDuracion numeric(10),
descripcion varchar(100),
tipo varchar(10),
finalizada numeric (3),
ciudadSucursal, direccionSucursal, producto,
FOREIGN KEY (ciudadSucursal, direccionSucursal) REFERENCES Sucursales,
foreign key (producto) references productos,
primary key (nombre, fechaInicio, diasDuracion),
 CHECK (tipo is not null and tipo in  ('1', '2', '3', '4', '5')),
 check (finalizada is not null and finalizada in  (0,1))
);

commit;

create table carritos
(	
clienteCC references clientes,
ciudadSucursal, direccionSucursal,
abandono numeric(3),
  
PRIMARY KEY (clienteCC, ciudadSucursal, direccionSucursal, abandono),
FOREIGN KEY (ciudadSucursal, direccionSucursal) REFERENCES Sucursales,
check (abandono is not null and abandono in (0,1))
);

commit;

create table estaEnCarrito
(
clienteCC, ciudadSucursal, direccionSucursal, abandono,
codigo references productos,
cantidad numeric(10),
primary key (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo),
foreign key (clienteCC, ciudadSucursal, direccionSucursal, abandono) references carritos,
check (cantidad >0)
);
commit;

