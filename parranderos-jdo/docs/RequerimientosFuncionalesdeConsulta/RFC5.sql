--RFC5 - MOSTRAR LAS COMPRAS HECHAS POR SUPERANDES A LOS PROVEEDORES

select proveedores.nombre as proveedor, pedidos.*  from pedidos, proveedores where proveedores.nit = pedidos.proveedor;