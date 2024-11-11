import java.util.Arrays;
import java.util.Scanner;

public class MyList {
    private int[] array;
    private int size;

    public MyList() {
        array = new int[100]; // Initial capacity of 100
        size = 0;
    }

    // Add a new value to the end of the list
    public void add(int value) {
        if (size == array.length) {
            growArray();
        }
        array[size++] = value;
    }

    // Delete an element based on index
    public void deleteByIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index!");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        shrinkArray();
        System.out.println("Deleted element at index " + index);
    }

    // Delete an element based on value (deletes the first occurrence)
    public boolean deleteByValue(int value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                deleteByIndex(i);
                return true;
            }
        }
        System.out.println("Value " + value + " not found!");
        return false;
    }

    // Retrieve a value from a specific index
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index!");
        }
        return array[index];
    }

    // Helper method to double the array size when it reaches capacity
    private void growArray() {
        array = Arrays.copyOf(array, array.length * 2);
    }

    // Helper method to shrink the array size if usage falls below a certain threshold
    private void shrinkArray() {
        if (size < array.length / 4 && array.length > 100) {
            array = Arrays.copyOf(array, array.length / 2);
        }
    }

    // Method to get the current size of the list (number of elements stored)
    public int size() {
        return size;
    }

    // Method to print the elements in the list
    public void printList() {
        System.out.print("Current List: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // Main method for user interaction
    public static void main(String[] args) {
        MyList myList = new MyList();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Add a value");
            System.out.println("2. Delete by index");
            System.out.println("3. Delete by value");
            System.out.println("4. Retrieve a value by index");
            System.out.println("5. Print the list");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to add: ");
                    int valueToAdd = scanner.nextInt();
                    myList.add(valueToAdd);
                    System.out.println("Added " + valueToAdd);
                    break;
                case 2:
                    System.out.print("Enter index to delete: ");
                    int indexToDelete = scanner.nextInt();
                    myList.deleteByIndex(indexToDelete);
                    break;
                case 3:
                    System.out.print("Enter value to delete: ");
                    int valueToDelete = scanner.nextInt();
                    myList.deleteByValue(valueToDelete);
                    break;
                case 4:
                    System.out.print("Enter index to retrieve: ");
                    int indexToRetrieve = scanner.nextInt();
                    try {
                        int retrievedValue = myList.get(indexToRetrieve);
                        System.out.println("Value at index " + indexToRetrieve + ": " + retrievedValue);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    myList.printList();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
