package ar.edu.unju.escminas.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfSingleton {
	
	private static EmfSingleton miInstancia = new EmfSingleton();
	static private final String PERSISTENCE_UNIT_NAME = "TestPersistence";
	private EntityManagerFactory emf = null;

	public static EmfSingleton getInstance() {
		return miInstancia;
	}

	private EmfSingleton() {
		
	}

	public EntityManagerFactory getEmf() {
		if (this.emf == null)
			this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		return this.emf;
	}

}



