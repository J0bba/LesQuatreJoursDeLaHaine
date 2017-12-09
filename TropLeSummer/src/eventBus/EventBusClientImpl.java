package eventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Consumer;

public class EventBusClientImpl implements EventBusClient {
    final HashMap<Channel, ArrayList<Subscription>> map = new HashMap<>();
    @Override
    public Subscription subscribe(Channel channel, Consumer<EventBusClient.Message> callback) {
        if (!map.containsKey(channel))
            return null;

        Subscription res = new SubscriptionImpl(channel, callback);

        if (map.containsKey(channel))
            map.get(channel).add(res);
        else
            map.put(channel, new ArrayList<>(Collections.singletonList(res)));

        return res;
    }

    @Override
    public void revoke(Subscription subscription) {
        if (map.containsKey(subscription.getChannel()) && map.get(subscription.getChannel()).contains(subscription))
            map.get(subscription.getChannel()).remove(subscription);
    }

    @Override
    public void publish(Channel channel, Message message) {
        if (map.containsKey(channel)) {
            for (Subscription s : map.get(channel))
                s.getCallback().accept(message);
        }
    }
}
