package ar.edu.unju.escminas.dominio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="detallefacturas")
public class DetalleFactura implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	
	@Column (name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="PRECIOUNITARIO")
	private double precioUnitario;
	
	@Column(name="CANTIDAD")
	private int cantidad;
	
	@Column(name="SUBTOTAL")
	private double subtotal;
	
	@ManyToOne (fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name= "NRO_FACTURA")
	private Factura factura;
	
	public DetalleFactura() {
		
	}

	public DetalleFactura(String descripcion, double precioUnitario, int cantidad, double subtotal, Factura factura) {
		super();
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.factura = factura;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Factura getFacturas() {
		return factura;
	}

	public void setFacturas(Factura facturas) {
		this.factura = facturas;
	}

	@Override
	public String toString() {
		return "DetalleFactura [id=" + id + ", descripcion=" + descripcion + ", precioUnitario=" + precioUnitario
				+ ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", facturas=" + factura + "]";
	}
	
	
}
