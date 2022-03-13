package dyp.deansoffice.repository;

import dyp.deansoffice.model.security.User;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {
    @Query("select * from user_details WHERE userName = ?0 ALLOW FILTERING")
    User findByUserName(String userName);
}
//INSERT INTO user_details (id, password)
// VALUES (ab84024e-a096-11ec-b909-0242ac120002, '$2a$10$OZt86WJSeDxkr.7xzftsMeLTXp/NDzP.khJVlu.S4si6ZzZGItZNO');