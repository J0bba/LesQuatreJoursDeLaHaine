package interfaces;

public interface Provider<T> {
    T get();
    void applyPreDestroy();
}
