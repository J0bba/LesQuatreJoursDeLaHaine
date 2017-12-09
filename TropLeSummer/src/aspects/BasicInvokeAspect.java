package aspects;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public abstract  class BasicInvokeAspect extends Aspect {
    public final Method method;
    public final Consumer consumer;
    public final Object arg;

    public BasicInvokeAspect(final Consumer consumer, final Method method, final Object arg)
    {
        this.consumer = consumer;
        this.method = method;
        this.arg = arg;
    }

    @Override
    public void execute() {
        consumer.accept(arg);
    }
}
