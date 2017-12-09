package aspects;

import java.util.function.Consumer;

public abstract class BasicAspect extends Aspect {
    private final Consumer consumer;
    private final Object arg;

    public BasicAspect(final Consumer consumer, final Object arg)
    {
        this.consumer = consumer;
        this.arg = arg;
    }

    @Override
    public void execute() {
        consumer.accept(arg);
    }
}
