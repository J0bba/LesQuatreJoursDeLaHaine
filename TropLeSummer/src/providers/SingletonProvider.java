package providers;

import aspects.Aspect;
import aspects.PostCreateAspect;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class SingletonProvider<T> extends AnyProvider<T> {
    private final T instance;

    public SingletonProvider(final ArrayList<Aspect> aspects, final T instance)
    {
        super(aspects);
        this.instance = instance;

        for (PostCreateAspect a : postCreateAspects) {
            a.execute();
        }
    }

    public T get() {
        Invocator invocator = new Invocator(instance);

        return (T)Proxy.newProxyInstance(
                instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(),
                invocator);
    }
}
