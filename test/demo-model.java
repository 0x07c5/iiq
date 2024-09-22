import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

public class Operator {
  public static final String append = "append";
  public static final String replace = "replace";
}

public class Path {
  public static final String firstName = "name.first_name";
  public static final String lastName = "name.last_name";
}

public class Operation {
  public String op;
  public String path;
  public Object value;

  public Operation(String op, String path, Object value) {
    this.op = op;
    this.path = path;
    this.value = value;
  }
}

public class BodyModel {
  public Operation[] operations;
  public String[] schemas;

  public BodyModel(Operation[] operations, String[] schemas) {
    this.operations = operations;
    this.schemas = schemas;
  }

  public String toString() {
    return new GsonBuilder()
      .setExclusionStrategies(new ExclusionStrategy() {
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
          return fieldAttributes.getName().startsWith("_");
        }
        public boolean shouldSkipClass(Class aClass) {
          return false;
        }
      })
      .create()
      .toJson(this)
    ;
  }
}

public String toJson(Object obj) {
  return new GsonBuilder()
    .setPrettyPrinting()
    .setExclusionStrategies(new ExclusionStrategy() {
      public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fieldAttributes.getName().startsWith("_");
      }

      public boolean shouldSkipClass(Class aClass) {
        return false;
      }
    })
    .create()
    .toJson(obj)
  ;
}

// 

try {
  model = new BodyModel(
    new Operation[] {
      new Operation(
        Operator.replace,
        Path.firstName,
        "John"
      ),
      new Operation(
        Operator.replace,
        Path.lastName,
        "Cena"
      )
    },
    new String[] {
      "some:schema"
    }
  );
  System.out.println(toJson(model));
} catch (Exception e) {
  System.out.println("---");
  e.printStackTrace();
}

return requestEndPoint;