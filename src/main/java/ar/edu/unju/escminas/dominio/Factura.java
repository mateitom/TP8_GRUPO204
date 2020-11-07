package ar.edu.unju.escminas.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="facturas")
public class Factura implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NROFACTURA")
	private long nrofactura;
	
	@Column(name="FECHA")
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name="CLIENTE")
	private Cliente cliente;
	
	@Column(name="TOTAL")
	private double total;
	
	@OneToMany(mappedBy= "factura",fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
	
	public Factura() {
		
	}

	public Factura(LocalDate fecha, Cliente cliente, double total, List<DetalleFactura> detalles) {
		super();
		this.fecha = fecha;
		this.cliente = cliente;
		this.total = total;
		this.detalles = detalles;
	}

	public long getNrofactura() {
		return nrofactura;
	}

	public void setNrofactura(long nrofactura) {
		this.nrofactura = nrofactura;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<DetalleFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Factura [nrofactura=" + nrofactura + ", fecha=" + fecha + ", total=" + total + "]";
	}



	

	
	
	
}
