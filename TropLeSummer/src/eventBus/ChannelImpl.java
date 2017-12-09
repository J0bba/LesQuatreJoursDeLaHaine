package eventBus;

public class ChannelImpl implements EventBusClient.Channel {
    private final String address;

    public ChannelImpl(final String address)
    {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return address;
    }
}
