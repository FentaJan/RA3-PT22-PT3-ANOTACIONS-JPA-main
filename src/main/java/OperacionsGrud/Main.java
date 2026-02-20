package OperacionsGrud;

// Importaciones básicas de Java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Importaciones de Hibernate
import org.hibernate.Session;
import org.hibernate.Transaction;

// Importaciones de las entidades JPA
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Car;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Motorcycle;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Person;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Plane;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Student;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Teacher;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Vehicle;

// Clase de utilidad para obtener la SessionFactory de Hibernate
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main.HibernateSession;

public class Main {

	// Listas que podrían usarse para almacenar personas y vehículos en memoria
	// (en este código realmente no se utilizan)
	private static List<Person> people = new ArrayList<>();
	private static List<Vehicle> vehicles = new ArrayList<>();

	public static void main(String[] args) {

		// Scanner para leer opciones del usuario por consola
		Scanner sc = new Scanner(System.in);
		int opcio;

		// Bucle principal del menú
		do {
			System.out.println("\n===== MENÚ PRINCIPAL =====");
			System.out.println("1) Fase 1: Crear dades de prova");
			System.out.println("2) Fase 2: Treure vehicles de persones");
			System.out.println("3) Fase 3: Actualitzar un vehicle");
			System.out.println("0) Sortir");
			System.out.print("Escull una opció: ");

			opcio = sc.nextInt();
			sc.nextLine(); // Limpia el buffer

			// Selección de la fase según la opción
			switch (opcio) {
				case 1:
					fase1();
					break;
				case 2:
					fase2();
					break;
				case 3:
					fase3();
					break;
				case 0:
					System.out.println("Fins aviat!");
					break;
				default:
					System.out.println("Opció incorrecta.");
			}

		} while (opcio != 0);

		// Cierre de recursos
		sc.close();
		HibernateSession.getSessionFactory().close();
	}

	/**
	 * FASE 1:
	 * - Crea personas (Students y Teachers)
	 * - Crea vehículos (Car, Plane, Motorcycle)
	 * - Asocia cada vehículo con su propietario
	 * - Guarda todo en la base de datos
	 */
	private static void fase1() {

		// Abrimos sesión Hibernate
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			// ===== CREACIÓN DE PERSONAS =====
			Student s1 = new Student("Anna Lopez", null, "111111111", "STU001", null);
			Student s2 = new Student("Jordi Martinez", null, "222222222", "STU002", null);
			Student s3 = new Student("Clara Sanchez", null, "333333333", "STU003", null);

			Teacher t4 = new Teacher("Joan Perez", null, "444444444", "TEA001", null);
			Teacher t5 = new Teacher("Maria Gomez", null, "555555555", "TEA002", null);
			Teacher t6 = new Teacher("Pere Ruiz", null, "666666666", "TEA003", null);

			// Persistimos las personas
			session.save(s1);
			session.save(s2);
			session.save(s3);
			session.save(t4);
			session.save(t5);
			session.save(t6);

			// ===== CREACIÓN DE VEHÍCULOS =====

			// Coche asociado al estudiante s1
			Car v1 = new Car("Toyota", "Corolla", 2020, "LP1", 18000.0, 5, 5.0);
			v1.setOwner(s1);      // lado propietario (ManyToOne)
			s1.addVehicle(v1);   // lado inverso (OneToMany)
			session.save(v1);

			// Coche asociado a la profesora t5
			Car v2 = new Car("Ford", "Focus", 2019, "LP2", 15000.0, 3, 4.0);
			v2.setOwner(t5);
			t5.addVehicle(v2);
			session.save(v2);

			// Avión asociado al profesor t4
			Plane v3 = new Plane("Cessna", "CessnaModel", 2015, "LP3", 120000.0, 11111.0, 2);
			v3.setOwner(t4);
			t4.addVehicle(v3);
			session.save(v3);

			// Avión asociado al estudiante s3
			Plane v4 = new Plane("Boeing", "BoeingModel", 2010, "LP4", 900000.0, 22222.0, 4);
			v4.setOwner(s3);
			s3.addVehicle(v4);
			session.save(v4);

			// Moto asociada al estudiante s2
			Motorcycle v5 = new Motorcycle("Yamaha", "YamahaModel", 2021, "LP5", 9000.0, 125, false);
			v5.setOwner(s2);
			s2.addVehicle(v5);
			session.save(v5);

			// Moto asociada al profesor t6
			Motorcycle v6 = new Motorcycle("Harley-Davidson", "HarleyModel", 2018, "LP6", 20000.0, 1200, true);
			v6.setOwner(t6);
			t6.addVehicle(v6);
			session.save(v6);

			// Confirmamos la transacción
			tx.commit();
			System.out.println("Fase1: dades inserides correctament.");

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); // Deshacer cambios si hay error
		} finally {
			session.close();
		}
	}

	/**
	 * FASE 2:
	 * - Obtiene el vehículo con id = 1
	 * - Lo desvincula de su propietario
	 * - Deja el person_id a NULL en la base de datos
	 */
	private static void fase2() {

		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			Vehicle v = session.get(Vehicle.class, 1);

			if (v != null) {
				Person owner = v.getOwner();

				// Quitamos el vehículo de la lista del propietario
				if (owner != null) {
					owner.removeVehicle(v);
				}

				// Quitamos el propietario del vehículo
				v.setOwner(null);

				session.update(v);
				tx.commit();

				System.out.println("Fase2: vehicle id=1 desvinculat (person_id = NULL)");
			} else {
				System.out.println("Fase2: no s'ha trobat el vehicle id=1");
				tx.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * FASE 3:
	 * - Obtiene el vehículo con id = 1
	 * - Actualiza marca y año
	 * - Usa reflexión para actualizar el precio
	 *   (por si el método setPrice no existe en todas las subclases)
	 */
	private static void fase3() {

		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			Vehicle v = session.get(Vehicle.class, 1);

			if (v != null) {
				v.setBrand("Seat");
				v.setYear(2022);

				// Uso de reflexión para llamar a setPrice si existe
				try {
					java.lang.reflect.Method m =
							v.getClass().getMethod("setPrice", Double.class);
					m.invoke(v, 19999.0);
				} catch (NoSuchMethodException nsme) {
					// Si la clase concreta no tiene setPrice, se ignora
				}

				session.update(v);
				tx.commit();

				System.out.println("Fase3: vehicle id=1 actualitzat a Seat, 2022, 19999");
			} else {
				System.out.println("Fase3: no s'ha trobat el vehicle id=1");
				tx.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
	}
}
