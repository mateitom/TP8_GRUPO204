package ar.edu.unju.escminas.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="clientes")
public class Cliente implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CODIGO")
	private long cod;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="APELLIDO")
	private String apellido;
	
	@Column(name="DOMICILIO")
	private String domicilio;
	
	@Column(name="DNI")
	private int dni;
	
	@OneToMany (mappedBy = "cliente")
	private List<Factura> facturas;
	
	public Cliente() {
		
	}

	public Cliente(String nombre, String apellido, String domicilio, int dni, List<Factura> facturas) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.dni = dni;
		this.facturas = facturas;
	}

	public long getCod() {
		return cod;
	}

	public void setCod(long cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	@Override
	public String toString() {
		return "Cliente [cod=" + cod + ", nombre=" + nombre + ", apellido=" + apellido + ", domicilio=" + domicilio
				+ ", dni=" + dni + ", facturas=" + facturas + "]";
	}



}
