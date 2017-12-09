package aspects;

import java.util.function.Consumer;

public class BeforeDestroyAspect extends BasicAspect {
    public BeforeDestroyAspect(final Consumer consumer, final Object arg) {
        super(consumer, arg);
    }
}
