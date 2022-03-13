package dyp.deansoffice.model.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Generated
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table("user_details")
public class User implements UserDetails {
    @PrimaryKey
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID id;
    private String userName;
    private String password;
    private Date createdAt;
    private Date updateAt;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean enabled=true;
    private List<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return this.enabled;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return this.enabled;
    }
}
