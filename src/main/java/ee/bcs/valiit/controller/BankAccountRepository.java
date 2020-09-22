package ee.bcs.valiit.controller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    BankAccount getByAccountNumber(String accountNumber);
}
