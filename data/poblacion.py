import random
import datetime
import constants
import csv

# Archivo en el que se escriben las sentencias SQL para la poblacion de datos.
file = open("poblacion.txt", "w")

# listado de datos de los cuales se obtendran los valores para generar datos aleatorios.
tipoDoc = ['TI','CC','CE']
tipoDocMed = ['CC','CE']
nombre = ['Maria','Jose','Luis','Luz','Ana','Carlos','Juan','Luz Marina','Juan Carlos','Maria del Carmen'
    ,'Luis Alberto','Carlos Alberto','Luis Eduardo','Sandra Milena','Ana Maria','Santiago','Valentina','Sebastian'
    ,'Daniela','Mariana','Natalia','Alejandro','Nicolas','Samuel','Camila','Juan Camilo', 'Maria Alejandra'
    ,'Juan David','Juan Esteban','Monica','Paula','Melisa','Marcela','Jorge','Enrique','Antonio','Antonia','Paulina'
    ,'Maria Paula','Gloria','Liliana','Claudia','Olga','Cecilia','Julian','Hernando','Valeria','Edgar','Andrés'
    ,'Felipe','Andrea,','Laura','Isabella','Isabel','Maria Isabel','Veronica','Federico','Juliana','Juana','Angela,'
    ,'Sofia','Bernardo','Leonardo','Manuela','Manuel','Tania','Nicole','Christian','German','Maria Camila','Ana Sofia'
    ,'William','Oscar','Adrian','Angie','Fernando','Joaquin','Jairo','Tomas','Eva','Maria Juliana','Hernan','Elizabeth'
    ,'Socorro','Luz Mila','Daniel','Francisco','Ricardo','Pedro','Katherine','Amparo','Juan Felipe','Rafael','Saul'
    ,'Jessica','Nelly','Alberto','David','Sonia','Rocio','Ariel','Guillermo','Mario','Martha','Mateo','Ivan']
apellido = ['Rodriguez','Gomez','Zambrano','Martinez','Garcia','Aguirre','Duque','Gutierrez','Garzon','Perez','Tobon'
    ,'Caceres','Gaviria','Ortega','Serrano','Califa','Hernandes','Sanchez','Lopez','Betancourt','Tafur','Muñoz'
    ,'Giraldo','Valencia','Diaz','Castillo','Pantoja','Castiblanco','Peña','Escalante','Maldonado','Rocha','Velez'
    ,'Vasquez','Rincon','Tellez','Gonzalez','Moreno','Benitez','Mejia','Suarez','Rendon','Manrique','Garces','Sardi'
    ,'Galvez','Rueda','Mosquera','Ortegon','Arrieta','Zamorano','Fernandez','Bejarano','Reyes','Romero','Otero'
    ,'Quintero','Becerra','Bocanegra','Millan','Rojas','Franco','Ramirez','Mendoza','Chavarriaga','Perlaza','Duran'
    ,'Carvajal','Puerto','Forero','Guerrero','Palacios','Torres','Ruiz','Silva','Maya','Pedroza','Aguiar','Bravo'
    ,'Avila','Botero','Soto','Montenegro','Ocampo','Campo','Uchima','Caicedo','Alzate','Orozco','Pava','Lenis','Acosta'
    ,'Restrepo','Sierra','Rios','Sarmiento','Alvarez','Bustamante','Jimenez','Rivera','Cordoba','Barrios','Valdes'
    ,'Bayona','Delgado','Posada']

#------------------------------------------------------------------------------
#--------------------------- Poblar Tabla Roles. 1.----------------------------
#------------------------------------------------------------------------------

#------------------------------------------------------------------------------
#----------------------------- Insertar 6 roles.-------------------------------
#------------------------------------------------------------------------------

file.writelines("insert into ROLUSUARIO (ID_ROL,NOMBRE) values(1,'Afiliado');\n")
file.writelines("insert into ROLUSUARIO (ID_ROL,NOMBRE) values(2,'Medico');\n")
file.writelines("insert into ROLUSUARIO (ID_ROL,NOMBRE) values(3,'Recepcionista');\n")
file.writelines("insert into ROLUSUARIO (ID_ROL,NOMBRE) values(4,'Administrador');\n")
file.writelines("insert into ROLUSUARIO (ID_ROL,NOMBRE) values(5,'Gerente');\n")
file.writelines("insert into ROLUSUARIO (ID_ROL,NOMBRE) values(6,'Organizador campania');\n")
file.writelines("\n")

#------------------------------------------------------------------------------
#------------------------------ Poblar Tabla IPS. 2.---------------------------
#------------------------------------------------------------------------------

#------------------------------------------------------------------------------
#----------------------------- Insertar 15 IPS.---------------------------------
#------------------------------------------------------------------------------

file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(1,'IPSColombia','Calle 102 # 35-20');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(2,'IPSAndes','Calle 19 # 1-69');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(3,'A & G NIZA','Avenida 127 # 71-96');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(4,'Javesalud','Calle 127 # 17 A – 81');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(5,'Unidad Médica Santa Fe Américas','Avenida Americas # 69 C 56');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(6,'Asistir Salud Fontibon','Carrera 99 # 20C-61');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(7,'SF Kennedy','Cl 35 A SUR #78 K - 17');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(8,'Salud en casa','Carrera 65 # 11-50');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(9,'IPSAndes Olaya','Avenida Carrera 14 # 26 A-79 Sur');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(10,'IPSAndes Chapinero','Carrera 7 # 54-27');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(11,'DarSalud','Carrera 78 K # 33 A Sur 76');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(12,'Virrey Solís','Cra 49 No.98a – 18');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(13,'Clínica Los Nogales','Cl. 95 #23-61');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(14,'Clínica Marly','Calle 50 #9-67');\n")
file.writelines("insert into IPS (ID_IPS,NOMBRE, LOCALIZACION) values(15,'Hospital Infantil Universitario de San José','Cra. 52 #67a-71');\n")
file.writelines("\n")

#------------------------------------------------------------------------------
#--------------------------- Poblar Tabla SERVICIOS. 3.------------------------
#------------------------------------------------------------------------------
#------------------------------------------------------------------------------
#--------------------------- Insertar 8 Tipos de servicio.---------------------
#------------------------------------------------------------------------------

file.writelines("INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(1,'Medicina general');\n")
file.writelines("INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(2,'Urgencias');\n")
file.writelines("INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(3,'Consulta especialista');\n")
file.writelines("INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(4,'Examenes diagnosticos');\n")
file.writelines("INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(5,'Hospitalizacion');\n")
file.writelines("INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(6,'Terapias');\n")
file.writelines("INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(7,'Procedimientos medicos especializados');\n")
file.writelines("INSERT INTO TIPOSERVICIO (ID,NOMBRE) VALUES(8,'Consulta de control');\n")
file.writelines("\n")

#------------------------------------------------------------------------------
#--------------------------- Insertar servicios.-------------------------------
#------------------------------------------------------------------------------

file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(1,'Consulta médica general', 1);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(2,'Consulta odontológica', 1);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(3,'Consulta con enfermería', 1);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(4,'Consulta de control', 1);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(5,'Urgencias',2);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(6,'Oftalmología',3);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(7,'Ginecología',3);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(8,'Pedriatría',3);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(9,'Medicina interna',3);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(10,'Ortopedia',3);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(11,'Urología',3);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(12,'Dermatología',3);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(13,'Oncología',3);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(14,'Psicología',3);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(15,'Psiquiatría',3);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(16,'Neurología',3);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(17,'Rayos X',4);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(18,'Ecografía',4);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(19,'Tomografía',4);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(20,'Endoscopia',4);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(21,'Citología',4);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(22,'Hemograma',4);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(23,'Electrocardiograma',4);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(24,'Resonancia magnética',4);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(25,'UCI',5);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(26,'Hospitalización por cirugía',5);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(27,'Unidad de quemados',5);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(28,'Hospitalización por tratamiento',5);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(29,'Quimioterapia',6);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(30,'Radioterapia',6);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(31,'Terapia respiratoria',6);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(32,'Fisioterapia',6);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(33,'Diálisis',7);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(34,'Trasplante',7);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(35,'Intervención',7);\n")
file.writelines("insert into SERVICIOS (ID_SERVICIO,NOMBRE, TIPO) values(36,'Cirugía ambulatoria',7);\n")

file.writelines("\n")

#------------------------------------------------------------------------------
#--------------------------- Poblar Tabla Usuarios. 4.-------------------------
#------------------------------------------------------------------------------

#------------------------------------------------------------------------------
#--------------------------- Insertar 40k afiliados.---------------------------
#------------------------------------------------------------------------------

#------------------------------------------------------------------------------
#-------------------------- Poblar Tabla Afiliados. 6.-------------------------
#------------------------------------------------------------------------------

i = 0
while i < constants.AFILIADOS:
    i += 1
    fecha = datetime.datetime.strptime('{} {}'.format(random.randint(1, 366), 2019), '%j %Y')
    L = ["insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) "
         "values (" +str(i) +", '"+ random.choice(tipoDoc) +"', 'randomMail@gmail.com', '" +random.choice(nombre)+ " " +random.choice(apellido)+ "', 1); \n",
         "insert into AFILIADOS (NUMDOC, FECHANACIMIENTO) values (" + str(i) + ", TO_DATE('" +str(fecha.day)+"/" + str(fecha.month) + "/" + str(random.randint(1940,2018)) + "', 'DD/MM/YYYY') ); \n"]
    file.writelines(L)
file.writelines("\n")

#------------------------------------------------------------------------------
#--------------------------- Insertar 100 Medicos.-----------------------------
#------------------------------------------------------------------------------

#------------------------------------------------------------------------------
#--------------------------- Poblar Tabla Medicos. 5.--------------------------
#------------------------------------------------------------------------------

#------------------------------------------------------------------------------
#--------------------- Poblar Tabla MEDICOSABSCRITOS. 9------------------------
#------------------------------------------------------------------------------

#------------------------------------------------------------------------------
#--------------------- Poblar Tabla SERVICIOSMEDICOS. 10-----------------------
#------------------------------------------------------------------------------



i = 0
while i < constants.MEDICOS:
    i += 1
    L = ["insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) "
         "values (" +str(constants.AFILIADOS+i) +", '"+ random.choice(tipoDocMed) +"', 'randomMail@gmail.com', '" +random.choice(nombre)+ " " +random.choice(apellido)+ "', 2); \n",
         "insert into MEDICOS (NUMDOC, REGMEDICO) values (" + str(constants.AFILIADOS+i) + ", " + str(constants.AFILIADOS+i) + "); \n",
         "insert into  MEDICOSABSCRITOS (NUMDOC, ID_IPS) values(" + str(constants.AFILIADOS+i) + "," + str(int(1+constants.IPSS/constants.MEDICOS*(i-1))) + ");\n",
         "insert into SERVICIOSMEDICOS (NUMDOC, ID_SERVICIO, ESPECIALIDAD) values(" + str(constants.AFILIADOS+i) + "," + str(int(1+constants.SERVICIOS/constants.MEDICOS*(i-1))) + ", 'especialidad');\n"]
    file.writelines(L)
file.writelines("\n")

#-------------------------------------------------------------------------------
#-------------------------- Insertar 45 recepcionistas.--------------------------
#-------------------------------------------------------------------------------

i = 0
while i < constants.RECEPCIONISTAS:
    i += 1
    L = ["insert into USUARIOS (numdoc, TIPODOC, CORREO, NOMBRE, ID_ROL) "
         "values (" +str(constants.AFILIADOS+constants.MEDICOS+i) +", '"+ random.choice(tipoDocMed) +"', 'randomMail@gmail.com', '" +random.choice(nombre)+ " " +random.choice(apellido)+ "', 3); \n",
         "insert into RECEPCIONISTAS (NUMDOC, ID_IPS) values (" + str(constants.AFILIADOS+constants.MEDICOS+i) + "," + str(int(1+constants.IPSS/constants.RECEPCIONISTAS*(i-1)))+ ");\n"]
    file.writelines(L)
file.writelines("\n")

#-------------------------------------------------------------------------------
#------------------------------- Insertar 1 Gerente.----------------------------
#-------------------------------------------------------------------------------

file.writelines("insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(127,'CC', 'p.casas@gmail.com', 'Pablo Casas', 4);\n")

#-------------------------------------------------------------------------------
#---------------------- Insertar 1 Administrador de datos.----------------------
#-------------------------------------------------------------------------------

file.writelines("insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(128,'CC', 'genesis@hotmail.com', 'Laura Perez',5);\n")

#-------------------------------------------------------------------------------
#---------------------- Insertar 1 Organizador de CampaÃ±a.----------------------
#-------------------------------------------------------------------------------

file.writelines("insert into USUARIOS (NUMDOC, TIPODOC, CORREO, NOMBRE, ID_ROL) values(129,'CC', 'k.duque@gmail.com', 'Kevin Duque',6);\n")

#------------------------------------------------------------------------------
#------------------------ Poblar Tabla SERVICIOSIPS. 8.------------------------
#------------------------------------------------------------------------------

i = 0
while i < constants.SERVICIOS_IPS:
    i += 1
    L = ["insert into SERVICIOSIPS (ID_IPS, ID_SERVICIO, CAPACIDAD, HORARIOINICIO, HORARIOFIN) "
         "values(" + str(int(1+constants.IPSS/constants.SERVICIOS_IPS*(i-1))) + "," + str(int(1+constants.SERVICIOS/constants.SERVICIOS_IPS*(i-1))) + "," + str(random.randint(5,30)) +
         ",TO_DATE('1900-01-01 " + str(random.randint(5,8)) + ":00','YYYY-MM-DD HH24:MI'),TO_DATE('1900-01-01 " + str(random.randint(17,22)) + ":00','YYYY-MM-DD HH24:MI'));\n"]
    file.writelines(L)
file.writelines("\n")

#------------------------------------------------------------------------------
#------------------------ Poblar Tabla RECETAS 11. ----------------------------
#------------------------------------------------------------------------------

file.writelines("insert into RECETAS (ID_RECETA, ID_MEDICO, ID_AFILIADO) values(1,104, 1);")
file.writelines("\n")

#------------------------------------------------------------------------------
#----------------------- Poblar Tabla MEDICAMENTOS 12.  -----------------------
#------------------------------------------------------------------------------

file.writelines("insert into MEDICAMENTOS (ID_MEDICAMENTO, NOMBRE, DESCRIPCION) values(1,'Acetaminofen', 'Pastilla que cura todo.');")
file.writelines("\n")

#------------------------------------------------------------------------------
#----------------------- Poblar Tabla ITEMSRECETA 13. -------------------------
#------------------------------------------------------------------------------

file.writelines("insert into ITEMSRECETA (ID_RECETA, ID_MEDICAMENTO, CANTIDAD, INDICACIONES) values(1,1,12, 'Tomar por 4 dias cada 8 horas.');")
file.writelines("\n")
file.writelines("\n")

#------------------------------------------------------------------------------
#------------------------ Poblar Tabla ORDENES 14. ----------------------------
#------------------------------------------------------------------------------

#------------------------------------------------------------------------------
#-------------------------- RESERVASERVICIO 15. -------------------------------
#------------------------------------------------------------------------------

i = 0
with open('poblacionOrdenes.csv', mode='w', newline='') as ordenes:
    ordenes_writer = csv.writer(ordenes, delimiter=',', quoting=csv.QUOTE_MINIMAL)
    ordenes_writer.writerow(['ID', 'ID_SERVICIO', 'ID_MEDICO', 'ID_AFILIADO','','ID_SERVICIO','NUMDOC','NUMDOC','ID_IPS','FECHAHORA','','ID_SERVICIO','NUMDOC','NUMDOC','ID_IPS','FECHAHORA','ID_RECEPCIONISTA'])
    while i < constants.ORDENES:
        i += 1
        fecha = datetime.datetime.strptime('{} {}'.format(random.randint(1, 366), random.randint(2018,2019)), '%j %Y')
        fecha = fecha+datetime.timedelta(seconds=random.randint(36000,72000))
        servicio = random.randint(1,constants.SERVICIOS)
        while servicio == 1 or servicio == 5:
            servicio = random.randint(1, constants.SERVICIOS)
        afiliado = random.randint(1,constants.AFILIADOS)
        medico = int(servicio*100/36+constants.AFILIADOS)
        ips=int(servicio*15/36+8/36)
        recepcionista = int((ips-1)*3+random.randint(1,3)+constants.AFILIADOS+constants.MEDICOS)

        ordenes_writer.writerow([i, servicio, medico, afiliado])

        ##if i<constants.RESERVA_SERVICIO:
            ##L = ["insert into RESERVASERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA) values(" + str(servicio) + ", " + str(afiliado) + "," + str(ips) + ",TO_DATE('" + str(fecha)+ "','YYYY-MM-DD HH24:MI:SS'));\n"]

        ##if i<constants.PRESTACION_SERVICIO:
            ##L = ["insert into PRESTACIONSERVICIO (ID_SERVICIO, NUMDOC, ID_IPS, FECHAHORA, ID_RECEPCIONISTA) values(" + str(servicio) + ", " + str(afiliado) + "," + str(ips) + ",TO_DATE('" + str(fecha)+ "','YYYY-MM-DD HH24:MI:SS'), " + str(recepcionista) + ");\n"]


file.close()  # to change file access modes