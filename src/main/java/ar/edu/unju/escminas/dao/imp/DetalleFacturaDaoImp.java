package ar.edu.unju.escminas.dao.imp;

import javax.persistence.EntityManager;

import ar.edu.unju.escminas.config.EmfSingleton;
import ar.edu.unju.escminas.dao.IDetalleFacturaDao;
import ar.edu.unju.escminas.dominio.DetalleFactura;

public class DetalleFacturaDaoImp implements IDetalleFacturaDao{
	
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

	public void guardarDetalle(DetalleFactura detalle) {
		// TODO Auto-generated method stub
		manager.getTransaction().begin();
		manager.persist(detalle);
		manager.getTransaction().commit();
	}

	public void borrarDetalle(DetalleFactura detalle) {
		// TODO Auto-generated method stub
		manager.getTransaction().begin();
		manager.remove(detalle);
		manager.getTransaction().commit();	
	}

}
