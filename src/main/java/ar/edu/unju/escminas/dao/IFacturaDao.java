package ar.edu.unju.escminas.dao;

import java.util.List;

import ar.edu.unju.escminas.dominio.Factura;

public interface IFacturaDao {
	public void guardarFactura(Factura factura);
	public void borrarFactura(Factura factura);
	public Factura buscarFactura(long numero);
	public List<Factura> mostrarFacturas();
	public void buscarFacturasporTotal();
	
}
