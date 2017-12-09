import interfaces.IProvider;
import interfaces.IScope;
import scopes.AnyScope;

import java.util.Stack;

public class TropLeSummer {
    private final Stack<IScope> scopes = new Stack<>();

    public TropLeSummer()
    {
        addScope();
    }

    public <T> void addProvider(final Class<T> classType, final IProvider<? extends T> provider)
    {
        scopes.peek().addProvider(classType, provider);
    }


    public <T> T getInstanceOf(final Class<? extends T> classType)
    {
        for (IScope o : scopes) {
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
