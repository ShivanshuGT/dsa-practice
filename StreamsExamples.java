import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Employee {
    int id;
    String name;
    String dept;
    double salary;

    Employee(int id, String name, String dept, double salary){
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


}
public class StreamsExamples {
    public static void main(String[] args) {
        // Q1: Find all even numbers in a list using streams
        // List<Integer> list = List.of(1, 2, 3, 4,5 ,6 ,7, 8, 9, 10);
        // list = list.stream().filter(x -> (x % 2 == 0)).toList();
        // System.out.println(list); 

        // Q2: Find all numbers greater than a given value
        // List<Integer> list = List.of(1, 2, 3, 4,5 ,6 ,7, 8, 9, 10);
        // list = list.stream().filter(x -> (x > 6)).toList();
        // System.out.println(list); 

        // Q3: Convert a list of strings to uppercase
        // List<String> list = Arrays.asList("hello", "World", "to");
        // list = list.stream().map(x -> x.toUpperCase()).toList();
        // System.out.println(list);

        // Q4: Count the number of elements in a list
        // List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // System.out.println(list.stream().count());

        // Q5: Find the sum of all elements in a list
        // List<Integer> list = Arrays.asList(1, 2, 3, 4,5);
        // System.out.println(list.stream().reduce((x, y) -> x+y).get());

        // Q6: Find the maximum and minimum element in a list
        // List<Integer> list = Arrays.asList(1, 2, 3, 4, 5,6);
        // System.out.println(list.stream().reduce((x, y) -> x >y ? x : y).get());
        // System.out.println(list.stream().reduce((x, y) -> x <y ? x : y).get());

        // Q7: Sort a list of integers in ascending and descending order
        // List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // list = list.stream().sorted().toList();
        // System.out.println(list);
        // List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // list = list.stream().sorted(Comparator.reverseOrder()).toList();
        // System.out.println(list);

        // Q8: Find the first non-repeated character in a string
        // String str = "sfmvkjvnjdnvjdvnn";
        // Character ch = str.chars().mapToObj(c -> (char) c ).collect(
        //     Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())
        // ).entrySet().stream().filter(x -> x.getValue() == 1).map(x -> x.getKey()).findFirst().orElse(null);
        // System.out.println(ch);

        // Q9: Find duplicate characters in a string
        // String str = "dsfnvjsvsdcksndvjs";
        // Set<Character> set =str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()))
        //     .entrySet().stream().filter(x -> x.getValue() > 1).map(x -> x.getKey()).collect(Collectors.toSet());
        // System.out.println(set);

        // Q10: Count frequency of each character in a string
        // String str = "asdjnjvnnvdjsfdjv";
        // Map<Character, Long> map = str.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
        // System.out.println(map);

        // Q11: Reverse each word in a list of strings
        // List<String> list = List.of("Hello", "World");
        // list = list.stream().map(s -> new StringBuilder(s).reverse().toString()).toList();
        // System.out.println(list);

        // Q12: Join a list of strings with a delimiter
        // List<String> list = List.of("Happy", "Birthday", "to" , "you");
        // String ans = list.stream().collect(Collectors.joining(" "));
        // System.out.println(ans);

        // Q13: Find the longest string in a list
        // List<String> list = List.of("Happy", "Birthday", "to", "you");
        // String ans = list.stream().reduce((x, y) -> x.length() > y.length() ? x : y).get();
        // System.out.println(ans);

        // Q14: Remove duplicate elements from a list
        // List<Integer> list = List.of(1, 2, 3, 3, 2, 0, 0, -1);
        // list = list.stream().distinct().toList();
        // System.out.println(list);





        // Q15: Find duplicate elements in a list
        // List<Integer> list = List.of(1, 2, 3, 2, 1, 0, -1, 0);
        // list = list.stream().collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()))
        //     .entrySet().stream().filter(x -> x.getValue() > 1).map(x -> x.getKey()).toList();
        // System.out.println(list);

        // Q16: Partition a list into even and odd numbers
        // List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        // Map<Boolean, List<Integer>> map = list.stream().collect(Collectors.partitioningBy(x -> (x % 2 == 0)));
        // System.out.println(map);


        // Q17: Group numbers by their remainder when divided by 3
        // List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // Map<Integer, List<Integer>> map = list.stream().collect(Collectors.groupingBy(x -> x % 3));
        // System.out.println(map);

        // Q18: Find the second highest number in a list
        // List<Integer> list = List.of(2, 2, 3, 4, 1, -1, 0, 0);
        // Integer val = list.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(-1);
        // System.out.println(val);
        
        // Q19: Find top N largest numbers
        // List<Integer> list = List.of(2, 2, 3, 3, 4, 1, -1, 0, 0);
        // list = list.stream().sorted(Comparator.reverseOrder()).limit(3).toList();
        // System.out.println(list);

        // Given a list of Employees:

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Ram", "Dev", 1200));
        employees.add(new Employee(2, "Shyam", "Sales", 1300));
        employees.add(new Employee(3, "Ghanshyam", "Dev", 1200));
        employees.add(new Employee(4, "Rohit", "Sales", 1100));
        employees.add(new Employee(5, "Mohit", "Fin", 1000));
        employees.add(new Employee(6, "Vipul", "Fin", 1100));

        // Q20: group them by department
        // Map<String, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(x -> x.dept));
        // System.out.println(map);

        // Q21: Find the highest-paid employee
        // Employee e = employees.stream().max(Comparator.comparingDouble(x -> x.salary)).get();
        // System.out.println(e);

        // Q22: Sort employees by salary and then by name
        // employees = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).thenComparing(Employee::getName)).toList();
        // System.out.println(employees);

        // Q23: Find average salary of employees
        // Double avgSalary = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        // System.out.println(avgSalary);

        // Q24: Convert list of employees to a Map (id → employee)
        // Map<Integer, Employee> map = employees.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
        // System.out.println(map);

        // Q25: Partition employees based on salary (e.g., >50k and ≤50k)
        Map<Boolean, List<Employee>> map = employees.stream().collect(Collectors.partitioningBy(x -> x.salary > 1100));
        System.out.println(map);
    }
}
