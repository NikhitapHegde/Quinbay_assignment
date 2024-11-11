public class Main {
    public static void main(String[] args) {
        MyList<Integer> intList = new MyList<>();
        intList.add(10);
        intList.add(20);
        intList.add(30);
        System.out.println("Integer List First Element: " + intList.get(0));
        intList.deleteByValue(10);
        System.out.println("After deleting 10, Integer List size: " + intList.size());

        MyList<String> stringList = new MyList<>();
        stringList.add("Hello");
        stringList.add("World");
        System.out.println("String List First Element: " + stringList.get(0));
        stringList.deleteByIndex(0);
        System.out.println("After deleting index 0, String List size: " + stringList.size());

        MyList<MyCustomClass> customList = new MyList<>();
        customList.add(new MyCustomClass("Test Object 1"));
        customList.add(new MyCustomClass("Test Object 2"));
        System.out.println("Custom Object List First Element: " + customList.get(0));
        customList.deleteByValue(new MyCustomClass("Test Object 1"));
        System.out.println("After deleting Test Object 1, Custom Object List size: " + customList.size());
    }
}

class MyCustomClass {
    private String name;

    public MyCustomClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyCustomClass{name='" + name + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyCustomClass that = (MyCustomClass) obj;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
