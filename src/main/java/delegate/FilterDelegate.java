package delegate;

import java.util.Map;
import java.util.function.Predicate;

/**
 * Delegate para filtrar la tabla
 */
public interface FilterDelegate {
    public void filters(Map<String, Predicate> coditions);
}
