package model.store;

public interface MemorySpecification<T> {
    public boolean test(T value);
}
