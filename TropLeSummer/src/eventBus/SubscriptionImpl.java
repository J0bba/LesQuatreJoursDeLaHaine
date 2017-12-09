package eventBus;

import java.time.LocalDateTime;
import java.util.function.Consumer;

public class SubscriptionImpl implements EventBusClient.Subscription {
    final EventBusClient.Channel channel;
    final Consumer<EventBusClient.Message> callback;
    final LocalDateTime localDateTime;

    public SubscriptionImpl(final EventBusClient.Channel channel, final Consumer<EventBusClient.Message> callback)
    {
        this.channel = channel;
        this.callback = callback;
        localDateTime = LocalDateTime.now();
    }

    @Override
    public EventBusClient.Channel getChannel() {
        return channel;
    }

    @Override
    public Consumer<EventBusClient.Message> getCallback() {
        return callback;
    }

    @Override
    public LocalDateTime getSubscriptionDateTime() {
        return localDateTime;
    }

    @Override
    public long getMessageReceivedCount() {
        return 0;
    }
}
