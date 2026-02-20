package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

// Importaciones Hibernate
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Clase HibernateSession
 *
 * - Singleton para manejar la SessionFactory de Hibernate
 * - Se asegura de que haya **una única SessionFactory** en toda la aplicación
 */
public class HibernateSession {

	// ===== SESION FACTORY GLOBAL =====
	private static final SessionFactory sessionFactory = buildSessionFactory();
	/*
	 * Se inicializa de forma estática al cargar la clase.
	 * Esto garantiza que solo exista una SessionFactory.
	 */

	// ===== MÉTODO PRIVADO PARA CREAR LA SESSIONFACTORY =====
	private static SessionFactory buildSessionFactory() {
		try {
			// Configuración de Hibernate
			Configuration configuration = new Configuration();

			// Cargar hibernate.cfg.xml
			configuration.configure(); 
			/*
			 * Si no se pasa parámetro, busca "hibernate.cfg.xml" en el classpath
			 */

			// Registrar clases anotadas (entidades)
			configuration.addAnnotatedClass(es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Person.class);
			configuration.addAnnotatedClass(es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Student.class);
			configuration.addAnnotatedClass(es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Teacher.class);

			configuration.addAnnotatedClass(es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Vehicle.class);
			configuration.addAnnotatedClass(es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Car.class);
			configuration.addAnnotatedClass(es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Plane.class);
			configuration.addAnnotatedClass(es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Motorcycle.class);
			/*
			 * Hibernate necesita conocer todas las clases con anotaciones @Entity
			 * para mapearlas correctamente a la base de datos
			 */

			// Construir el ServiceRegistry
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();
			/*
			 * ServiceRegistry aplica todas las propiedades del cfg
			 * y prepara Hibernate para construir la SessionFactory
			 */

			// Crear y retornar la SessionFactory
			return configuration.buildSessionFactory(serviceRegistry);		

		} catch (Throwable ex) {
			// Captura cualquier error al inicializar Hibernate
			System.err.println("Error creando SessionFactory: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	// ===== MÉTODO PÚBLICO PARA OBTENER LA SESSIONFACTORY =====
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
		/*
		 * Todas las clases de la aplicación usan este método
		 * para abrir sesiones Hibernate (sessionFactory.openSession())
		 */
	}
}
