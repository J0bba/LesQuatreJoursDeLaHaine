package aspects;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public class AfterInvokeAspect extends BasicInvokeAspect {
    public AfterInvokeAspect(final Consumer consumer, final Method method, final Object arg) {
        super(consumer, method, arg);
    }
}
