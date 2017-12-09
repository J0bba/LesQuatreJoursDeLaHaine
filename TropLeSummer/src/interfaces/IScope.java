package interfaces;

public interface IScope {
    <T> void addProvider(final Class<T> classType, final IProvider<? extends T> provider);
    <T> T getInstanceOf(final Class<T> classType);
    void applyPreDestroy();
}
