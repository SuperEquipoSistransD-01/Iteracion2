Para instalar la instalación de la aplicación, deben seguirse los siguientes pasos.

1. Descomprima el proyecto Java
2. En el archivo persistence.xml, cambie los valores de username y password por aquellos de su pertenencia.
3. Dentro de SQL developer, borre todas las tablas ya existentes.
4. Utilice CreacionTablasFinal.sql para crear todas las tablas del modelo. Este archivo está en la carpeta docs/DiseñoYConstruccion.
5. Utilice DatosEjemploFinal.sql para llenar con tuplas válidas todas las tablas del modelo. Este archivo está en la carpeta docs/Poblacion de Tablas
6. Dando click derecho a InterfazSuperandes, seleccione RunAs>JavaApplication.
7. Para empezar a manejar la aplicación, debe primero iniciar sesión. Dependiendo del usuario y su contraseña, podrá ejecutar ciertos requerimientos funcionales.
7.1 Si desea, por ejemplo, añadir una sucursal, debe tener un usuario admin. Para esto, puede probar con numDocumento = 123456, clave = 122333.
7.2 Si desea, por ejemplo, añadir provedor, debe tener un usuario gerente sucursal. Para esto, puede probar con numDocumento = 123457, clave = 12345.

Ya está listo para empezar a usar la aplicación! 