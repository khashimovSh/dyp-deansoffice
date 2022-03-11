package dyp.security.model;

import lombok.Data;

@Data
public class UserInfo {

    private String firstName;
    private String lastName;
    private String userName;

    private Object roles;


}
