package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public BigDecimal getAccountBalance(String AccountNumber) {
        BigDecimal accountBalance = accountRepository.getBalance(AccountNumber);
        return accountBalance;
    }

    public void makeDeposit(String accountNumber, BigDecimal deposit) {
        accountRepository.updateBalance(accountNumber, accountRepository.getBalance(accountNumber).add(deposit));
    }

    public void createAccount(String accountNumber) {
        accountRepository.createAccount(accountNumber);
    }

    public void makeWithdraw(String accountNumber, BigDecimal withdraw) {
        int check = accountRepository.getBalance(accountNumber).compareTo(withdraw);
        if (check >= 0) {
            accountRepository.updateBalance(accountNumber, accountRepository.getBalance(accountNumber).subtract(withdraw));
        } else {
            System.out.println("Withdraw request > account balance");
        }
    }

    public void makeTransferService(String accountFrom, String accountTo, BigDecimal amountToTransfer) {
        int check = accountRepository.getBalance(accountFrom).compareTo(amountToTransfer);
        if (check >=0) {
            accountRepository.updateBalance(accountFrom, accountRepository.getBalance(accountFrom).subtract(amountToTransfer));
            accountRepository.updateBalance(accountTo, accountRepository.getBalance(accountTo).add(amountToTransfer));
        }
        else {
            System.out.println("Transfer request > account balance");
        }

    }

    public List<Account> getAllBalancesService() {
        return accountRepository.getAllBalances();
    }
}



