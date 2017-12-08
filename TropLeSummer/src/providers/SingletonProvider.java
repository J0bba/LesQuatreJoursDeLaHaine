package providers;

import aspects.Aspect;
import aspects.PostCreateAspect;
import interfaces.IProvider;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class SingletonProvider<T> extends Provider<T> {
    private final T instance;

    public SingletonProvider(final T instance, ArrayList<Aspect> aspects)
    {
        this.instance = instance;
        this.aspects.addAll(aspects);
        // TODO : fill les lists
    }

    public T get() {
        Invocator invocator = new Invocator(instance);

        for (PostCreateAspect a : postCreateAspects) {
            a.execute();
        }

        return (T)Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(), invocator);
    }
}
