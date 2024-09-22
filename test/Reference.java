import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Reference {
  protected final Object ref;
  public Reference(Object ref) {
    this.ref = ref;
  }

  public Class<?> getRefClass() {
    return this.ref.getClass();
  }

  public Field getField(String name) throws Exception {
    return this.ref.getClass().getField(name);
  }

  public List<Method> getMethod(String name) throws Exception {
    return Arrays
      .stream(this.ref.getClass().getDeclaredMethods())
      .filter(m -> m.getName().equals(name))
      .collect(Collectors.toList())
    ;
  }

  public Class<?> getType(String name) throws Exception {
    return this.getField(name).getType();
  }

  public Object getValue(String name) throws Exception {
    return this.getField(name).get(this.ref);
  }

  public void setValue(String name, Object value) throws Exception {
    this.getField(name).set(this.ref, value);
  }

  public String[] methodDefine(String name) throws Exception {
    return this
      .getMethod(name)
      .stream()
      .map(m -> {
        String[] classes = Arrays
          .stream(m.getParameterTypes())
          .map(Class::getSimpleName)
          .toArray(String[]::new)
        ;
        String returnType = m.getReturnType().getSimpleName();
        return name + "(" + String.join(", ", classes) + ") -> " + returnType;
      })
      .toArray(String[]::new)
    ;
  }

  public Object invoke(String name, Object ...args) throws Exception {
    return this
      .getRefClass()
      .getMethod(
        name,
        Arrays.stream(args).map(Object::getClass).toArray(Class[]::new)
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
    return Arrays
      .stream(this.ref.getClass().getDeclaredFields())
      .map(Field::getName)
      .toArray(String[]::new)
    ;
  }

  public String[] getAllMethodNames() {
    return Arrays
      .stream(this.ref.getClass().getDeclaredMethods())
      .map(Method::getName)
      .toArray(String[]::new)
    ;
  }
}