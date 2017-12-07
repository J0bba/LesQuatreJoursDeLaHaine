package scopes;

import interfaces.IProvider;
import interfaces.IScope;

import java.util.HashMap;

public class AnyScope implements IScope{
    private final HashMap<Class<?>, IProvider<?>> providers = new HashMap<Class<?>, IProvider<?>>();

    public <T> void addProvider(Class<? extends T> classType, IProvider provider) {
        providers.put(classType, provider);
    }

    public <T> T getInstanceOf(Class<? extends T> classType)
    {
        if (!providers.containsKey(classType))
            return null;

        return classType.cast(providers.get(classType).get());
    }
}
