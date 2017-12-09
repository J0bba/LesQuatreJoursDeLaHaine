package interfaces;

public interface IProvider<T> {
    T get();
    void applyPreDestroy();
}
