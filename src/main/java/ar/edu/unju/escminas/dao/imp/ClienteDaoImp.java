package ar.edu.unju.escminas.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;

import ar.edu.unju.escminas.config.EmfSingleton;
import ar.edu.unju.escminas.dao.IClienteDao;
import ar.edu.unju.escminas.dominio.Cliente;

public class ClienteDaoImp implements IClienteDao {
	
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	public void guardarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		manager.getTransaction().begin();
		manager.persist(cliente);
		manager.getTransaction().commit();	
	}

	public List<Cliente> mostrarClientes() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = (List<Cliente>) manager.createQuery("SELECT e FROM Cliente e").getResultList();
		return clientes;
	}

	public void borrarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		manager.getTransaction().begin();
		manager.remove(cliente);
		manager.getTransaction().commit();	
	}

	@Override
	public Cliente buscarCliente(long cod) {
		// TODO Auto-generated method stub
		return manager.find(Cliente.class, cod);
	}

}
