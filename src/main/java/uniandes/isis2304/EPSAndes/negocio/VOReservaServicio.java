package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public interface VOReservaServicio {

	int getnumDocAfiliado();
	
	long getIdServicio();
	long getIdIPS();
	
	Timestamp getFechaHora();
}
