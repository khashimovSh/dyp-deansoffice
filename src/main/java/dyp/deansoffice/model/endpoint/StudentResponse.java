package dyp.deansoffice.model.endpoint;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class StudentResponse {
    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
