package dyp.deansoffice.repository;

import dyp.deansoffice.entity.StudentEntity;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

public interface StudentRepository extends CassandraRepository<StudentEntity, UUID> {

    @Query("DELETE FROM student WHERE ID = ?0")
    void deleteStudentById(UUID id);

    @Query("SELECT * FROM student WHERE student_id = ?0 ALLOW FILTERING")
    StudentEntity findByStudentId(String studentId);
}
