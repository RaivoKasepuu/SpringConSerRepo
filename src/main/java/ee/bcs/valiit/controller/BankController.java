package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
public class BankController {

    @Autowired
    private AccountService accountService;

    @PostMapping("createaccount/{accountNumber}")
    public void createAccount(@PathVariable String accountNumber) {
        accountService.createAccount(accountNumber);
    }


    @GetMapping("getbalance/{accountNumber}")
    public BigDecimal getBalance(@PathVariable String accountNumber) {
        return accountService.getAccountBalance(accountNumber);
    }

    @PutMapping("makedeposit/{accountNumber}")
    public void makeDeposit(@PathVariable String accountNumber, @RequestBody BigDecimal deposit) {
        accountService.makeDeposit(accountNumber, deposit);
    }

    @PutMapping("makewithdraw/{accountNumber}")
    public void makeWithdraw(@PathVariable String accountNumber, @RequestBody BigDecimal withdraw) {
        accountService.makeWithdraw(accountNumber, withdraw);
    }

    @PutMapping("maketransfer/{accountNumberFrom},{accountNumberTo}")
    public void makeTransfer(@PathVariable String accountNumberFrom, @PathVariable String accountNumberTo, @RequestBody BigDecimal amountToTransfer) {
        accountService.makeTransferService(accountNumberFrom, accountNumberTo, amountToTransfer);
    }

    @GetMapping("getallaccounts")
        public List<Account> getAllBalances() {
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
