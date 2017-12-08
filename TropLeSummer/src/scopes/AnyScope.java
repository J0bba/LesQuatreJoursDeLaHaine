package scopes;

import interfaces.IProvider;
import interfaces.IScope;

import java.util.HashMap;

public class AnyScope implements IScope{
    private final HashMap<Class<?>, IProvider<?>> providers = new HashMap<>();

    public <T> void addProvider(Class<T> classType, IProvider<? extends T> provider) {
        providers.put(classType, provider);
    }

    public <T> T getInstanceOf(Class<T> classType)
    {
        if (!providers.containsKey(classType))
            return null;

        return classType.cast(providers.get(classType).get());
    }
}
