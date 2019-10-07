Instrucciones de uso EPSAndes


1.	CONEXIÓN A LA BASE DE DATOS 
Los datos de conexión se deben especificar en el archivo resources/META-INF/persistence.xml. Este archivo contiene las unidades de persistencia a las cuales hacen referencia los archivos json de configuración. 

Abra el archivo persistence.xml, estudie las unidades de persistencia definidas y modifique la unidad de persistencia “EPSAndes” de la siguiente forma: 

•	En la propiedad “javax.jdo.option.ConnectionUserName” ponga como valor el nombre de usuario.
•	En la propiedad “javax.jdo.option.ConnectionPassword” ponga como valor la contraseña.

2.	CREE LAS TABLAS (EL ESQUEMA) DE LA BASE DE DATOS
En SQL Developer abra el archivo que se encuentra en data/EsquemaEPSAndes.sql corra este archivo para que las tablas queden en la base de datos.
En SQL Developer abra el archivo que se encuentra en data/poblacionTablas.sql para simular el administrador de sistemas, quien se encarga de designar roles y servicios, además de que esto permite tener datos para el funcionamiento de EPSAndes desde la vista gerencial o la vista del 

3.	Ejecución Aplicación EPSAndes
Ejecute EPSAndes (src.main.java/interfazApp/InterfazEPSAndesApp.java). explore su funcionamiento en el que podrá ver como se cumplen los requerimientos del proyecto especificados en el documento Requerimientos_Funcionales.pdf que se encuentra en la carpeta  data/Requerimientos_Funcionales.pdf.  


3.1.	Funcionamiento del programa
Al correr la aplicación el sistema pide que especifique el ROL de su usuario.
Este debe ser un valor de los siguientes (sin las comillas y sin importar mayusculas/minúsculas):
	- "admin"
	- "recepcionista"
	- "afiliado"
	- "gerente"
	- "medico"
A continuación debería abrirse la aplicación mostrando las pestañas a las que tiene acceso según el rol que especificó.
