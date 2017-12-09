package aspects;

import java.util.function.Consumer;

public class PostCreateAspect extends Aspect {
    private final Consumer consumer;
    private final Object arg;

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
