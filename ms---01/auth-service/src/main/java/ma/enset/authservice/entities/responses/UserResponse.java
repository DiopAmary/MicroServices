package ma.enset.authservice.entities.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.authservice.entities.RoleEntity;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {
    private Long Id;
    private String username;
    private Collection<RoleEntity> roles;
}
