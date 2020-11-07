package ar.edu.unju.escminas.dao;

import java.util.List;

import ar.edu.unju.escminas.dominio.Cliente;

public interface IClienteDao {
	public void guardarCliente(Cliente cliente);
	public void borrarCliente(Cliente cliente);
	public List<Cliente> mostrarClientes();
	public Cliente buscarCliente(long cod);
}
