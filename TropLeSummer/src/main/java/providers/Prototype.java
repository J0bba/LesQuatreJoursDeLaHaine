package providers;

import interfaces.IProvider;

import java.lang.reflect.Constructor;

public class Prototype<T> implements IProvider {
    Class<? extends T> classType;

    public Prototype(Class<? extends T> classType)
    {
        this.classType = classType;
    }

    public Object get() {
        try {
            return classType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
