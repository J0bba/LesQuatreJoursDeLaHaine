package aspects;

import java.lang.reflect.Method;
import java.util.function.Function;

public class AroundInvokeAspect extends Aspect {
    final Function function;
    final Method method;

    public AroundInvokeAspect(final Function function, final Method method)
    {
        this.function = function;
        this.method = method;
    }

    @Override
    public void execute() {
    }
}
