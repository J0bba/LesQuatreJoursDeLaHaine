package providers;

import aspects.AfterInvokeAspect;
import aspects.Aspect;
import aspects.BeforeInvokeAspect;
import aspects.PostCreateAspect;
import interfaces.IProvider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class Provider<T> implements IProvider<T> {

    ArrayList<Aspect> aspects = new ArrayList<>();
    ArrayList<BeforeInvokeAspect> beforeInvokeAspects = new ArrayList<>();
    ArrayList<AfterInvokeAspect> afterInvokeAspects = new ArrayList<>();
    ArrayList<PostCreateAspect> postCreateAspects = new ArrayList<>();

    public class Invocator implements InvocationHandler {
        Object target;

        public Invocator(Object target)
        {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (BeforeInvokeAspect b : beforeInvokeAspects)
            {
                if (method.equals(b.method))
                    b.execute();
            }

            boolean oneAround = false;
            Object result = null;
            /*
            for (Aspect a : aspects) {
                if (a.aspectType == Aspect.AspectType.AROUND_INVOKE && method.equals(a.method)){
                    oneAround = true;

                    //result = new Context(target, args, methods).execute();
                }
            }*/

            if (!oneAround)
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
