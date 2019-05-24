package repository;

public interface MemorySpecification<T> extends Specification {
    public boolean test(T value);
}
