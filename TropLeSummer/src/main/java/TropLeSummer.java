import interfaces.IProvider;
import interfaces.IScope;
import scopes.AnyScope;

import java.util.Iterator;
import java.util.Stack;

public class TropLeSummer {
    private final Stack<IScope> scopes = new Stack<IScope>();

    public TropLeSummer()
    {
        addScope();
    }

    public <T extends IProvider> void addProvider(Class<? extends T> classType, T provider)
    {
        scopes.peek().addProvider(classType, provider);
    }

    public <T> T getInstanceOf(Class<? extends T> classType)
    {
        for (Iterator<IScope> iterator = scopes.iterator(); iterator.hasNext();) {
            IScope o = iterator.next();
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

    public void removeScope()
    {
        scopes.pop();
    }
}
