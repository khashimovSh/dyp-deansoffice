package dyp.deansoffice.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class StudentIdGenerator {
    private static final Set<Integer> EXISTING_IDS = new HashSet<>();
    private static final int MAX_STUDENT_ID = 99999;
    private static final int MIN_STUDENT_ID = 10000;
    private static final Random random = new Random();

    public static String generateStudentId() {
        int studentId;
        do {
            studentId = random.nextInt((MAX_STUDENT_ID - MIN_STUDENT_ID) + 1) + MIN_STUDENT_ID;
        } while (!isUnique(studentId));
        EXISTING_IDS.add(studentId);
        return studentId + "";
    }

    private static boolean isUnique(int studentId) {
        return !EXISTING_IDS.contains(studentId);
    }
}
