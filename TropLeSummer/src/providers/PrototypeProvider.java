package providers;

import aspects.Aspect;
import aspects.PostCreateAspect;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.function.Supplier;

public class PrototypeProvider<T> extends AnyProvider<T> {
    final Supplier<T> supplier;

    public PrototypeProvider(final ArrayList<Aspect> aspects, final Supplier<T> supplier)
    {
        super(aspects);
        this.supplier = supplier;
    }

    public T get() {
        T target = supplier.get();
        Invocator invocator = new Invocator(target);

        for (PostCreateAspect a : postCreateAspects) {
            a.execute();
        }

        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocator);
    }
}

