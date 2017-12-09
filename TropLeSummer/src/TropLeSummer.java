import interfaces.Provider;
import interfaces.Scope;
import scopes.AnyScope;

import java.util.Stack;

public class TropLeSummer {
    private final Stack<Scope> scopes = new Stack<>();

    public TropLeSummer()
    {
        addScope();
    }

    public <T> void addProvider(final Class<T> classType, final Provider<? extends T> provider)
    {
        scopes.peek().addProvider(classType, provider);
    }


    public <T> T getInstanceOf(final Class<? extends T> classType)
    {
        for (Scope o : scopes) {
            T instance = o.getInstanceOf(classType);
            if (instance != null)
                return instance;
        }

        return null;
    }

    public void addScope()
    {
        scopes.add(new AnyScope());
    }

    public void popScope()
    {
        scopes.peek().applyPreDestroy();
        scopes.pop();
    }
}
