package providers;

import aspects.AfterInvokeAspect;
import aspects.Aspect;
import aspects.BeforeInvokeAspect;
import aspects.PostCreateAspect;
import interfaces.IProvider;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class SingletonProvider<T> extends Provider<T> {
    private final T instance;

    public SingletonProvider(ArrayList<Aspect> aspects, final T instance)
    {
        super(aspects);
        this.instance = instance;
    }

    public T get() {
        Invocator invocator = new Invocator(instance);

        for (PostCreateAspect a : postCreateAspects) {
            a.execute();
        }

        return (T)Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(), invocator);
    }
}
