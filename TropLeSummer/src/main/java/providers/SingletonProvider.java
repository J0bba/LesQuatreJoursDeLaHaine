package providers;

import interfaces.IProvider;

public class SingletonProvider<T> implements IProvider<T> {
    private final T instance;

    public SingletonProvider(final T instance)
    {
        this.instance = instance;
    }

    public T get() {
        return instance;
    }
}
