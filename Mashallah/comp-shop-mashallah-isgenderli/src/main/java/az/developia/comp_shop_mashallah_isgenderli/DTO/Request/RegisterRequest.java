package az.developia.comp_shop_mashallah_isgenderli.DTO.Request;

import az.developia.comp_shop_mashallah_isgenderli.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String name;
  private String email;
  private String phone;
  private String password;
  private Role role;
}
