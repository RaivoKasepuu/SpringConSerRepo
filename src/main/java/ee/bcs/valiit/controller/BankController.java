package ee.bcs.valiit.controller;


import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/*
Loo uus controlleri klass BankController

Kasuta staatilist muutujat andmete salvestamiseks

Defineeri rest enpoindid:

createAccount(String accountNr) | loob uue konto etteantud konto
numbriga

getAccount(String accountNr) | tagasta kui palju raha on vastaval kontol

depositMoney(String accountNr, amount) | kannab loodud kontole raha
(suurendab kontoga seotud raha muutujat)

withdrawMoney(String accountNr, amount) | võtab kontolt raha (vähendab
kontol olevat rahasummat)

transferMoney(String account1, String account2, amount) | kanna raha
esimeselt kontolt teisele kontole

NB:
@PathVariable  - viide, konto jne
@RequestBody - data, amount etc, lisame reegline Postmanis json-ina
@RequestParam - päraast küsimärki url-is
 */

@RestController
public class BankController {

public static final Map<String, Integer> bankAccounts = new HashMap<>();

    @PostMapping("create/{accountNumber}")
    public void createAccount(@PathVariable String accountNumber) {
        System.out.println("Account: "+accountNumber+ " created" );
        bankAccounts.put(accountNumber, 0 );
        System.out.println("****************************************");
    }

    @GetMapping("balance/{accountNumber}")
    public Integer getAccount(@PathVariable String accountNumber) {
        System.out.println("Account "+ accountNumber+ " has "+ bankAccounts.get(accountNumber)+" dollars");

        System.out.println("****************************************");
        return bankAccounts.get(accountNumber);
    }
/*
    @GetMapping("bank")
    public String getAllAccount(@PathVariable String accountNumber) {
        System.out.println("getAccount ");
        return bankAccounts.toString();
    }
    */


    @PostMapping("deposit/{accountNumber}")
    public void depositMoney(@PathVariable String accountNumber, @RequestBody Integer amount) {
        System.out.println("depositMoney ");
        System.out.println("Amount before: " + getAccount(accountNumber));

        bankAccounts.put(accountNumber, bankAccounts.get(accountNumber) + amount);
        System.out.println("Amount after :"+ bankAccounts.get(accountNumber));
        System.out.println("****************************************");
    }

    @PostMapping("withdraw/{accountNumber}")
    public void withdrawMoney(@PathVariable String accountNumber, @RequestBody Integer amount) {
        System.out.println("withdrawMoney");
        System.out.println("From: " + accountNumber + " before: " + bankAccounts.get(accountNumber));
        if (bankAccounts.get(accountNumber) > amount) {
            bankAccounts.put(accountNumber, bankAccounts.get(accountNumber) - amount);
        } else {
            System.out.println("Amount on bankAccount < withdraw request");
        }
        System.out.println("after: " + bankAccounts.get(accountNumber));
        System.out.println("****************************************");
    }

    @PostMapping("transfer/{accountNumber},{toAccountNumber}")
    public void btMoney(@PathVariable String accountNumber, @RequestBody Integer amount, @PathVariable String toAccountNumber) {
        // http://localhost:8080/bankTransfer/1111,100,1112 case sensitive!!!!

        System.out.println("transferMoney");
        System.out.println("From: " + accountNumber + " before: " + bankAccounts.get(accountNumber));
        System.out.println("To: " + toAccountNumber + " before: " + bankAccounts.get(toAccountNumber));
        System.out.println("amount: " + amount);
        //System.out.println("From: " + accountNumber + " before: " + bankAccounts.get(accountNumber));
        if (bankAccounts.get(accountNumber) > amount) {
            bankAccounts.put(accountNumber, bankAccounts.get(accountNumber) - amount);
            bankAccounts.put(toAccountNumber, bankAccounts.get(toAccountNumber) + amount);
        } else {
            System.out.println("Amount on bankAccount < withdraw request");
        }
        System.out.println(accountNumber + " after: " + bankAccounts.get(accountNumber));
        System.out.println(toAccountNumber + "after: " + bankAccounts.get(toAccountNumber));

        System.out.println("****************************************");
    }



/*

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

*/




}
