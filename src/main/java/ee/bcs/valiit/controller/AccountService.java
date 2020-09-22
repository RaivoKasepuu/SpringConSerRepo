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
    private BankAccountRepository bankAccountRepository;

    public Integer getBalance(String accountNumber) {
        BankAccount result = bankAccountRepository.getByAccountNumber(accountNumber);
        System.out.println("getBalance läbi BankAccountRepository (JPA)");
        return result.getBalance();
    }

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
        System.out.println("AccountService getAccountBalance " + accountNumber);
        BigDecimal accountBalance = accountRepository.getBalance(accountNumber);
        history("", accountNumber, "balance request");
        return accountBalance;
    }

    public void makeDeposit(String accountNumber, BigDecimal deposit) {
        System.out.println("AccountService makeDeposit accountNumber: "+ accountNumber + " deposit: " + deposit);
        accountRepository.updateBalance(accountNumber, accountRepository.getBalance(accountNumber).add(deposit));
        history("", accountNumber, "deposit made: "+ deposit);
    }

    public void createAccount(String accountNumber, Integer id) {
        System.out.println("AccountService createAccount accountNumber: " + accountNumber + " id: " + id);
        accountRepository.createAccount(accountNumber, id);
        history("",accountNumber, "account created");
    }

    public void createCustomer(String name, Integer personalId) {
        System.out.println("AccountService createCustomer name: " + name + " personalId: " + personalId);
        accountRepository.createCustomer(name, personalId);
        //history(name,"", "customer created");
    }

    public void makeWithdraw(String accountNumber, BigDecimal withdraw) {
        System.out.println("AccountService makeWithdraw accountNumber: " + accountNumber + " withdraw: " + withdraw);
        int check = accountRepository.getBalance(accountNumber).compareTo(withdraw);
        if (check >= 0) {
            accountRepository.updateBalance(accountNumber, accountRepository.getBalance(accountNumber).subtract(withdraw));
            history("",accountNumber, "withdraw: " + withdraw);
        } else {
            System.out.println("Withdraw request > account balance");
        }
    }

    public void makeTransferService(String accountFrom, String accountTo, BigDecimal amountToTransfer) {
        System.out.println("AccountService makeTransferService accountFrom: " + accountFrom + " accountTo: " + accountTo + " amountToTransfer: "+ amountToTransfer);
        int check = accountRepository.getBalance(accountFrom).compareTo(amountToTransfer);
        if (check >= 0) {
            accountRepository.updateBalance(accountFrom, accountRepository.getBalance(accountFrom).subtract(amountToTransfer));
            accountRepository.updateBalance(accountTo, accountRepository.getBalance(accountTo).add(amountToTransfer));
            history("", accountFrom, "transfer to " + accountTo + ", amount: " + amountToTransfer);

        } else {
            System.out.println("Transfer request > account balance");
        }

    }

    public List<Account> getAllBalancesService() {
        // List<Account> üles tagastusse
        System.out.println("AccountService getAllBalancesService");
        // siin jama, sest läheb ID kallale repository-s history("Superman","all accounts", "balance request");
        return accountRepository.getAllBalances();
    }

    public void history(String name, String account , String action) {
        String time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        Integer accountId = accountRepository.getIdBasedAccountNumber(account);
        accountRepository.historyUpdate(time,accountId,name, account, action);

    }
}




