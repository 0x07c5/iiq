import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reference {
  private final Object ref;
  public Reference(Object ref) {
    this.ref = ref;
  }

  private Field getField(String name) throws Exception {
    return this.ref.getClass().getField(name);
  }

  private Method getMethod(String name) throws Exception {
    return this.ref.getClass().getMethod(name);
  }

  public Object getValue(String name) throws Exception {
    return this.getField(name).get(this.ref);
  }

  public void setValue(String name, Object value) throws Exception {
    this.getField(name).set(this.ref, value);
  }

  public Object invoke(String name, Object... args) throws Exception {
    return this.getMethod(name).invoke(this.ref, args);
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