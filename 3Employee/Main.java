import java.util.Date;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        // Creating an instance of MyList to store Employee objects
        MyList<Employee> employeeList = new MyList<>();

        // Adding Employee objects to the list
        employeeList.add(new Employee("E001", "Alice", 30, createDate(2020, 1, 15)));
        employeeList.add(new Employee("E002", "Bob", 35, createDate(2019, 5, 10)));
        employeeList.add(new Employee("E003", "Charlie", 28, createDate(2021, 7, 1)));

        // Retrieve and print an Employee from the list
        System.out.println("Employee at index 0: " + employeeList.get(0));

        // Delete an Employee by index
        employeeList.deleteByIndex(1);
        System.out.println("After deleting index 1, Employee List size: " + employeeList.size());

        // Delete an Employee by value
        Employee empToDelete = new Employee("E003", "Charlie", 28, createDate(2021, 7, 1));
        employeeList.deleteByValue(empToDelete);
        System.out.println("After deleting E003, Employee List size: " + employeeList.size());

        // Print remaining employees
        for (int i = 0; i < employeeList.size(); i++) {
            System.out.println("Employee at index " + i + ": " + employeeList.get(i));
        }
    }


    private static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);  // Month is 0-based in Calendar
        return calendar.getTime();
    }
}
