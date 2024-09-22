public void main() {
  class Ref {
    public String name, note;

    public Ref(String name, String note) {
      this.name = name;
      this.note = note;
    }
  };

  Ref ref = new Ref("John", "Doe");

  ref.name = "John";

  System.out.println(ref.name);
}

main();

return requestEndPoint;