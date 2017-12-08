package providers;

import aspects.Aspect;
import aspects.Context;
import interfaces.IProvider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class Provider<T> implements IProvider<T> {

    ArrayList<Aspect> aspects = new ArrayList<>();

    public class Invocator implements InvocationHandler {
        Object target;

        public Invocator(Object target)
        {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Aspect a : aspects) {
                if (a.aspectType == Aspect.AspectType.BEFORE_INVOKE && method.equals(a.method))
                    a.execute();
            }

            boolean oneAround = false;
            Object result = null;
            for (Aspect a : aspects) {
                if (a.aspectType == Aspect.AspectType.AROUND_INVOKE && method.equals(a.method)){
                    oneAround = true;

                    result = new Context(target, /*args*/, /*methods*/).execute();
                }
            }

            if (!oneAround)
                result = method.invoke(target, args);

            for (Aspect a : aspects) {
                if (a.aspectType == Aspect.AspectType.AFTER_INVOKE && method.equals(a.method))
                    a.execute();
            }
            return result;
        }
    }
}
