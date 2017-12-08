package providers;

import aspects.Aspect;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.function.Supplier;

public class PrototypeProvider<T> extends Provider<T> {
    final Supplier<T> supplier;

    public PrototypeProvider(final Supplier<T> supplier, ArrayList<Aspect> aspects)
    {
        this.supplier = supplier;
        this.aspects.addAll(aspects);
    }

    public T get() {
        T target = supplier.get();
        Invocator invocator = new Invocator(target);
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocator);
    }
}

