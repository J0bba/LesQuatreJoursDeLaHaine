package interfaces;

public interface IScope {
    <T> void addProvider(Class<T> classType, IProvider<? extends T> provider);
    <T> T getInstanceOf(Class<T> classType);
}
