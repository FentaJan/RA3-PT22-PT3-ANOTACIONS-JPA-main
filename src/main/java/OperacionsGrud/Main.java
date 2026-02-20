package OperacionsGrud;

// Importaciones básicas de Java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Importaciones de Db4o
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

// Importaciones de las clases de dominio
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Car;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Motorcycle;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Person;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Plane;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Student;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Teacher;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Vehicle;

// Clase de utilidad para obtener la conexión a Db4o
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main.Db4oSession;

public class Main {

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
			System.out.println("4) Listar totes les persones i vehicles");
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
				case 4:
					listarDatos();
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
	}

	/**
	 * FASE 1:
	 * - Crea personas (Students y Teachers)
	 * - Crea vehículos (Car, Plane, Motorcycle)
	 * - Asocia cada vehículo con su propietario
	 * - Guarda todo en la base de datos
	 */
	private static void fase1() {

		ObjectContainer db = Db4oSession.getObjectContainer();

		try {
			// ===== CREACIÓN DE PERSONAS =====
			Student s1 = new Student("Anna Lopez", null, "111111111", "STU001", null);
			Student s2 = new Student("Jordi Martinez", null, "222222222", "STU002", null);
			Student s3 = new Student("Clara Sanchez", null, "333333333", "STU003", null);

			Teacher t4 = new Teacher("Joan Perez", null, "444444444", "TEA001", null);
			Teacher t5 = new Teacher("Maria Gomez", null, "555555555", "TEA002", null);
			Teacher t6 = new Teacher("Pere Ruiz", null, "666666666", "TEA003", null);

			// Persistimos las personas
			db.store(s1);
			db.store(s2);
			db.store(s3);
			db.store(t4);
			db.store(t5);
			db.store(t6);

			// ===== CREACIÓN DE VEHÍCULOS =====

			// Coche asociado al estudiante s1
			Car v1 = new Car("Toyota", "Corolla", 2020, "LP1", 18000.0, 5, 5.0);
			v1.setOwner(s1);
			s1.addVehicle(v1);
			db.store(v1);

			// Coche asociado a la profesora t5
			Car v2 = new Car("Ford", "Focus", 2019, "LP2", 15000.0, 3, 4.0);
			v2.setOwner(t5);
			t5.addVehicle(v2);
			db.store(v2);

			// Avión asociado al profesor t4
			Plane v3 = new Plane("Cessna", "CessnaModel", 2015, "LP3", 120000.0, 11111.0, 2);
			v3.setOwner(t4);
			t4.addVehicle(v3);
			db.store(v3);

			// Avión asociado al estudiante s3
			Plane v4 = new Plane("Boeing", "BoeingModel", 2010, "LP4", 900000.0, 22222.0, 4);
			v4.setOwner(s3);
			s3.addVehicle(v4);
			db.store(v4);

			// Moto asociada al estudiante s2
			Motorcycle v5 = new Motorcycle("Yamaha", "YamahaModel", 2021, "LP5", 9000.0, 125, false);
			v5.setOwner(s2);
			s2.addVehicle(v5);
			db.store(v5);

			// Moto asociada al profesor t6
			Motorcycle v6 = new Motorcycle("Harley-Davidson", "HarleyModel", 2018, "LP6", 20000.0, 1200, true);
			v6.setOwner(t6);
			t6.addVehicle(v6);
			db.store(v6);

			// Confirmamos la transacción
			db.commit();
			System.out.println("Fase1: dades inserides correctament.");
			listarDatos();

		} catch (Exception e) {
			e.printStackTrace();
			db.rollback();
		} finally {
			Db4oSession.closeObjectContainer(db);
		}
	}

	/**
	 * FASE 2:
	 * - Obtiene el primer vehículo
	 * - Lo desvincula de su propietario
	 * - Deja el propietario a NULL en la base de datos
	 */
	private static void fase2() {

		ObjectContainer db = Db4oSession.getObjectContainer();

		try {
			// Obtener todos los vehículos
			ObjectSet<Vehicle> vehiculos = db.queryByExample(new Vehicle());

			if (vehiculos.hasNext()) {
				Vehicle v = vehiculos.next();

				Person owner = v.getOwner();

				// Quitamos el vehículo de la lista del propietario
				if (owner != null) {
					owner.removeVehicle(v);
					db.store(owner); // Actualizar el propietario
				}

				// Quitamos el propietario del vehículo
				v.setOwner(null);
				db.store(v); // Actualizar el vehículo

				db.commit();

				System.out.println("Fase2: vehicle id=1 desvinculat (person_id = NULL)");
				listarDatos();
			} else {
				System.out.println("Fase2: no s'ha trobat cap vehicle");
				db.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
			db.rollback();
		} finally {
			Db4oSession.closeObjectContainer(db);
		}
	}

	/**
	 * FASE 3:
	 * - Obtiene el primer vehículo
	 * - Actualiza marca y año
	 * - Usa reflexión para actualizar el precio
	 *   (por si el método setPrice no existe en todas las subclases)
	 */
	private static void fase3() {

		ObjectContainer db = Db4oSession.getObjectContainer();

		try {
			// Obtener todos los vehículos
			ObjectSet<Vehicle> vehiculos = db.queryByExample(new Vehicle());

			if (vehiculos.hasNext()) {
				Vehicle v = vehiculos.next();

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

				db.store(v);
				db.commit();

				System.out.println("Fase3: vehicle id=1 actualitzat a Seat, 2022, 19999");
				listarDatos();
			} else {
				System.out.println("Fase3: no s'ha trobat cap vehicle");
				db.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
			db.rollback();
		} finally {
			Db4oSession.closeObjectContainer(db);
		}
	}

	/**
	 * LISTAR TODOS LOS DATOS (FASE 4):
	 * - Muestra todas las personas con sus vehículos
	 * - Muestra un resumen del total de vehículos
	 * - Formato estructurado para depuración y validación
	 */
	private static void listarDatos() {

		ObjectContainer db = Db4oSession.getObjectContainer();

		try {
			System.out.println("\n===== DADES DE LA BASE DE DADES (db4o) =====");
			System.out.println();

			// Obtener todas las personas
			ObjectSet<Person> personas = db.queryByExample(new Person());

			if (!personas.hasNext()) {
				System.out.println("No hi ha dades a la base de dades.");
				return;
			}

			// Contar personas
			int countPersonas = 0;
			List<Person> personasList = new ArrayList<>();
			for (Person p : personas) {
				personasList.add(p);
				countPersonas++;
			}

			// Mostrar sección de PERSONES
			System.out.println("PERSONES (" + countPersonas + ")");
			System.out.println();

			int totalVehiculos = 0;

			for (Person persona : personasList) {
				// Mostrar detalles de la persona
				if (persona instanceof Student) {
					Student s = (Student) persona;
					System.out.println("Student: name \"" + s.getName() + "\", phone \"" + s.getPhone()
							+ "\", studentCode \"" + s.getEnrollmentNumber() + "\", vehicles:");
				} else if (persona instanceof Teacher) {
					Teacher t = (Teacher) persona;
					System.out.println("Teacher: name \"" + t.getName() + "\", phone \"" + t.getPhone()
							+ "\", teacherCode \"" + t.getEmployeeId() + "\", vehicles:");
				}

				// Mostrar vehículos de la persona
				List<Vehicle> vehiclesPersona = persona.getVehicles();
				if (vehiclesPersona != null && !vehiclesPersona.isEmpty()) {
					for (Vehicle v : vehiclesPersona) {
						System.out.println("  Vehicle: " + v.toStringShort());
						totalVehiculos++;
					}
				} else {
					System.out.println("  (sin vehículos)");
				}

				System.out.println();
			}

			// Mostrar sección de VEHICLES TOTALS
			System.out.println("VEHICLES TOTALS (" + totalVehiculos + ")");
			System.out.println();

			// Obtener todos los vehículos
			ObjectSet<Vehicle> vehiculos = db.queryByExample(new Vehicle());
			List<Vehicle> vehiculosList = new ArrayList<>();
			for (Vehicle v : vehiculos) {
				vehiculosList.add(v);
			}

			// Mostrar cada vehículo en formato corto
			for (Vehicle v : vehiculosList) {
				System.out.println(v.toStringShort());
			}

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Db4oSession.closeObjectContainer(db);
		}
	}
