package aspects;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public class BeforeInvokeAspect extends BasicInvokeAspect {
    public BeforeInvokeAspect(final Consumer consumer, final Method method, final Object arg) {
        super(consumer, method, arg);
    }
}
