package dyp.deansoffice.mapper;

import dyp.deansoffice.entity.StudentEntity;
import dyp.deansoffice.model.student.Student;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Builder;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(Student dto, @MappingTarget StudentEntity entity);

    Student mapEntityToDto(StudentEntity entity);
    StudentEntity mapDtoToEntity(Student dto);

    List<Student> mapEntityListToDtoList(List<StudentEntity> entityList);
    List<StudentEntity> mapDtoListToEntityList(List<Student> dtoList);
}