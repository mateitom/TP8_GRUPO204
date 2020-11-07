package ar.edu.unju.escminas.principal;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ar.edu.unju.escminas.dao.IClienteDao;
import ar.edu.unju.escminas.dao.IDetalleFacturaDao;
import ar.edu.unju.escminas.dao.IFacturaDao;
import ar.edu.unju.escminas.dao.imp.ClienteDaoImp;
import ar.edu.unju.escminas.dao.imp.DetalleFacturaDaoImp;
import ar.edu.unju.escminas.dao.imp.FacturaDaoImp;
import ar.edu.unju.escminas.dominio.Cliente;
import ar.edu.unju.escminas.dominio.DetalleFactura;
import ar.edu.unju.escminas.dominio.Factura;

public class Principal {
	
	public static EntityManager manager;
	public static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//emf = Persistence.createEntityManagerFactory("TestPersistence");
		//manager= emf.createEntityManager();
		
		//Cliente cliente= new Cliente("Mateo","Mara�on","Micasa",43791);
		//manager.getTransaction().begin();
		//manager.persist(cliente);
		//manager.getTransaction().commit();
		Scanner sc = new Scanner(System.in);
		int op=-1;
		menu(op, sc);
	}
	public static void menu(int op, Scanner sc) {
		boolean cod;
		while (op!=7) {
			System.out.println("----------MENU---------");
			System.out.println("1- ALTA CLIENTE");
			System.out.println("2- CREAR FACTURA");
			System.out.println("3- BUSCAR FACTURA");
			System.out.println("4- LISTADO DE FACTURAS");
			System.out.println("5- LISTADO DE CLIENTES");
			System.out.println("6- MOSTRAR FACTURAS MAYORES A $1000");
			System.out.println("7- SALIR");
			System.out.println("Ingrese Opcion: ");
			do {
				cod = false;
				try {
					op=sc.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("NO INGRESE LETRAS");
					System.out.println("INGRESE OPCION NUEVAMENTE");
					cod = true;
					sc.next();
				}
			} while(cod == true);	
			switch (op) {
			case 1: darAltaCliente();
			break;
			case 2: crearFactura();
			break;
			case 3: buscarFactura();
			break;
			case 4: listarFactura();
			break;
			case 5: listarCliente();
			break;
			case 6: listarFacturasMayoresA1000();
			break;
			case 7: System.out.println("FIN DEL PROGRAMA");
			break;
			default: System.out.println("OPCION INCORRECTA INTENTE NUEVAMENTE");
			break;
			}
		}
	}
	public static void darAltaCliente() {
		boolean cod;
		Cliente unCliente = new Cliente();
		Scanner sc = new Scanner(System.in);
		IClienteDao clienteDao = new ClienteDaoImp();
		System.out.println("INGRESE NOMBRE: ");
		unCliente.setNombre(sc.next());
		System.out.println("INGRESE APELLIDO: ");
		unCliente.setApellido(sc.next());
		System.out.println("INGRESE DNI: ");
		do {
			cod = false;
			try {
				unCliente.setDni(sc.nextInt());
			}catch(InputMismatchException e) {
				System.out.println("INGRESE SU DNI EN NUMEROS");
				System.out.println("INGRESE SU DNI NUEVAMENTE: ");
				cod = true;
				sc.next();
			}
		} while(cod == true);	
		System.out.println("INGRESE DOMICILIO");
		unCliente.setDomicilio(sc.next());
		clienteDao.guardarCliente(unCliente);
		System.out.println("CLIENTE GUARDADO");
	}
	public static void crearFactura() {
		Factura unaFactura = new Factura();
		List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
		boolean band = false;
		Scanner sc = new Scanner(System.in);
		IClienteDao clienteDao = new ClienteDaoImp();
		IFacturaDao facturaDao = new FacturaDaoImp();
		//facturaDao.guardarFactura(unaFactura);
		unaFactura.setFecha(LocalDate.now());
		unaFactura.setTotal(0);
		long codigo=0;
		boolean cod, cod1, cod2, cod3;
		System.out.println("INGRESE CODIGO DEL CLIENTE: ");
		do {
			
			cod = false;
			try {
				codigo= sc.nextLong();
			}catch(InputMismatchException e) {
				System.out.println("FORMATO DE CODIGO ERRONEO (SOLO INGRESE NUMEROS)");
				System.out.println("INGRESE CODIGO DEL CLIENTE NUEVAMENTE: ");
				cod = true;
				sc.next();
			}
		} while(cod == true);
		
		if (clienteDao.buscarCliente(codigo)==null) {
			System.out.println("Cliente no encontrado, no se almacenara la factura");
		}
		else {
			unaFactura.setCliente(clienteDao.buscarCliente(codigo));
			while (band==false) {
				int op=0;
				DetalleFactura unDetalle= new DetalleFactura();
				System.out.println("INGRESE NOMBRE DEL PRODUCTO");
				unDetalle.setDescripcion(sc.next());
				System.out.println("INGRESE PRECIO UNITARIO");
				do {
					cod1 = false;
					try {
						unDetalle.setPrecioUnitario(sc.nextDouble());
					}catch(InputMismatchException e) {
						System.out.println("FORMATO DE PRECIO INCORRECTO, NO INCLUYA LETRAS Y NO USE PUNTO");
						System.out.println("INGRESE PRECIO UNITARIO NUEVAMENTE");
						cod1 = true;
						sc.next();
					}
				} while(cod1 == true);	
				System.out.println("INGRESE CANTIDAD");
				do {
					cod2 = false;
					try {
						unDetalle.setCantidad(sc.nextInt());
					}catch(InputMismatchException e) {
						System.out.println("NO INGRESE LETRAS");
						System.out.println("INGRESE CANTIDAD NUEVAMENTE");
						cod2 = true;
						sc.next();
					}
				} while(cod2 == true);	
				unDetalle.setSubtotal(unDetalle.getPrecioUnitario()*unDetalle.getCantidad());
				System.out.println("SUB TOTAL: "+ unDetalle.getSubtotal());
				unaFactura.setTotal(unaFactura.getTotal()+unDetalle.getSubtotal());
				unDetalle.setFacturas(unaFactura);
				detalles.add(unDetalle);
			
				while (op!=2&&op!=1) {
				System.out.println("DESEA AÑADIR OTRO PRODUCTO? 1-SI / 2-NO");
				do {
					cod3 = false;
					try {
						op = sc.nextInt();
					}catch(InputMismatchException e) {
						System.out.println("SUS OPCIONES SON INGRESAR EL NUMERO 1 O 2 SOLAMENTE");
						System.out.println("DESEA AÑADIR OTRO PRODUCTO? 1-SI / 2-NO");
						cod3 = true;
						sc.next();
					}
				} while(cod3 == true);	
				
				if (op==1) {
					band=false;
				}
				else {
					if (op==2) {
						band=true;
					}
					else {
						System.out.println("Opcion Incorrecta intente nuevamente");
					}
				}
				}
			}
			unaFactura.setDetalles(detalles);
			System.out.println("TOTAL: "+unaFactura.getTotal());
			facturaDao.guardarFactura(unaFactura);
		}
	}
	public static void buscarFactura() {
		boolean cod;
		long num=0;
		IFacturaDao facturaDao = new FacturaDaoImp();
		Factura unaFactura = new Factura();
		Scanner sc = new Scanner(System.in); 
		System.out.println("INGRESE FACTURA");
		do {
			cod = false;
			try {
				num = sc.nextLong();
			}catch(InputMismatchException e) {
				System.out.println("NO INGRESE LETRAS");
				System.out.println("INGRESE FACTURA NUEVAMENTE");
				cod = true;
				sc.next();
			}
		} while(cod == true);	
		
		if (facturaDao.buscarFactura(num)==null) {
			System.out.println("FACTURA NO ENCONTRADA");
		}
		else {
			unaFactura=facturaDao.buscarFactura(num);
			System.out.println(unaFactura.toString());
		}
	}
	public static void listarFactura() {
		IFacturaDao facturaDao = new FacturaDaoImp();
		if (facturaDao.mostrarFacturas().isEmpty()) {
			System.out.println("NO SE ENCONTRARON FACTURAS");
		}
		else {
		facturaDao.mostrarFacturas().stream().forEach(System.out::println);
		}
	}
	public static void listarCliente() {
		IClienteDao clienteDao = new ClienteDaoImp();
		if (clienteDao.mostrarClientes().isEmpty()) {
			System.out.println("NO SE ENCONTRARON CLIENTES");
		}
		else {
		System.out.println(clienteDao.mostrarClientes());
		}
	}
	public static void listarFacturasMayoresA1000() {
		IFacturaDao facturaDao = new FacturaDaoImp();
		facturaDao.buscarFacturasporTotal();
	}
	
}
