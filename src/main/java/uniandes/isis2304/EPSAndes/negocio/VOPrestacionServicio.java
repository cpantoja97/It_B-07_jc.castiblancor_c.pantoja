package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public interface VOPrestacionServicio {
	
	int getNumDocAfiliado();
	long getIdServicio();
	long getIdIPS();
	Timestamp getFechaHora();
	long getIdRecepcionista();
}