package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

/**
 * Clase Db4oSession
 *
 * - Singleton para manejar la conexión a la base de datos Db4o
 * - Se asegura de que haya **una única conexión** en toda la aplicación
 * - Proporciona métodos para abrir y cerrar contenedores de objetos
 */
public class Db4oSession {

	// ===== RUTA DE LA BASE DE DATOS =====
	private static final String DB_FILE = "objetosdb.db4o";

	// ===== CONSTRUCTOR PRIVADO =====
	private Db4oSession() {
		// No permitir instancias
	}

	// ===== MÉTODO PARA OBTENER UN CONTENEDOR DE OBJETOS =====
	/**
	 * Abre una nueva conexión a la base de datos Db4o.
	 * Cada llamada crea un nuevo ObjectContainer.
	 * 
	 * @return ObjectContainer para interactuar con la base de datos
	 */
	public static ObjectContainer getObjectContainer() {
		return Db4o.openFile(DB_FILE);
	}

	// ===== MÉTODO PARA CERRAR LA BASE DE DATOS =====
	/**
	 * Cierra la conexión con la base de datos.
	 * Debe llamarse al terminar de usar el ObjectContainer.
	 * 
	 * @param container el ObjectContainer a cerrar
	 */
	public static void closeObjectContainer(ObjectContainer container) {
		if (container != null) {
			container.close();
		}
	}

	// ===== MÉTODO PARA OBTENER LA RUTA DEL ARCHIVO =====
	/**
	 * Retorna la ruta del archivo de la base de datos.
	 * 
	 * @return la ruta del archivo
	 */
	public static String getDbFilePath() {
		return DB_FILE;
	}
}
