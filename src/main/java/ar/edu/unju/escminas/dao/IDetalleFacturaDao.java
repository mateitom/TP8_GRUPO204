package ar.edu.unju.escminas.dao;

import ar.edu.unju.escminas.dominio.DetalleFactura;

public interface IDetalleFacturaDao {
	public void guardarDetalle(DetalleFactura detalle);
	public void borrarDetalle(DetalleFactura detalle);
}
