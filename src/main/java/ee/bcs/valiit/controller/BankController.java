package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
public class BankController {

    @Autowired
    private AccountService accountService;

    @PostMapping("createrandomaccount/")

    public void createAccount() {
        int randomAccountNumber = (int) ((Math.random() * 5000) + 1000);
        String accountNumber = Integer.toString(randomAccountNumber);
        int id = 1;
        accountService.createAccount(accountNumber, id);
    }


    @PostMapping("createaccount/{accountNumber}")
    public void createAccount(@PathVariable String accountNumber, @RequestBody Integer id) {
        System.out.println("BankController createAccount accountNumber: " + accountNumber + " id: " + id);
        accountService.createAccount(accountNumber, id);
    }

    /*
        @PostMapping("createnewaccount/{name}")
        public void createAccount(@PathVariable String name) {
            // kontrolline, kas bank_customer tabelis on selline nimi olemas

            // kui olemas, siis teeme konto 0 rahaga
            // kui ei ole klient, teeme uue kliendi

            // genereerime automaatse kontonumbri

            accountService.createAccount(name);
        }
    */
    @PostMapping("createcustomer/{name}")
    public void createCustomer(@PathVariable String name) {
        int randomPersonalCode = (int) ((Math.random() * 500000) + 100);
        System.out.println("BankController createCustomer name: " + name + " peronalCode: " + randomPersonalCode);
        accountService.createCustomer(name, randomPersonalCode);
    }

    @GetMapping("getbalance/{accountNumber}")
    public BigDecimal getBalance(@PathVariable String accountNumber) {
        System.out.println("BankController getBalance accountNumber: " + accountNumber);
        return accountService.getAccountBalance(accountNumber);
    }

    @PutMapping("makedeposit/{accountNumber}")
    public void makeDeposit(@PathVariable String accountNumber, @RequestBody BigDecimal deposit) {
        System.out.println("BankController makeDeposit accountNumber: " + accountNumber + " deposit: " + deposit);
        accountService.makeDeposit(accountNumber, deposit);
    }

    @PutMapping("makewithdraw/{accountNumber}")
    public void makeWithdraw(@PathVariable String accountNumber, @RequestBody BigDecimal withdraw) {
        System.out.println("BankController makeWithdraw accountNumber: " + accountNumber + " withdraw: " + withdraw);
        accountService.makeWithdraw(accountNumber, withdraw);
    }

    @PutMapping("maketransfer/{accountNumberFrom},{accountNumberTo}")
    public void makeTransfer(@PathVariable String accountNumberFrom,
                             @PathVariable String accountNumberTo,
                             @RequestBody BigDecimal amountToTransfer) {
        System.out.println("BankController makeTransfer accountNumberFrom: " + accountNumberFrom + " accountNumberTo: " + accountNumberTo + " amoutToTransfer: " + amountToTransfer);
        accountService.makeTransferService(accountNumberFrom, accountNumberTo, amountToTransfer);
    }

    @GetMapping("getallaccounts")
    public List<Account> getAllBalances() {
        System.out.println("BankController getAllBalances");
        return accountService.getAllBalancesService();
    }

    //transfer by Siim Rebane:
    @PutMapping("transfer")
    public void maketransfer(@RequestBody TransferMoneyRequest request) {
        accountService.transferMoney(
                request.getFromAccount(),
                request.getToAccount(),
                request.getAmount());
    }

}
