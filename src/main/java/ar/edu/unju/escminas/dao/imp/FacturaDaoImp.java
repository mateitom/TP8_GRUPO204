package ar.edu.unju.escminas.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.edu.unju.escminas.config.EmfSingleton;
import ar.edu.unju.escminas.dao.IFacturaDao;
import ar.edu.unju.escminas.dominio.Factura;



public class FacturaDaoImp implements IFacturaDao{

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	public void guardarFactura(Factura factura) {
		// TODO Auto-generated method stub
		manager.getTransaction().begin();
		manager.persist(factura);
		manager.getTransaction().commit();
	}

	public List<Factura> mostrarFacturas() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Factura> facturas = (List<Factura>) manager.createQuery("SELECT e FROM Factura e").getResultList();
		return facturas;
	}

	public void buscarFacturasporTotal() {
		// TODO Auto-generated method stub
		Query query= manager.createQuery("SELECT e FROM Factura e " + " WHERE e.total > :total ");
		query.setParameter("total", (double)1000);
		@SuppressWarnings("unchecked")
		List<Factura> facturasenc= (List<Factura>)query.getResultList();
		if (facturasenc.isEmpty()) {
			System.out.println("FACTURAS NO ENCONTRADAS");
		}
		else {
		facturasenc.stream().forEach(System.out::println);
		}
	}

	public Factura buscarFactura(long numero) {
		// TODO Auto-generated method stub
		return manager.find(Factura.class, numero);
	}

	public void borrarFactura(Factura factura) {
		// TODO Auto-generated method stub
		manager.getTransaction().begin();
		manager.remove(factura);
		manager.getTransaction().commit();
	}

}
