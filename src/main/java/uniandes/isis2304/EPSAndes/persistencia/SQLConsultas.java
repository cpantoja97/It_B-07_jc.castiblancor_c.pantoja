package uniandes.isis2304.EPSAndes.persistencia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;

class SQLConsultas {

	private static Logger log = Logger.getLogger(SQLConsultas.class.getName());

	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLConsultas(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	// RFC 1 MOSTRAR LA CANTIDAD DE SERVICIOS PRESTADOS POR CADA IPS DURANTE UN PERIODO DE TIEMPO Y EN EL A�O CORRIDO.
	public List<Object> RF1(PersistenceManager pm, Timestamp anio,Timestamp fechaHoraInicio, Timestamp fechaHoraFin) {
		String sql = "SELECT IPS.NOMBRE, COUNT(*) ";
		sql+= "FROM ";
		sql+= peps.darTablaIPS() +" IPS ";
		sql+= " INNER JOIN ";
		sql+= peps.darTablaPrestacionServicio() + " prestacion ";
		sql+= "ON IPS.ID_IPS = prestacion.ID_IPS ";
		sql+= "WHERE FECHAHORA >? AND FECHAHORA BETWEEN ? AND ? ";
		sql+= "GROUP BY IPS.NOMBRE ";

		log.info ("mandando consulta");

		Query q = pm.newQuery(SQL, sql);
		q.setParameters(anio, fechaHoraInicio, fechaHoraFin);
		return q.executeList();  
	}

	// RFC 2 MOSTRAR LOS 20 SERVICIOS M�S SOLICITADOS.
	public List<Object>  RF2(PersistenceManager pm) {
		String sql = "SELECT * ";
		sql+= "FROM ( ";
		sql+= "SELECT servicio.NOMBRE, COUNT(*) AS cuenta ";
		sql+= "FROM ";
		sql+= peps.darTablaServicio() + " servicio ";
		sql+= "NATURAL INNER JOIN ";
		sql+= peps.darTablaReservaServicio() + " reserva ";
		sql+= "GROUP BY servicio.NOMBRE "; 
		sql+= "ORDER BY cuenta DESC ) ";
		sql+= "WHERE ROWNUM <20" ;

		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	// RFC3 MOSTRAR EL �NDICE DE USO DE CADA UNO DE LOS SERVICIOS PROVISTOS.
	public List<Object> RF3(PersistenceManager pm) {
		String sql = " SELECT servicio.NOMBRE, uso/capacidad ";
		sql+= "FROM ";
		sql+= peps.darTablaServicio() + " servicio ";
		sql+= "INNER JOIN ( ";
		sql+= "SELECT prestacion.ID_SERVICIO AS id, COUNT(*) AS uso ";
		sql+= "FROM ";
		sql+= peps.darTablaPrestacionServicio() + " prestacion ";
		sql+= "GROUP BY prestacion.ID_SERVICIO ) aux1 ";
		sql+= "ON servicio.ID_SERVICIO = aux1.id ";
		sql+= "INNER JOIN ( ";
		sql+= "SELECT ServIPS.ID_SERVICIO AS id, SUM(ServIPS.CAPACIDAD) AS capacidad ";
		sql+= "FROM ";
		sql+= peps.darTablaServiciosIPS()+ " ServIPS ";
		sql+= "GROUP BY ServIPS.ID_SERVICIO ) aux2 ON aux1.id = aux2.id";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

	// RFC4 - MOSTRAR LOS SERVICIOS QUE CUMPLEN CON CIERTA CARACTER�STICA. jeje no funciona :*
	public List<Object> RF4(PersistenceManager pm, int idRecepcionista) {
		String sql = " SELECT servicios.* ";
		sql+= "FROM ";
		sql+= peps.darTablaServicio() + " INNER JOIN " + peps.darTablaPrestacionServicio();
		sql+= " ON SERVICIOS.ID_SERVICIO = PRESTACIONSERVICIO.ID_SERVICIO ";
		sql+= " WHERE prestacionservicio.ID_RECEPCIONISTA = ? ";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(idRecepcionista);
		return q.executeList(); 
	}

	// RFC5 - MOSTRAR LA UTILIZACI�N DE SERVICIOS DE EPSANDES POR UN AFILIADO DADO.
	public List<Object> RF5(PersistenceManager pm, int numDoc, Timestamp f1, Timestamp f2) {
		String sql = " SELECT servicio.NOMBRE, COUNT(*) ";
		sql+= "FROM ";
		sql+= peps.darTablaServicio() + " servicio ";
		sql+= "INNER JOIN ";
		sql+= peps.darTablaPrestacionServicio() + " prestacion ";
		sql+= "ON servicio.ID_SERVICIO = prestacion.ID_SERVICIO ";
		sql+= "WHERE prestacion.NUMDOC = ? ";
		sql+= "AND prestacion.FECHAHORA BETWEEN ? AND ? ";
		sql+= "GROUP BY servicio.NOMBRE ";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(numDoc, f1,f2);
		return q.executeList(); 
	}

	// RFC6 -ANALIZAR LA OPERACIÓN DE EPSANDES.
	public List<Object> RF6(PersistenceManager pm, String tiempo, String servicio) {
		String sql = " SELECT * ";
		sql+= "FROM ( ";
		sql+= " select TRUNC ( reservaServicio.fechaHora, 'IW' ), count(*) as cuenta ";
		sql+= "FROM ";
		sql+= peps.darTablaServicio() + " servicios ";
		sql+= "inner join " + peps.darTablaReservaServicio() + " reservaServicio ";
		sql+= "on servicios.id_servicio = reservaServicio.id_servicio ";
		sql += "where servicios.nombre = 'Oftalmologia' ";
		sql+= "Group by TRUNC ( reservaServicio.fechaHora, 'IW' ) ";
		sql+= "Order by cuenta desc ";
		sql+= " ) WHERE ROWNUM < 3 ";
		Query q = pm.newQuery(SQL, sql);
		//q.setParameters(tiempo, servicio, tiempo);
		return q.executeList(); 		

	}

	public List<Object> RF62(PersistenceManager pm, String tiempo, String servicio) {
		String sql = " SELECT * ";
		sql+= "FROM ( ";
		sql+= " select TRUNC ( reservaServicio.fechaHora, 'IW' ), count(*) as cuenta ";
		sql+= "FROM ";
		sql+= peps.darTablaServicio() + " servicios ";
		sql+= "inner join " + peps.darTablaReservaServicio() + " reservaServicio ";
		sql+= "on servicios.id_servicio = reservaServicio.id_servicio ";
		sql += "where servicios.nombre = 'Oftalmologia' ";
		sql+= "Group by TRUNC ( reservaServicio.fechaHora, 'IW' ) ";
		sql+= "Order by cuenta asc ";
		sql+= " ) WHERE ROWNUM < 3 ";
		Query q = pm.newQuery(SQL, sql);
		//q.setParameters(tiempo, servicio,tiempo);
		return q.executeList(); 		

	}

	public List<Object> RF63(PersistenceManager pm, String tiempo, String servicio) {
		String sql = " SELECT * ";
		sql+= "FROM ( ";
		sql+= " select TRUNC ( prestacionServicio.fechaHora, 'IW' ), count(*) as cuenta ";
		sql+= "FROM ";
		sql+= peps.darTablaPrestacionServicio() + " prestacionServicio ";
		sql+= "inner join " + peps.darTablaServicio() + " servicios ";
		sql+= "on servicios.id_servicio = prestacionServicio.id_servicio ";
		sql += "where servicios.nombre = 'Oftalmologia' ";
		sql+= "Group by TRUNC ( prestacionServicio.fechaHora, 'IW' ) ";
		sql+= "Order by cuenta desc ";
		sql+= " ) WHERE ROWNUM < 3 ";
		Query q = pm.newQuery(SQL, sql);
		//q.setParameters(tiempo, servicio, tiempo);
		return q.executeList(); 		

	}

	// RFC7 - ENCONTRAR LOS AFILIADOS EXIGENTES.
	public List<Object> RF7(PersistenceManager pm) {
		String sql = " SELECT aux.numdoc, count(*), sum(cuenta) as suma ";
		sql+= "FROM ( ";
		sql+= "select prestacionServicio.numdoc, servicios.tipo, count(*) as cuenta ";
		sql+= "from ";
		sql+= peps.darTablaPrestacionServicio();
		sql+= " inner join "+peps.darTablaServicio() + " servicios ";
		sql+= "on servicios.id_servicio = prestacionservicio.id_servicio ";
		sql+= "group by prestacionServicio.numdoc , servicios.tipo ";
		sql+= ") aux ";
		sql+= "group by aux.numdoc ";
		sql+= "having count(*)>1 and sum(cuenta)>1 ";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

	// RFC8 - ENCONTRAR LOS SERVICIOS QUE NO TIENEN MUCHA DEMANDA.
	public List<Object> RF8(PersistenceManager pm) {
		String sql = " SELECT servicios.nombre ";
		sql+= "FROM servicios left outer join ( ";
		sql+= "select aux.nombre ";
		sql+= "from ( ";
		sql+= "select servicios.nombre, TRUNC( reservaServicio.fechaHora,'IW' ) ";
		sql+= "from " + peps.darTablaServicio() + " servicios ";
		sql+= "inner join " + peps.darTablaReservaServicio() + " reservaServicio ";
		sql+= " on servicios.id_servicio = reservaServicio.id_servicio ";
		sql+= "Group by servicios.nombre, TRUNC( reservaServicio.fechaHora, 'IW' ) ";
		sql+= "having count(*) >2 ) aux ";
		sql+= "group by aux.nombre ";
		sql+= "having count(*)=1 ";
		sql+= ") aux2 ";
		sql+= "on servicios.nombre = aux2.nombre ";
		sql+= "where aux2.nombre is null ";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

	// RFC9 - CONSULTAR LA PRESTACIÓN DE SERVICIOS EN EPSANDES.
	public List<Object> RF9sinAgrupar(PersistenceManager pm, Timestamp f1, Timestamp f2, long idServicio, long tipo, long ips, String orden,  boolean organizador, int orgID) {
		
		String sql = "SELECT usuarios.nombre, afiliados.*, servicios.tipo, prestacionservicio.id_servicio, prestacionservicio.id_ips, prestacionservicio.fechahora ";
		sql+= "FROM " + peps.darTablaUsuario() +" usuarios, ";
		sql+= peps.darTablaAfiliados() +" afiliados, ";
		sql+= peps.darTablaPrestacionServicio() +" prestacionservicio, ";
		sql+= peps.darTablaServicio() +" servicios";
		if( organizador) {
			sql+= ", " + peps.darTablaCampania();
		}
		sql+= " where usuarios.numdoc = afiliados.numdoc";
		sql+= " and afiliados.numdoc = prestacionservicio.numdoc";
		sql+= " and servicios.id_servicio = prestacionservicio.id_servicio";
		
		List parametros = new ArrayList();
		if(f1!= null && f2!= null) {
			sql+= " and prestacionservicio.fechahora between ? and ?";
			parametros.add(f1);
			parametros.add(f2);
		}
		if(idServicio != -1) {
			sql+= " and servicios.id_servicio = ?";
			parametros.add(idServicio);
		}
		if(tipo !=-1) {
			sql+= " and servicios.tipo = ?";
			parametros.add(tipo);
		}
		if(ips != -1) {
			sql+= " and prestacionservicio.ID_IPS = ?";
			parametros.add(ips);
		}
		if( organizador) {
			sql+= " and prestacionservicio.campania = campania.id";
			sql+= " and campania.organizador = ?";
			parametros.add(orgID);
		}
		
		if (orden != null) {
			sql+= " ORDER BY ";
			sql+= orden;
		}

		Query q = pm.newQuery(SQL, sql);
		Object[] params = parametros.toArray();
		q.setParameters(params);
		return q.executeList(); 
	}

	// RFC10 - CONSULTAR LA PRESTACIÓN DE SERVICIOS EN EPSANDES – RFC9-V2.
	public List<Object> RF10criterioServicio(PersistenceManager pm, Timestamp f1, Timestamp f2, long idServicio, long tipo, long ips, String orden, boolean organizador, int orgID) {
		List parametros = new ArrayList();
		
		String sql = "SELECT t1.* ";
		sql+= "FROM " + peps.darTablaServicio() +" t1";
		sql+= " LEFT OUTER JOIN "; //join con mi selección de prestacion
		sql+= "( SELECT * FROM "+ peps.darTablaPrestacionServicio();
		if( organizador) {
			sql+= ", " + peps.darTablaCampania();
		}
		sql+= " WHERE 1=1 ";
		//condicionales
		if(f1!= null && f2!= null) {
			sql+= " and prestacionservicio.fechahora between ? and ?";
			parametros.add(f1);
			parametros.add(f2);
		}
		if(ips != -1) {
			sql+= " and prestacionservicio.ID_IPS = ?";
			parametros.add(ips);
		}
		if( organizador) {
			sql+= " and prestacionservicio.campania = campania.id ";
			sql+= " and campania.organizador = ? ";
			parametros.add(orgID);
		}
		sql+= ") AUX_PRESTACION "; 
		sql+= "ON t1.ID_SERVICIO = AUX_PRESTACION.ID_SERVICIO ";
		sql+= "WHERE AUX_PRESTACION.FECHAHORA IS NULL ";
		if(tipo !=-1) {
			sql+= " and t1.tipo = ?";
			parametros.add(tipo);
		}

		if (orden != null) {
			sql+= " ORDER BY ";
			sql+= orden;
		}

		Query q = pm.newQuery(SQL, sql);
		Object[] params = parametros.toArray();
		q.setParameters(params);
		return q.executeList(); 
	}
	
	public List<Object> RF10criterioGente(PersistenceManager pm, Timestamp f1, Timestamp f2, long idServicio, long tipo, long ips, String orden, boolean organizador, int orgID) {
		List parametros = new ArrayList();
		
		String sql = "SELECT t1.NUMDOC, t1.NOMBRE, t1.FECHANACIMIENTO, t1.CORREO ";
		sql+= "FROM ( ";
		sql+= "SELECT USUARIOS.*, AFILIADOS.FECHANACIMIENTO ";
		sql+= "FROM "+ peps.darTablaAfiliados() +" INNER JOIN USUARIOS ON AFILIADOS.NUMDOC = USUARIOS.NUMDOC ";
		sql+= ") t1 LEFT OUTER JOIN ("; //join con mi selección de prestacion
		sql+= "SELECT PRESTACIONSERVICIO.* ";
		sql+= "FROM "+ peps.darTablaPrestacionServicio();
		sql+= ", " + peps.darTablaServicio();
		if( organizador) {
			sql+= ", " + peps.darTablaCampania();
		}
		sql+= " WHERE PRESTACIONSERVICIO.ID_SERVICIO = SERVICIOS.ID_SERVICIO";
		//condicionales
		if(f1!= null && f2!= null) {
			sql+= " and prestacionservicio.fechahora between ? and ?";
			parametros.add(f1);
			parametros.add(f2);
		}
		if(idServicio != -1) {
			sql+= " and servicios.id_servicio = ?";
			parametros.add(idServicio);
		}
		if(tipo !=-1) {
			sql+= " and servicios.tipo = ?";
			parametros.add(tipo);
		}
		if( organizador) {
			sql+= " and prestacionservicio.campania = campania.id";
			sql+= " and campania.organizador = ?";
			parametros.add(orgID);
		}
		sql+= " ) AUX_PRESTACION "; 
		sql+= "ON t1.NUMDOC = AUX_PRESTACION.NUMDOC ";
		sql+= "WHERE AUX_PRESTACION.FECHAHORA IS NULL";
		
		
		if (orden != null) {
			sql+= " ORDER BY ";
			sql+= orden;
		}

		Query q = pm.newQuery(SQL, sql);
		Object[] params = parametros.toArray();
		q.setParameters(params);
		return q.executeList(); 
	}
	
	public List<Object> RF10criterioIPS(PersistenceManager pm, Timestamp f1, Timestamp f2, long idServicio, long tipo, long ips, String orden, boolean organizador, int orgID) {
		List parametros = new ArrayList();
		
		String sql = "SELECT t1.* ";
		sql+= "FROM ( ";
		sql+= "SELECT IPS.*, SERVICIOSIPS.ID_SERVICIO, SERVICIOS.NOMBRE NOMSERVICIO, SERVICIOS.TIPO ";
		sql+= "FROM IPS, SERVICIOSIPS, SERVICIOS "; 
		sql+= "WHERE IPS.ID_IPS = SERVICIOSIPS.ID_IPS AND SERVICIOSIPS.ID_SERVICIO = SERVICIOS.ID_SERVICIO";
		sql+= ") t1 LEFT OUTER JOIN ( "; //join con mi selección de prestacion
		sql+= "SELECT PRESTACIONSERVICIO.*, SERVICIOS.TIPO "; 
		sql+= "FROM PRESTACIONSERVICIO, SERVICIOS";
		if( organizador) {
			sql+= ", " + peps.darTablaCampania();
		}
		sql+= " WHERE PRESTACIONSERVICIO.ID_SERVICIO = SERVICIOS.ID_SERVICIO ";
		//condicionales
		if(f1!= null && f2!= null) {
			sql+= " and prestacionservicio.fechahora between ? and ?";
			parametros.add(f1);
			parametros.add(f2);
		}
		if( organizador) {
			sql+= " and prestacionservicio.campania = campania.id";
			sql+= " and campania.organizador = ?";
			parametros.add(orgID);
		}
		sql+= ") AUX_PRESTACION "; 
		sql+= "ON t1.ID_SERVICIO = AUX_PRESTACION.ID_SERVICIO AND t1.ID_IPS = AUX_PRESTACION.ID_IPS ";
		sql+= "WHERE AUX_PRESTACION.FECHAHORA IS NULL ";
		if(tipo !=-1) {
			sql+= " and t1.tipo = ?";
			parametros.add(tipo);
		}
		if(idServicio != -1) {
			sql+= " and t1.ID_SERVICIO = ?";
			parametros.add(idServicio);
		}
		if (orden != null) {
			sql+= " ORDER BY ";
			sql+= orden;
		}

		Query q = pm.newQuery(SQL, sql);
		Object[] params = parametros.toArray();
		q.setParameters(params);
		return q.executeList(); 
	}
	
	// RFC11 - CONSULTAR FUNCIONAMIENTO.
	public List<Object> RF11(PersistenceManager pm) {
		String sql = " "
				+ "select t1.week, t1.cantidad, t2.serviciomas, t3.serviciomenos, t4.ipsmas, t5.ipsmenos, t6.afiliado, t7.tipomenos, t8.tipomas\n" + 
				"from\n" + 
				"(\n" + 
				"    select week, (\n" + 
				"    select count(*) as cantidad\n" + 
				"    from afiliados\n" + 
				"    )- count(*) as cantidad\n" + 
				"    from (\n" + 
				"        select TRUNC( prestacionServicio.fechaHora, 'IW' ) as week, prestacionservicio.numdoc\n" + 
				"        from prestacionServicio\n" + 
				"        Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), numdoc\n" + 
				"    )\n" + 
				"    group by week\n" + 
				") t1\n" + 
				",\n" + 
				"(\n" + 
				"    select week, serviciomas\n" + 
				"    from\n" + 
				"        (\n" + 
				"        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_servicio as serviciomas, count(*), \n" + 
				"            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) \n" + 
				"            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn\n" + 
				"        from prestacionservicio \n" + 
				"        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_servicio\n" + 
				"        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) desc\n" + 
				"        )\n" + 
				"    where rn = 1\n" + 
				") t2\n" + 
				",\n" + 
				"(\n" + 
				"    select week, serviciomenos\n" + 
				"    from\n" + 
				"        (\n" + 
				"        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_servicio as serviciomenos, count(*), \n" + 
				"            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) \n" + 
				"            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn\n" + 
				"        from prestacionservicio \n" + 
				"        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_servicio\n" + 
				"        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc\n" + 
				"        )\n" + 
				"    where rn = 1\n" + 
				") t3\n" + 
				",\n" + 
				"(\n" + 
				"    select week, ipsmas\n" + 
				"    from\n" + 
				"        (\n" + 
				"        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_ips as ipsmas, count(*), \n" + 
				"            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) \n" + 
				"            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn\n" + 
				"        from prestacionservicio \n" + 
				"        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_ips\n" + 
				"        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) desc\n" + 
				"        )\n" + 
				"    where rn = 1\n" + 
				") t4\n" + 
				",\n" + 
				"(\n" + 
				"    select week, ipsmenos\n" + 
				"    from\n" + 
				"        (\n" + 
				"        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, id_ips as ipsmenos, count(*), \n" + 
				"            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) \n" + 
				"            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn\n" + 
				"        from prestacionservicio \n" + 
				"        group by TRUNC( prestacionServicio.fechaHora,'IW' ), id_ips\n" + 
				"        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc\n" + 
				"        )\n" + 
				"    where rn = 1\n" + 
				") t5\n" + 
				",\n" + 
				"(\n" + 
				"    select week, afiliado\n" + 
				"    from\n" + 
				"        (\n" + 
				"        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, numdoc as afiliado, count(*), \n" + 
				"            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) \n" + 
				"            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn\n" + 
				"        from prestacionservicio \n" + 
				"        group by TRUNC( prestacionServicio.fechaHora,'IW' ), numdoc\n" + 
				"        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc\n" + 
				"        )\n" + 
				"    where rn = 1\n" + 
				") t6\n" + 
				",\n" + 
				"(\n" + 
				"    select week, tipomenos\n" + 
				"    from\n" + 
				"        (\n" + 
				"        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, servicios.tipo as tipomenos, count(*), \n" + 
				"            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) \n" + 
				"            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn\n" + 
				"        from prestacionservicio inner join servicios \n" + 
				"        on prestacionservicio.id_servicio = servicios.id_servicio\n" + 
				"        group by TRUNC( prestacionServicio.fechaHora,'IW' ), servicios.tipo\n" + 
				"        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) desc\n" + 
				"        )\n" + 
				"    where rn = 1\n" + 
				") t7\n" + 
				",\n" + 
				"(\n" + 
				"    select week, tipomas\n" + 
				"    from\n" + 
				"        (\n" + 
				"        select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, servicios.tipo as tipomas, count(*), \n" + 
				"            ROW_NUMBER() OVER(PARTITION BY TRUNC( prestacionServicio.fechaHora,'IW' ) \n" + 
				"            ORDER BY  TRUNC( prestacionServicio.fechaHora,'IW' ) DESC) AS rn\n" + 
				"        from prestacionservicio inner join servicios \n" + 
				"        on prestacionservicio.id_servicio = servicios.id_servicio\n" + 
				"        group by TRUNC( prestacionServicio.fechaHora,'IW' ), servicios.tipo\n" + 
				"        order by TRUNC( prestacionServicio.fechaHora,'IW' ), count(*) asc\n" + 
				"        )\n" + 
				"    where rn = 1\n" + 
				") t8\n" + 
				" where t1.week=t2.week and t2.week=t3.week and t3.week=t4.week and t4.week=t5.week\n" + 
				" and t5.week=t6.week and t6.week=t7.week and t7.week=t8.week\n" + 
				" order by t1.week";
 
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

	// RFC12 - CONSULTAR LOS AFILIADOS COSTOSOS.
	public List<Object> RF12a(PersistenceManager pm) {
		// afiliados que van cada mes. 
		String sql = " select numdoc from ( ";
		sql+= "select afiliados.numdoc, TRUNC( prestacionServicio.fechaHora, 'MM' ) as meses ";
		sql+= "from "+peps.darTablaAfiliados()+" afiliados, "+peps.darTablaPrestacionServicio()+" prestacionservicio ";
		sql+= "where afiliados.numdoc = prestacionservicio.numdoc ";
		sql+= "group by afiliados.numdoc, TRUNC( prestacionServicio.fechaHora, 'MM' ) ) ";
		sql+= "group by numdoc ";
		sql+= "having count(*)=24 ";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

	public List<Object> RF12b(PersistenceManager pm) {
		// afiliados que siempre requieren servicios medicos especializados
		String sql = " select afiliados.numdoc ";
		sql+= "from "+peps.darTablaAfiliados()+" afiliados, "+peps.darTablaPrestacionServicio()+" prestacionservicio , " +peps.darTablaServicio()+" servicios ";
		sql+= "where afiliados.numdoc = prestacionservicio.numdoc and prestacionservicio.id_servicio = servicios.id_servicio ";
		sql+= "group by afiliados.numdoc ";
		sql+= "having count(*) = sum(case when servicios.tipo = 7 then 1 else 0 end) ";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

	public List<Object> RF12c(PersistenceManager pm) {
		// afiliados que cada vez que requieren de un servicio de salud terminan hospitalizados. 
		String sql = " SELECT AUX.* \n" + 
				"FROM (\n" + 
				"    SELECT PRESTACIONSERVICIO.NUMDOC, COUNT(*) AS TOTAL, sum(case when servicios.tipo = 5 then 1 else 0 end) AS HOSPITALIZACON\n" + 
				"    FROM PRESTACIONSERVICIO, SERVICIOS\n" + 
				"    WHERE prestacionservicio.id_servicio = servicios.id_servicio\n" + 
				"    GROUP BY prestacionservicio.numdoc\n" + 
				"    ) AUX\n" + 
				"WHERE aux.hospitalizacon >=  (aux.total/2)" ;
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

}
