package dyp.deansoffice.repository.security;

import dyp.deansoffice.model.security.User;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {
    @Query("select * from dyp.user_details WHERE userName = ?0 ALLOW FILTERING")
    User findByUserName(String userName);
}