package aspects;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public class Aspect {
    Consumer consumer;
    Object arg;
    public enum AspectType { BEFORE_INVOKE, AFTER_INVOKE, POST_CREATE };
    public final AspectType aspectType;
    public final Method method;

    public Aspect(final AspectType aspectType, Consumer consumer, Object arg)
    {
        this.aspectType = aspectType;
        this.consumer = consumer;
        this.arg = arg;
        method = null;
    }

    public Aspect(final AspectType aspectType, Consumer consumer, Object arg, Method method)
    {
        this.aspectType = aspectType;
        this.consumer = consumer;
        this.arg = arg;
        this.method = method;
    }

    public void execute()
    {
        consumer.accept(arg);
    }
}
