import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Reference {
  protected final Object ref;
  public Reference(Object ref) {
    this.ref = ref;
  }

  public Class getRefClass() {
    return this.ref.getClass();
  }

  public Field getField(String name) throws Exception {
    return this.ref.getClass().getField(name);
  }

  public Method getMethod(String name) throws Exception {
    return this.ref.getClass().getMethod(name);
  }

  public Method getMethod(String name, Class[] parameterTypes) {
    return this.ref.getClass().getMethod(name, parameterTypes);
  }

  public Method[] getMethods(String name) throws Exception {
    Method[] methods = this.ref.getClass().getDeclaredMethods();
    List result = new ArrayList();
    for (Method method: methods) {
      if (method.getName().equals(name))
        result.add(method);
    }
    return result.toArray(new Method[0]);
  }

  public Class getType(String name) throws Exception {
    return this.getField(name).getType();
  }

  public Object getValue(String name) throws Exception {
    return this.getField(name).get(this.ref);
  }

  public void setValue(String name, Object value) throws Exception {
    this.getField(name).set(this.ref, value);
  }

  public String[] methodDefines(String name) throws Exception {
    Method[] methods = this.getMethods(name);
    List result = new ArrayList();
    for (Method method: methods) {
      result.add(methodDefine(method));
    }
    return result.toArray(new String[0]);
  }

  public static String methodDefine(Method method) {
    String methodName = method.getName();
    Class[] parameterTypes = method.getParameterTypes();
    List typeNames = new ArrayList();
    for (Class c: parameterTypes) {
      typeNames.add(c.getSimpleName());
    }
    String returnType = method.getReturnType().getSimpleName();
    return methodName + "(" + String.join(", ", typeNames) + ") -> " + returnType;
  }

  public Object invoke(String name) throws Exception {
    return this
      .getRefClass()
      .getMethod(name)
      .invoke(this.ref)
    ;
  }

  public Object invoke(String name, Object[] args) throws Exception {
    List classes = new ArrayList();
    for(Object arg: args) {
      classes.add(arg.getClass());
    }
    return this
      .getRefClass()
      .getMethod(
        name,
        classes.toArray(new Class[0])
      )
      .invoke(this.ref, args)
    ;
  }

  public Field[] getAllFields() throws Exception {
    return this.ref.getClass().getDeclaredFields();
  }

  public Method[] getAllMethods() throws Exception {
    return this.ref.getClass().getDeclaredMethods();
  }

  public String[] getAllFieldNames() {
    Field[] fields = this.ref.getClass().getDeclaredFields();
    List result = new ArrayList();
    for (Field field: fields) {
      result.add(field.getName());
    }
    return result.toArray(new String[0]);
  }

  public String[] getAllMethodNames() {
    Method[] methods = this.ref.getClass().getDeclaredMethods();
    List result = new ArrayList();
    for (Method method: methods) {
      result.add(method.getName());
    }
    return result.toArray(new String[0]);
  }
}