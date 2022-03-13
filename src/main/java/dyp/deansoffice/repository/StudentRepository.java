package dyp.deansoffice.repository;

import dyp.deansoffice.model.student.StudentEntity;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface StudentRepository extends CassandraRepository<StudentEntity, UUID> {

}
