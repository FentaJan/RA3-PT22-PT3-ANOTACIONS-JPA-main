package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

// Importaciones de JPA
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entidad Car
 *
 * - Representa un coche en el modelo de dominio.
 * - Hereda de Vehicle.
 * - Usa herencia JPA con estrategia JOINED.
 * - Tiene su propia tabla "car" que se une con "vehicle"
 *   mediante la clave primaria compartida.
 */
@Entity                         // Marca la clase como entidad JPA
@Table(name = "car")            // Tabla específica para esta subclase
@PrimaryKeyJoinColumn(name = "vehicle_id")
/*
 * @PrimaryKeyJoinColumn indica que:
 * - La clave primaria de la tabla "car" es también una clave foránea
 * - Apunta al id de la tabla "vehicle"
 * - Esto es típico de la estrategia JOINED
 */
@DiscriminatorValue("CAR")
/*
 * Valor que se guarda en la columna discriminadora
 * (definida en la clase Vehicle) para identificar
 * que este registro es de tipo Car
 */
public class Car extends Vehicle {

	private static final long serialVersionUID = 1L;
	/*
	 * Necesario porque Vehicle (normalmente) implementa Serializable.
	 * Ayuda a la compatibilidad en serialización.
	 */

	// ===== ATRIBUTOS PROPIOS DE CAR =====

	@Column(name = "num_doors")
	/*
	 * Número de puertas del coche.
	 * Se almacena en la columna num_doors de la tabla "car".
	 */
	private Integer numDoors;

	@Column(name = "trunk_capacity")
	/*
	 * Capacidad del maletero (por ejemplo, en litros).
	 * Se almacena en la tabla "car".
	 */
	private Double trunkCapacity;

	// ===== CONSTRUCTORES =====

	/**
	 * Constructor vacío obligatorio para JPA.
	 * Hibernate lo utiliza para instanciar el objeto mediante reflexión.
	 */
	public Car() {
	}

	/**
	 * Constructor sin precio.
	 * Llama al constructor de Vehicle sin el atributo price.
	 */
	public Car(String brand,
			   String model,
			   Integer year,
			   String licensePlate,
			   Integer numDoors,
			   Double trunkCapacity) {

		super(brand, model, year, licensePlate); // Inicializa atributos comunes
		this.numDoors = numDoors;
		this.trunkCapacity = trunkCapacity;
	}

	/**
	 * Constructor con precio.
	 * Inicializa tanto los atributos de Vehicle como los específicos de Car.
	 */
	public Car(String brand,
			   String model,
			   Integer year,
			   String licensePlate,
			   Double price,
			   Integer numDoors,
			   Double trunkCapacity) {

		super(brand, model, year, licensePlate, price);
		this.numDoors = numDoors;
		this.trunkCapacity = trunkCapacity;
	}

	// ===== GETTERS Y SETTERS =====

	public Integer getNumDoors() {
		return numDoors;
	}

	public void setNumDoors(Integer numDoors) {
		this.numDoors = numDoors;
	}

	public Double getTrunkCapacity() {
		return trunkCapacity;
	}

	public void setTrunkCapacity(Double trunkCapacity) {
		this.trunkCapacity = trunkCapacity;
	}

	// ===== MÉTODOS =====

	/**
	 * Representación en texto del objeto.
	 * Útil para depuración y logs.
	 * Usa getters heredados de Vehicle.
	 */
	@Override
	public String toString() {
		return "Car [vehicleId=" + this.getVehicleId()
				+ ", brand=" + this.getBrand()
				+ ", model=" + this.getModel()
				+ ", year=" + this.getYear()
				+ ", licensePlate=" + this.getLicensePlate()
				+ ", numDoors=" + numDoors
				+ ", trunkCapacity=" + trunkCapacity
				+ "]";
	}
}
