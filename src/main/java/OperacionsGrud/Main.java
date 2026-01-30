package OperacionsGrud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Car;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Motorcycle;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Person;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Plane;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Student;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Teacher;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Vehicle;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main.HibernateSession;

public class Main {

	private static List<Person> people = new ArrayList<>();
	private static List<Vehicle> vehicles = new ArrayList<>();
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int opcio;

		do {
			System.out.println("\n===== MENÚ PRINCIPAL =====");
			System.out.println("1) Fase 1: Crear dades de prova");
			System.out.println("2) Fase 2: Treure vehicles de persones");
			System.out.println("3) Fase 3: Actualitzar un vehicle");
			System.out.println("0) Sortir");
			System.out.print("Escull una opció: ");

			opcio = sc.nextInt();
			sc.nextLine();

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

		sc.close();
		HibernateSession.getSessionFactory().close();
	}
	
	private static void fase1() {
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			// Crear persons (Students y Teachers)
			Student s1 = new Student("Anna Lopez", null, "111111111", "STU001", null);
			Student s2 = new Student("Jordi Martinez", null, "222222222", "STU002", null);
			Student s3 = new Student("Clara Sanchez", null, "333333333", "STU003", null);
			Teacher t4 = new Teacher("Joan Perez", null, "444444444", "TEA001", null);
			Teacher t5 = new Teacher("Maria Gomez", null, "555555555", "TEA002", null);
			Teacher t6 = new Teacher("Pere Ruiz", null, "666666666", "TEA003", null);

			session.save(s1);
			session.save(s2);
			session.save(s3);
			session.save(t4);
			session.save(t5);
			session.save(t6);

			
			Car v1 = new Car("Toyota", "Corolla", 2020, "LP1", 18000.0, 5, 5.0);
			v1.setOwner(s1);
			s1.addVehicle(v1);
			session.save(v1);

			Car v2 = new Car("Ford", "Focus", 2019, "LP2", 15000.0, 3, 4.0);
			v2.setOwner(t5);
			t5.addVehicle(v2);
			session.save(v2);

			Plane v3 = new Plane("Cessna", "CessnaModel", 2015, "LP3", 120000.0, 11111.0, 2);
			v3.setOwner(t4);
			t4.addVehicle(v3);
			session.save(v3);

			Plane v4 = new Plane("Boeing", "BoeingModel", 2010, "LP4", 900000.0, 22222.0, 4);
			v4.setOwner(s3);
			s3.addVehicle(v4);
			session.save(v4);

			Motorcycle v5 = new Motorcycle("Yamaha", "YamahaModel", 2021, "LP5", 9000.0, 125, false);
			v5.setOwner(s2);
			s2.addVehicle(v5);
			session.save(v5);

			Motorcycle v6 = new Motorcycle("Harley-Davidson", "HarleyModel", 2018, "LP6", 20000.0, 1200, true);
			v6.setOwner(t6);
			t6.addVehicle(v6);
			session.save(v6);

			tx.commit();
			System.out.println("Fase1: dades inserides correctament.");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
	}
	
	private static void fase2() {

		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Vehicle v = session.get(Vehicle.class, 1);
			if (v != null) {
				Person owner = v.getOwner();
				if (owner != null) {
					owner.removeVehicle(v);
				}
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

	private static void fase3() {

		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Vehicle v = session.get(Vehicle.class, 1);
			if (v != null) {
				v.setBrand("Seat");
				v.setYear(2022);
				// Campo price añadido en la entidad Vehicle
				try {
					java.lang.reflect.Method m = v.getClass().getMethod("setPrice", Double.class);
					m.invoke(v, 19999.0);
				} catch (NoSuchMethodException nsme) {
					// Si no existeix el setter (no hauria de passar), ignorar
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
