package ee.bcs.valiit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
@RequestParam - pärast küsimärki url-is
 */

@RestController
public class BankController {

    public static final Map<String, Integer> bankAccounts = new HashMap<>();

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostMapping("create/{accountNumber}")
    public void createAccount(@PathVariable String accountNumber) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account", accountNumber);
        paramMap.put("balance", 0);
        int random = (int) ((Math.random() * 500) + 1);
        paramMap.put("client_id", random);
        jdbcTemplate.update("INSERT INTO bank_accounts (account_number, balance, client_id) values (:account, :balance, :client_id)",
                paramMap);
        System.out.println("Account: " + accountNumber + " created");
        bankAccounts.put(accountNumber, 0);
        System.out.println("****************************************");
    }

    @GetMapping("balance/{accountNumber}")
    public Integer getAccount(@PathVariable String accountNumber) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account", accountNumber);
// SQL injection
// EE123'); DROP TABLE bank_accounts; --,
// SELECT balance FROM bank_accounts WHERE (account_number = 'EE123');
        Integer balance = jdbcTemplate.queryForObject("SELECT balance FROM bank_accounts WHERE (account_number = :account)",
                paramMap, Integer.class);


        System.out.println("Account " + accountNumber + " has " + balance + " dollars");
        System.out.println("****************************************");
        return balance;
    }

    @PostMapping("deposit/{accountNumber}")
    public void depositMoney(@PathVariable String accountNumber, @RequestBody Integer amount) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account", accountNumber);
        paramMap.put("balance", amount);
        Integer before = jdbcTemplate.queryForObject("SELECT balance FROM bank_accounts WHERE (account_number = :account)",
                paramMap, Integer.class);
        Integer balance = before + amount;
        paramMap.put("newbalance", balance);
        jdbcTemplate.update("UPDATE bank_accounts SET balance = :newbalance WHERE (account_number = :account)", paramMap);


        System.out.println("depositMoney ");
        System.out.println("Amount before: " + before);
        balance = jdbcTemplate.queryForObject("SELECT balance FROM bank_accounts WHERE (account_number = :account)",
                paramMap, Integer.class);
        System.out.println("Amount after :" + balance);
        System.out.println("****************************************");
    }

    @PostMapping("withdraw/{accountNumber}")
    public void withdrawMoney(@PathVariable String accountNumber, @RequestBody Integer amount) {
        System.out.println("withdrawMoney");

        // Küsime konto seisu:
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account", accountNumber);
        paramMap.put("amount", amount);

        Integer before = jdbcTemplate.queryForObject("SELECT balance FROM bank_accounts WHERE (account_number = :account)",
                paramMap, Integer.class);
        System.out.println("Account: " + accountNumber + " has: " + before);
        System.out.println("Withdraw request: " + amount);

        if (before > amount) {
            // teeme withdraw
            Integer balance = before - amount;
            paramMap.put("newbalance", balance);
            jdbcTemplate.update("UPDATE bank_accounts SET balance = :newbalance WHERE (account_number = :account)", paramMap);

        } else {
            //keeldume withdraw-st
            System.out.println("Amount on bankAccount < withdraw request");
        }
        Integer after = jdbcTemplate.queryForObject("SELECT balance FROM bank_accounts WHERE (account_number = :account)",
                paramMap, Integer.class);

        System.out.println("Balance after withdraw: " + after);
        System.out.println("****************************************");
    }



    @PostMapping("transfer/{accountNumber},{toAccountNumber}")
    public void btMoney(@PathVariable String accountNumber, @RequestBody Integer amount, @PathVariable String toAccountNumber) {
        // http://localhost:8080/bankTransfer/1111,100,1112 case sensitive!!!!
        System.out.println("transferMoney");
        // Küsime konto seisu:
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account", accountNumber);
        paramMap.put("amount", amount);
        paramMap.put("toaccount", toAccountNumber);

        Integer beforeFrom = jdbcTemplate.queryForObject("SELECT balance FROM bank_accounts WHERE (account_number = :account)",
                paramMap, Integer.class);
        Integer toAccountBefore = jdbcTemplate.queryForObject("SELECT balance FROM bank_accounts WHERE (account_number = :toaccount)",
                paramMap, Integer.class);

        System.out.println("Account: " + accountNumber + " has: " + beforeFrom);
        System.out.println("Transfer request amount: " + amount);
        System.out.println("to account: " + toAccountNumber + "with balance before: " + toAccountBefore);
        System.out.println("Thinking about it...");
        System.out.println();
        if (beforeFrom > amount) {
            // teeme transferi
            System.out.println("Pappi jagub!");
            Integer newBalance = beforeFrom - amount;
            paramMap.put("newbalance", newBalance);
            jdbcTemplate.update("UPDATE bank_accounts SET balance = :newbalance WHERE (account_number = :account)", paramMap);
            Integer new2balance = toAccountBefore + amount;
            paramMap.put("new2balance", new2balance);
            jdbcTemplate.update("UPDATE bank_accounts SET balance = :new2balance WHERE (account_number = :toaccount)", paramMap);

        } else {
            //keeldume transferist
            System.out.println("Amount on bankAccount < transfer request");
        }

        System.out.println("From account balance after transfer request: " + jdbcTemplate.queryForObject("SELECT balance FROM bank_accounts WHERE (account_number = :account)",
                paramMap, Integer.class));
        System.out.println("To account balance after transfer request: " + jdbcTemplate.queryForObject("SELECT balance FROM bank_accounts WHERE (account_number = :toaccount)",
                paramMap, Integer.class));
        System.out.println("****************************************");
    }

    @GetMapping("allaccounts")
    public List<Account> getAllAccounts() {

        String sql = "SELECT * FROM bank_accounts ";
        List<Account> accounts = jdbcTemplate.query(sql, new HashMap<>(), new AccountRowMapper());
        //System.out.println(accounts.toString());
        return accounts;
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
