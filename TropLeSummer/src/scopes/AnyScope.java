package scopes;

import interfaces.IProvider;
import interfaces.IScope;

import java.util.HashMap;

public class AnyScope implements IScope{
    private final HashMap<Class<?>, IProvider<?>> providers = new HashMap<>();

    public <T> void addProvider(final Class<T> classType, final IProvider<? extends T> provider) {
        providers.put(classType, provider);
    }

    public <T> T getInstanceOf(final Class<T> classType)
    {
        if (!providers.containsKey(classType))
            return null;

        return classType.cast(providers.get(classType).get());
    }

    public void applyPreDestroy()
    {
        for (HashMap.Entry<Class<?>, IProvider<?>> entry : providers.entrySet())
        {
            entry.getValue().applyPreDestroy();
        }
    }
}
