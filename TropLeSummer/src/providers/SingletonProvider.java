package providers;

import aspects.Aspect;
import interfaces.IProvider;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class SingletonProvider<T> extends Provider<T> {
    private final T instance;

    public SingletonProvider(final T instance, ArrayList<Aspect> aspects)
    {
        this.instance = instance;
        this.aspects.addAll(aspects);
    }

    public T get() {
        Invocator invocator = new Invocator(instance);

        for (Aspect a : aspects) {
            if (a.aspectType == Aspect.AspectType.POST_CREATE)
                a.execute();
        }

        return (T)Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(), invocator);
    }
}
