package dyp.deansoffice.validator;

import dyp.deansoffice.model.student.Student;
import dyp.deansoffice.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Slf4j
public class StudentValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9. ()-]{7,25}$");

    public static void validate(Student student) {
        log.info("Validating student '{}'...", student.getFirstname());
        validateString(student.getFirstname(), "firstname");
        validateString(student.getLastname(), "lastname");
        validateEmail(student.getEmail());
        validatePhoneNumber(student.getPhoneNumber());
        validateString(student.getAddress(), "address");
        validateDate(student.getDateOfBirth());
        validateString(student.getPlaceOfBirth(), "place of birth");
        validateString(student.getNationality(), "nationality");
        validateSex(student.getSex());
        validateString(student.getLevel(), "level");
        validateString(student.getFaculty(), "faculty");
        validateString(student.getGroup(), "group");
        validateString(student.getFormOfStudy(), "form of study");
        validateString(student.getHeadTeacher(), "head teacher");
        validateDate(student.getStartDate());
        validateDate(student.getEstimatedEndDate());
    }

    private static void validateString(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(String.format("Empty field not allowed for '%s'", field));
        }
    }

    private static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Invalid email");
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new ValidationException("Invalid phone number");
        }
    }

    private static void validateDate(LocalDate date) {
        if (date == null || date.isBefore(LocalDate.of(1915, 1, 1))) {
            throw new ValidationException("Invalid birth date");
        }
    }

    private static void validateSex(String sex) {
        if (sex == null || (!sex.equals("Male") && !sex.equals("Female"))) {
            throw new ValidationException("Invalid sex");
        }
    }
}

