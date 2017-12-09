package interfaces;

public interface Scope {
    <T> void addProvider(final Class<T> classType, final Provider<? extends T> provider);
    <T> T getInstanceOf(final Class<T> classType);
    void applyPreDestroy();
}
