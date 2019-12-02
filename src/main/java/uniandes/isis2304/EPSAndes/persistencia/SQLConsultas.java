package uniandes.isis2304.EPSAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
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
	public List<Object> RF4(PersistenceManager pm, String atributoServicio) {
		String sql = " SELECT servicio.* ";
		sql+= "FROM ";
		sql+= peps.darTablaServicio() + " servicio ";
		sql+= "WHERE SERVICIOS.? = ? ";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(atributoServicio, 0);
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
	public List<Object> RF9(PersistenceManager pm) {
		//TODO pasar parametros
		String sql = " SELECT afiliados.*, prestacionservicio.fechahora ";
		sql+= "FROM" + peps.darTablaAfiliados() +" afiliados, ";
		sql+= peps.darTablaPrestacionServicio() +" prestacionservicio, ";
		sql+= peps.darTablaServicio() +" servicios ";
		sql+= "where prestacionservicio.fechahora between '27-oct-18' and '29-dec-18' ";
		sql+= "and afiliados.numdoc = prestacionservicio.numdoc";
		sql+= "and servicios.id_servicio = prestacionservicio.id_servicio ";
		sql+= "and servicios.id_servicio = 4 ";
		sql+= "and servicios.tipo = 1 ";
		sql+= "and prestacionservicio.ID_IPS = 1 ";
		Query q = pm.newQuery(SQL, sql);
		//q.setParameters(f1, f2, idServicio, tipo, ips);
		return q.executeList(); 
	}

	// RFC10 - CONSULTAR LA PRESTACIÓN DE SERVICIOS EN EPSANDES – RFC9-V2.
	public List<Object> RF10(PersistenceManager pm) {
		//TODO poner sentencia sql correcta
		String sql = " SELECT afiliados.*, prestacionservicio.fechahora ";
		sql+= "FROM" + peps.darTablaAfiliados() +" afiliados, ";
		sql+= peps.darTablaPrestacionServicio() +" prestacionservicio, ";
		sql+= peps.darTablaServicio() +" servicios ";
		sql+= "where prestacionservicio.fechahora between '27-oct-18' and '29-dec-18' ";
		sql+= "and afiliados.numdoc = prestacionservicio.numdoc";
		sql+= "and servicios.id_servicio = prestacionservicio.id_servicio ";
		sql+= "and servicios.id_servicio = 4 ";
		sql+= "and servicios.tipo = 1 ";
		sql+= "and prestacionservicio.ID_IPS = 1 ";
		Query q = pm.newQuery(SQL, sql);
		//q.setParameters(f1, f2, idServicio, tipo, ips);
		return q.executeList(); 
	}

	// RFC11 - CONSULTAR FUNCIONAMIENTO.
	public List<Object> RF11a(PersistenceManager pm) {
		// sentencia para servicios + y - consumido
		String sql = " select  t1.week, t1.servicio, t1.cuenta ";
		sql+= "from ( ";
		sql+= "select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, prestacionServicio.id_servicio as servicio, count(*) as cuenta ";
		sql+= "from " + peps.darTablaPrestacionServicio() +" prestacionServicio ";
		sql+= "Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), prestacionServicio.id_servicio ";
		sql+= ") t1, ( ";
		sql+= "select week, max(cuenta) as maximo, min(cuenta) as minimo ";
		sql+= "from( ";
		sql+= "select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, prestacionServicio.id_servicio as serv, count(*) as cuenta ";
		sql+= "from " + peps.darTablaPrestacionServicio() +" prestacionServicio ";
		sql+= "Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), prestacionServicio.id_servicio ";
		sql+= ") aux ";
		sql+= "group by aux.week ";
		sql+= ") t2 ";
		sql+= "where (t1.week = t2.week and t1.cuenta = t2.maximo) or (t1.week = t2.week and t1.cuenta = t2.minimo) ";
		sql+= "order by week ";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

	public List<Object> RF11b(PersistenceManager pm) {
		// TODO sentencia para tipo servicios + y - consumido
		String sql = " select  t1.week, t1.ips, t1.cuenta ";
		sql+= "from ( ";
		sql+= "select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, prestacionServicio.id_ips as ips, count(*) as cuenta ";
		sql+= "from " + peps.darTablaPrestacionServicio() +" prestacionServicio ";
		sql+= "Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), prestacionServicio.id_ips ";
		sql+= ") t1, ( ";
		sql+= "select week, max(cuenta) as maximo, min(cuenta) as minimo ";
		sql+= "from( ";
		sql+= "select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, prestacionServicio.id_ips as ips, count(*) as cuenta ";
		sql+= "from " + peps.darTablaPrestacionServicio() +" prestacionServicio ";
		sql+= "Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), prestacionServicio.id_ips ";
		sql+= ") aux ";
		sql+= "group by aux.week ";
		sql+= ") t2 ";
		sql+= "where (t1.week = t2.week and t1.cuenta = t2.maximo) or (t1.week = t2.week and t1.cuenta = t2.minimo) ";
		sql+= "order by week ";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

	public List<Object> RF11c(PersistenceManager pm) {
		// sentencia para ips + y - solicitada
		String sql = " select  t1.week, t1.ips, t1.cuenta ";
		sql+= "from ( ";
		sql+= "select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, prestacionServicio.id_ips as ips, count(*) as cuenta ";
		sql+= "from " + peps.darTablaPrestacionServicio() +" prestacionServicio ";
		sql+= "Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), prestacionServicio.id_ips ";
		sql+= ") t1, ( ";
		sql+= "select week, max(cuenta) as maximo, min(cuenta) as minimo ";
		sql+= "from( ";
		sql+= "select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, prestacionServicio.id_ips as ips, count(*) as cuenta ";
		sql+= "from " + peps.darTablaPrestacionServicio() +" prestacionServicio ";
		sql+= "Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), prestacionServicio.id_ips ";
		sql+= ") aux ";
		sql+= "group by aux.week ";
		sql+= ") t2 ";
		sql+= "where (t1.week = t2.week and t1.cuenta = t2.maximo) or (t1.week = t2.week and t1.cuenta = t2.minimo) ";
		sql+= "order by week ";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

	public List<Object> RF11d(PersistenceManager pm) {
		// afiliado que no han utilizado servicios
		String sql = " select week, ( ";
		sql+= "select count(*) as cantidad ";
		sql+= "from "+ peps.darTablaAfiliados() +" afiliados ";
		sql+= ")- count(*) ";
		sql+= "from ( ";
		sql+= "select TRUNC( prestacionServicio.fechaHora, 'IW' ) as week, prestacionservicio.numdoc ";
		sql+= " from " + peps.darTablaPrestacionServicio() + " prestacionServicio ";
		sql+= "Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), numdoc ) ";
		sql+= "group by week ";
		sql+= "order by week ";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

	public List<Object> RF11e(PersistenceManager pm) {
		// sentencia para ips + y - solicitada
		String sql = " select  t1.week, t1.afiliado ";
		sql+= "from ( ";
		sql+= "select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, prestacionServicio.numdoc as afiliado, count(*) as cuenta ";
		sql+= "from " + peps.darTablaPrestacionServicio() +" prestacionServicio ";
		sql+= "Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), prestacionServicio.numdoc ";
		sql+= ") t1, ( ";
		sql+= "select week, max(cuenta) as maximo";
		sql+= "from( ";
		sql+= "select TRUNC( prestacionServicio.fechaHora,'IW' ) as week, prestacionServicio.numdoc as afiliado, count(*) as cuenta ";
		sql+= "from " + peps.darTablaPrestacionServicio() +" prestacionServicio ";
		sql+= "Group by TRUNC( prestacionServicio.fechaHora, 'IW' ), prestacionServicio.numdoc ";
		sql+= ") aux ";
		sql+= "group by aux.week ";
		sql+= ") t2 ";
		sql+= "where (t1.week = t2.week and t1.cuenta = t2.maximo)";
		sql+= "order by week ";
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
		// TODO afiliados que cada vez que requieren de un servicio de salud terminan hospitalizados. 
		String sql = " select numdoc, count(*) from ( ";
		sql+= "select afiliados.numdoc, TRUNC( prestacionServicio.fechaHora, 'MM' ) as meses ";
		sql+= "from "+peps.darTablaAfiliados()+" afiliados, "+peps.darTablaPrestacionServicio()+" prestacionservicio ";
		sql+= "where afiliados.numdoc = prestacionservicio.numdoc ";
		sql+= "group by afiliados.numdoc, TRUNC( prestacionServicio.fechaHora, 'MM' ) )";
		sql+= "group by numdoc ";
		sql+= "having count(*)=24 ";
		sql+= "";
		sql+= "";
		sql+= "";
		sql+= "";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList(); 
	}

}
