package eventBus;

public class MessageImpl implements EventBusClient.Message {
    final EventBusClient.Channel channel;
    final String messageType;
    final String content;

    public MessageImpl(final EventBusClient.Channel channel, final String messageType, final String content)
    {
        this.channel = channel;
        this.messageType = messageType;
        this.content = content;
    }

    @Override
    public EventBusClient.Channel getChannel() {
        return channel;
    }

    @Override
    public String getMessageType() {
        return messageType;
    }

    @Override
    public String getContent() {
        return content;
    }
}
