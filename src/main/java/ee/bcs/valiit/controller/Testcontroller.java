package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static ee.bcs.valiit.controller.Lesson3.sumarray;
import static ee.bcs.valiit.controller.Lesson3.summa;
import static ee.bcs.valiit.controller.Lesson3Hard.evenFibonacci;
import static ee.bcs.valiit.controller.Lesson3Hard.morseCode;


@RestController
public class Testcontroller {

    @GetMapping("employees")
    public List<Employee> getAllEmployees() {
        System.out.println("List");
        return employees;
    }

    @PostMapping("employee")
    public void addEmployee(@RequestBody Employee employee) {
        System.out.println("Post");
        employees.add(employee);
    }

    @GetMapping(value ="employee/{id}")
    public Employee getEmployeeByID(@PathVariable int id) {
        System.out.println("Get " + id);
        return employees.get(id);
    }

    @PutMapping("employee/{id}")
    public void changeEmployee(@RequestBody Employee employee, @PathVariable int id) {
        employees.set(id, employee);
        System.out.println("set " + id);

    }

    @DeleteMapping("employee/{id}")
    public void deleteEmployeeByID(@PathVariable int id) {
        System.out.println("Delete");
        employees.remove(id);
    }







    private static final List<Employee> employees = new ArrayList<>();

    public static int fibonacci(int x) {
        // TODO tagasta x fibonacci
        // Näiteks
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21

        if (x > 2) {
            return fibonacci(x - 1) + fibonacci(x - 2);
        } else if (x == 2) {
            return 1;
        } else {
            return 0;
        }
    }

    @GetMapping(value = "/friends")
    public Friends dtoTests() {
        Friends friends = new Friends();
        friends.setName("Albert");
        friends.setAddress("Kapa-Kohila");
        return friends;

        // http://localhost:8080/friends
    }

    @PostMapping("/friends")
    public void postTests(@RequestBody Friends friends) {
        System.out.println(friends.getName());
        System.out.println(friends.getAddress());

        // http://localhost:8080/friends
    }

    @GetMapping(value = "/fibonacci")
    public Integer test(@RequestParam("id") int id) {
        // http://localhost:8080/fibonacci?id=5

        return fibonacci(id);
    }

    @GetMapping(value = "/evenfibonacci")
    public Integer test2(@RequestParam("id") int id) {
        // http://localhost:8080/evenfibonacci?id=5
        return evenFibonacci(id);
    }

    @GetMapping(value = "/sum")
    public Integer sum(@RequestParam("a") int a, @RequestParam("b") int b) {
        // http://localhost:8080/sum?a=5&b=4
        return summa(a, b);

    }

    @GetMapping(value = "/sumarray")
    public Integer test4(@RequestParam("array") int[] array) {
        // http://localhost:8080/sumarray?array=1,2,3
        return sumarray(array);

    }

    @GetMapping(value = "/morsecode")
    public String test3(@RequestParam("morse") String morse) throws FileNotFoundException {
        // http://localhost:8080/morsecode?morse=raivo
        return morseCode(morse);
    }

    @GetMapping(value = "/pornhub")
    public String test(@RequestParam("actor") String actor) {

        // http://localhost:8080/pornhub?actor
        return "Madis Vatko";
    }


}
