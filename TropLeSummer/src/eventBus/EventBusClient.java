package eventBus;

import java.time.LocalDateTime;
import java.util.function.Consumer;

/**
 * Interface for event bus clients.
 */
@SuppressWarnings("unused")
public interface EventBusClient {

    /**
     * Subscribe to the given channel.
     *
     * @param channel  The channel to subscribe to.
     * @param callback The callback to register and have called on message reception.
     * @return A subscription object to keep track of subscription and statistics.
     */
    Subscription subscribe(final Channel channel, final Consumer<Message> callback);

    /**
     * Revoke the given subscription.
     *
     * @param subscription The subscription to revoke.
     */
    void revoke(final Subscription subscription);

    /**
     * Publish the given message on the given channel.
     *
     * @param channel The channel to publish to.
     * @param message The message to publish.
     */
    void publish(final Channel channel, final Message message);


    /**
     * Interface for channels.
     */
    interface Channel {
        /**
         * Get the address of the given channel.
         *
         * @return the address of the given channel.
         */
        String getAddress();
    }

    /**
     * Interface for messages.
     */
    interface Message {
        /**
         * The origin channel.
         *
         * @return the origin channel.
         */
        Channel getChannel();

        /**
         * Get the (java) type of the message content.
         *
         * @return The Java type of the message content.
         */
        String getMessageType();

        /**
         * Get the content, as json.
         *
         * @return the json content.
         */
        String getContent();
    }

    /**
     * Interface for subscriptions.
     */

    interface Subscription {

        /**
         * The subscription channel.
         *
         * @return get the subscription channel.
         */
        Channel getChannel();

        /**
         * Get the callback to call for incoming messages.
         *
         * @return the callback to call for incoming messages.
         */
        Consumer<Message> getCallback();

        /**
         * Get the date/time of subscription.
         *
         * @return the date/time of subscription.
         */
        LocalDateTime getSubscriptionDateTime();

        /**
         * Get the count of received messages sine subscription.
         *
         * @return the count of received messages sine subscription.
         */
        long getMessageReceivedCount();
    }
}
