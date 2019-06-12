package delegate;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface FilterDelegate {
    public void filters(Map<String, Predicate> coditions);
}
