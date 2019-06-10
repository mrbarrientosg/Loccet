package repository;

public interface MemorySpecification<T> {
    public boolean test(T value);
}
