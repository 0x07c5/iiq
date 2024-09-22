import java.lang.reflect.Field;

private Object init(String _name, String _note) {
  class Ref {
    public String name, note;

    public Ref(String name, String note) {
      this.name = name;
      this.note = note;
    }
  };
  return new Ref(_name, _note);
  // return new Object() {
  //   public String name = _name, note = _note;
  // };
}

private Object getValue(Object ref, String name) {
  try {
    Field field = ref.getClass().getField(name);
    return field.get(ref);
  } catch (Exception e) {
    return null;
  }
}

private void setValue(Object ref, String name, Object value) {
  try {
    Field field = ref.getClass().getField(name);
    field.set(ref, value);
  } catch (Exception ignored) { }
}

void main() {
  Object ref = init("John", "Doe");
  setValue(ref, "note", "Leo");
  System.out.println(ref);
  System.out.println((String) getValue(ref, "note"));
}

main();

System.gc();
return requestEndPoint;