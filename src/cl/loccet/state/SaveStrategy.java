package cl.loccet.state;

public interface SaveStrategy<T> {
    public T save(T object);
}
