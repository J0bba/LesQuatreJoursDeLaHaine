package aspects;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public class PostCreateAspect extends Aspect {
    public final Consumer consumer;
    public final Object arg;

    public PostCreateAspect(final Consumer consumer, Object arg)
    {
        this.consumer = consumer;
        this.arg = arg;
    }

    @Override
    public void execute() {
        consumer.accept(arg);
    }
}
