package interfaces;

public interface IScope {
    <T> void addProvider(Class<? extends T> classType, IProvider provider);
    <T> T getInstanceOf(Class<? extends T> classType);
}
