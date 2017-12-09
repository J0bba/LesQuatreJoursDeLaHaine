package scopes;

import interfaces.Provider;
import interfaces.Scope;

import java.util.HashMap;

public class AnyScope implements Scope {
    private final HashMap<Class<?>, Provider<?>> providers = new HashMap<>();

    public <T> void addProvider(final Class<T> classType, final Provider<? extends T> provider) {
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
        for (HashMap.Entry<Class<?>, Provider<?>> entry : providers.entrySet())
        {
            entry.getValue().applyPreDestroy();
        }
    }
}
