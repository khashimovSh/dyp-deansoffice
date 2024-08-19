package dyp.deansoffice.repository.security;

import dyp.deansoffice.entity.UserDetailsEntity;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<UserDetailsEntity, UUID> {
    @Query("SELECT * FROM user_details WHERE username = ?0 ALLOW FILTERING")
    UserDetailsEntity findByUsername(String userName);

    @Query("DELETE FROM user_details WHERE ID = ?0")
    void deleteByUserId(UUID id);
}