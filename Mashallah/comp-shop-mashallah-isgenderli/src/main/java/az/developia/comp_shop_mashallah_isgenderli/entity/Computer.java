package az.developia.comp_shop_mashallah_isgenderli.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "computer")

public class Computer {

    public UserEntity getSeller() {
		return seller;
	}

	public void setSeller(UserEntity seller) {
		this.seller = seller;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 30, message = "Marka adı 1 ilə 30 simvol arasında olmalıdır!")
    private String brand;

    private String model;

    @NotNull
    @Min(value = 0, message = "Komputerin qiyməti sıfırdan kiçik ola bilməz!")
    @Max(value = 100000, message = "Komputerin qiyməti 100000-dən çox ola bilməz!")
    private Double price;

    private String description;
    private boolean isNew;

    private String photo;
    private Long ram;
    private String cpu;
    private String storage;
    private String storageType;
    private String os;
    private String videoCard;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserEntity seller;

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

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
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

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getVideoCard() {
		return videoCard;
	}

	public void setVideoCard(String videoCard) {
		this.videoCard = videoCard;
	}


    
}
