package specification;

/**
 * Interface que se usa para buscar
 * @param <T> Tipo de dato con el que se quiere hacer la busqueda
 */
public interface MemorySpecification<T> {
    public boolean test(T value);
}
