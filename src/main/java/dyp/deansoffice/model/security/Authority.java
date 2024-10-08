package dyp.deansoffice.model.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@UserDefinedType("authority")
public class Authority implements GrantedAuthority {

    private Role role;
    private String roleDescription;

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return role.name();
    }
}
