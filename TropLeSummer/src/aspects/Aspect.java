package aspects;

import java.util.function.Consumer;

public class Aspect {
    Consumer consumer;
    Object arg;
    public enum AspectType { BEFORE_INVOKE, AFTER_INVOKE, POST_CREATE };
    public final AspectType aspectType;

    public Aspect(final AspectType aspectType, Consumer consumer, Object arg)
    {
        this.aspectType = aspectType;
        this.consumer = consumer;
        this.arg = arg;
    }

    public void execute()
    {
        consumer.accept(arg);
    }
}
