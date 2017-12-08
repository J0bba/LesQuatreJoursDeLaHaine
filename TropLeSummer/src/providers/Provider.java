package providers;

import aspects.*;
import interfaces.IProvider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class Provider<T> implements IProvider<T> {
    ArrayList<BeforeInvokeAspect> beforeInvokeAspects = new ArrayList<>();
    ArrayList<AfterInvokeAspect> afterInvokeAspects = new ArrayList<>();
    ArrayList<PostCreateAspect> postCreateAspects = new ArrayList<>();
    ArrayList<AroundInvokeAspect> aroundInvokeAspects = new ArrayList<>();

    public Provider(ArrayList<Aspect> aspects)
    {
        for (Aspect a : aspects)
        {
            if (a instanceof BeforeInvokeAspect)
                beforeInvokeAspects.add(BeforeInvokeAspect.class.cast(a));
            else if (a instanceof AfterInvokeAspect)
                afterInvokeAspects.add(AfterInvokeAspect.class.cast(a));
            else if (a instanceof PostCreateAspect)
                postCreateAspects.add(PostCreateAspect.class.cast(a));
        }
    }

    public class Invocator implements InvocationHandler {
        Object target;

        public Invocator(Object target)
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
            {
                //result = new Context();
            }
            else
                result = method.invoke(target, args);

            /*
            for (Aspect a : aspects) {
                if (a.aspectType == Aspect.AspectType.AROUND_INVOKE && method.equals(a.method)){
                    oneAround = true;

                    //result = new Context(target, args, methods).execute();
                }
            }*/

            for (AfterInvokeAspect a : afterInvokeAspects)
            {
                if (method.equals(a.method))
                    a.execute();
            }
            return result;
        }
    }
}
