package DSA1.Array.Sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorSort {

    public static void main(String[] args) {
        // Step 1: Create a sample list of people with name and age
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 25));
        people.add(new Person("David", 35));
        people.add(new Person("Eve", 30));

        // Step 2: Sort the list using Comparator
        // Sorting criteria:
        // - First by age in ascending order
        // - If ages are equal, then by name in lexicographic order
        people.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                // Compare by age
                if (o1.age != o2.age) {
                    return Integer.compare(o1.age, o2.age);
                } else {
                    // If ages are equal, compare by name
                    return o1.name.compareTo(o2.name);
                }
            }
        });

        // Step 3: Print the sorted list
        System.out.println("Sorted list of people:");
        for (Person person : people) {
            System.out.println(person);
        }
    }

    // Person class to represent a person with name and age
    static class Person {
        String name;
        int age;

        // Constructor to initialize person fields
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Overriding toString method to display person in readable format
        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}
