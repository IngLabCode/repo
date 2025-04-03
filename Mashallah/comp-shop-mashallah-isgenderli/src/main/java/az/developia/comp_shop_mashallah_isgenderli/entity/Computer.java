package az.developia.comp_shop_mashallah_isgenderli.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Computer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String brand;
	private String model;
	private Double price;
	private String description;
	private String photo;
	private Long ram;
	private String os;
	private Long rom;

	// Relation with User
	@ManyToOne
	@JoinColumn(name = "seller_id", referencedColumnName = "id")
	private User seller;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Long getRam() {
		return ram;
	}

	public void setRam(Long ram) {
		this.ram = ram;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getRom() {
		return rom;
	}

	public void setRom(Long rom) {
		this.rom = rom;
	}

	public User getSeller() {
		return seller;
	}

}