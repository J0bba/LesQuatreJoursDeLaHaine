package providers;

import interfaces.IProvider;

public class SingletonProvider<T> implements IProvider {
    T instance;

    public SingletonProvider(T instance)
    {
        this.instance = instance;
    }

    public T get() {
        return instance;
    }
}
