package az.developia.comp_shop_mashallah_isgenderli.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "İstifadəçi adı boş ola bilməz!")
    @Size(min = 3, max = 20, message = "İstifadəçi adı 3 ilə 20 simvol arasında olmalıdır!")
    private String username;

    @NotNull(message = "Şifrə boş ola bilməz!")
    @Size(min = 6, message = "Şifrə ən az 6 simvol olmalıdır!")
    private String password;

    @Email(message = "Email düzgün formatda olmalıdır!")
    private String email;

    @Size(min = 10, max = 15, message = "Telefon nömrəsi 10 ilə 15 simvol arasında olmalıdır!")
    private String phone;

    @Size(min = 3, max = 50, message = "Ad və soyad 3 ilə 50 simvol arasında olmalıdır!")
    private String fullName;
    
    private int enabled;

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    
}
