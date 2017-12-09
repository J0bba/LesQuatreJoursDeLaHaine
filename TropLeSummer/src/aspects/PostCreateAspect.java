package aspects;

import java.util.function.Consumer;

public class PostCreateAspect extends BasicAspect {
    public PostCreateAspect(final Consumer consumer, final Object arg) {
        super(consumer, arg);
    }
}
