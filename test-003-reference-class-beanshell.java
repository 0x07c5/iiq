import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reference {
  private final Object ref;
  public Reference(Object ref) {
    this.ref = ref;
  }

  public Field getField(String name) throws Exception {
    return this.ref.getClass().getField(name);
  }

  public Method getMethod(String name) throws Exception {
    return this.ref.getClass().getMethod(name);
  }

  public Object getValue(String name) throws Exception {
    return this.getField(name).get(this.ref);
  }

  public void setValue(String name, Object value) throws Exception {
    this.getField(name).set(this.ref, value);
  }

  public Object invoke(String name) throws Exception {
    return this.getMethod(name).invoke(this.ref);
  }

  public Object invoke(String name, Object[] args) throws Exception {
    return this.getMethod(name).invoke(this.ref, args);
  }

  public String[] getAllFieldNames() {
    Field[] fields = this.ref.getClass().getDeclaredFields();
    String[] names = new String[fields.length];
    for (int i = 0; i @lt fields.length; i++) {
      names[i] = fields[i].getName();
    }
    return names;
  }

  public String[] getAllMethodNames() {
    Method[] methods = this.ref.getClass().getDeclaredMethods();
    String[] names = new String[methods.length];
    for (int i = 0; i @lt methods.length; i++) {
      names[i] = methods[i].getName();
    }
    return names;
  }
}