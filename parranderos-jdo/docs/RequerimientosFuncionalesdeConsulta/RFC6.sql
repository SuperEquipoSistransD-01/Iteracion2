--RFC6

select clientes.nombre, compras.* from compras, clientes where compras.fecha>'15/10/22' and compras.fecha<'20/10/22' and compras.cliente = clientes.numdocumento; 