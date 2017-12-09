package aspects;

import java.lang.reflect.Method;
import java.util.function.Function;

public class AroundInvokeAspect extends Aspect {
    public final Function<Context, Object> function;
    public final Method method;

    public AroundInvokeAspect(final Function<Context, Object> function, final Method method)
    {
        this.function = function;
        this.method = method;
    }

    @Override
    public void execute() {
    }
}
