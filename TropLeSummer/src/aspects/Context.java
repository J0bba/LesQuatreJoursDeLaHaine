package aspects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Context {
    final Object instance;
    final Object[] args;
    final Method method;

    public Context(Object instance, Object[] args, Method method)
    {
        this.instance =instance;
        this.args = args;
        this.method = method;
    }

    public Object execute() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(instance, args);
    }
}
