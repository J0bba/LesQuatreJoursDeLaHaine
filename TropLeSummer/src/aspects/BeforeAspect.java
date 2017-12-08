package aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class BeforeAspect implements InvocationHandler {

  private Object targetObject;
  public abstract void before(Object proxy, Method method, Object[] args);

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    before(proxy, method, args);
    return method.invoke(targetObject, args);
  }
}
