package util;

public final class StringUtils {

    public static boolean isEmpty(String field) {
        return field.isEmpty() || field.trim().isEmpty();
    }
}
