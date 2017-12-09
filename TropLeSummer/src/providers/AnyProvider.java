package providers;

import aspects.*;
import interfaces.Provider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

abstract class AnyProvider<T> implements Provider<T> {
    final ArrayList<PostCreateAspect> postCreateAspects = new ArrayList<>();
    private final ArrayList<BeforeInvokeAspect> beforeInvokeAspects = new ArrayList<>();
    private final ArrayList<AfterInvokeAspect> afterInvokeAspects = new ArrayList<>();
    private final ArrayList<AroundInvokeAspect> aroundInvokeAspects = new ArrayList<>();
    private final ArrayList<BeforeDestroyAspect> beforeDestroyAspects = new ArrayList<>();

    public void applyPreDestroy()
    {
        for (BeforeDestroyAspect a : beforeDestroyAspects)
        {
            a.execute();
        }
    }

    AnyProvider(final ArrayList<Aspect> aspects)
    {
        for (Aspect a : aspects)
        {
            if (a instanceof BeforeInvokeAspect)
                beforeInvokeAspects.add(BeforeInvokeAspect.class.cast(a));
            else if (a instanceof AfterInvokeAspect)
                afterInvokeAspects.add(AfterInvokeAspect.class.cast(a));
            else if (a instanceof PostCreateAspect)
                postCreateAspects.add(PostCreateAspect.class.cast(a));
            else if (a instanceof AroundInvokeAspect)
                aroundInvokeAspects.add(AroundInvokeAspect.class.cast(a));
            else if (a instanceof BeforeDestroyAspect)
                beforeDestroyAspects.add(BeforeDestroyAspect.class.cast(a));
        }
    }

    public class Invocator implements InvocationHandler {
        final Object target;

        Invocator(final Object target)
        {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (BeforeInvokeAspect a : beforeInvokeAspects)
            {
                if (method.equals(a.method))
                    a.execute();
            }

            Object result = null;
            if (aroundInvokeAspects.size() > 0)
                for (AroundInvokeAspect a : aroundInvokeAspects)
                    if (method.equals(a.method))
                        result = a.function.apply(new Context(target, args, a.method));
            else
                result = method.invoke(target, args);

            for (AfterInvokeAspect a : afterInvokeAspects)
            {
                if (method.equals(a.method))
                    a.execute();
            }
            return result;
        }
    }
}
