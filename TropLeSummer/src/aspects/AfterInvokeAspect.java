package aspects;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public class AfterInvokeAspect extends Aspect {
    public final Method method;
    public final Consumer consumer;
    public final Object arg;

    public AfterInvokeAspect(final Consumer consumer, final Method method, Object arg)
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
