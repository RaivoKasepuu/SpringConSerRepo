package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void transferMoney(String fromAccount,
                              String toAccount,
                              BigDecimal amount) {
        BigDecimal fromAccountBalance = accountRepository.getBalance(fromAccount);
        if (fromAccountBalance.compareTo(amount) >= 0) {
            BigDecimal toAccountBalance = accountRepository.getBalance(toAccount);
            fromAccountBalance = fromAccountBalance.subtract(amount);
            toAccountBalance = toAccountBalance.add(amount);
            accountRepository.updateBalance(fromAccount, fromAccountBalance);
            accountRepository.updateBalance(toAccount, toAccountBalance);
        }
    }

    public BigDecimal getAccountBalance(String accountNumber) {
        BigDecimal accountBalance = accountRepository.getBalance(accountNumber);
        history("", accountNumber, "balance request");
        return accountBalance;
    }

    public void makeDeposit(String accountNumber, BigDecimal deposit) {
        accountRepository.updateBalance(accountNumber, accountRepository.getBalance(accountNumber).add(deposit));
        history("", accountNumber, "deposit made: "+ deposit);
    }

    public void createAccount(String accountNumber, Integer id) {
        accountRepository.createAccount(accountNumber, id);
        history("",accountNumber, "account created");
    }

    public void createCustomer(String name, Integer personalId) {
        accountRepository.createCustomer(name, personalId);
        history(name,"", "customer created");
    }

    public void makeWithdraw(String accountNumber, BigDecimal withdraw) {
        int check = accountRepository.getBalance(accountNumber).compareTo(withdraw);
        if (check >= 0) {
            accountRepository.updateBalance(accountNumber, accountRepository.getBalance(accountNumber).subtract(withdraw));
            history("",accountNumber, "withdraw: " + withdraw);
        } else {
            System.out.println("Withdraw request > account balance");
        }
    }

    public void makeTransferService(String accountFrom, String accountTo, BigDecimal amountToTransfer) {
        int check = accountRepository.getBalance(accountFrom).compareTo(amountToTransfer);
        if (check >= 0) {
            accountRepository.updateBalance(accountFrom, accountRepository.getBalance(accountFrom).subtract(amountToTransfer));
            accountRepository.updateBalance(accountTo, accountRepository.getBalance(accountTo).add(amountToTransfer));
            history("", accountFrom, "transfer to " + accountTo + ", amount: " + amountToTransfer);
            history("",accountTo, "transfer from " + accountFrom+ ", amount: " + amountToTransfer);
        } else {
            System.out.println("Transfer request > account balance");
        }

    }

    public List<Account> getAllBalancesService() {
        history("Superman","all accounts", "balance request");
        return accountRepository.getAllBalances();
    }

    public void history(String name, String account , String action) {
        String time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        accountRepository.historyUpdate(time, name, account, action);

    }
}




