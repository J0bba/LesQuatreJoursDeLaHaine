package providers;

import interfaces.IProvider;

import java.util.function.Supplier;

public class PrototypeProvider<T> implements IProvider<T> {
    final Supplier<T> supplier;

    public PrototypeProvider(final Supplier<T> supplier)
    {
        this.supplier = supplier;
    }

    public T get() {
        return supplier.get();
    }
}
