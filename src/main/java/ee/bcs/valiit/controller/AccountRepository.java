package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public BigDecimal getBalance(String account) {
        String sql = "SELECT balance FROM bank_accounts " +
                "WHERE account_number = :accountNumber";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accountNumber", account);
        return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
    }

    public void updateBalance(String account, BigDecimal balance) {
        String sql = "UPDATE bank_accounts SET balance = :balance WHERE " +
                "account_number = :accountNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("balance", balance);
        paramMap.put("accountNumber", account);
        jdbcTemplate.update(sql, paramMap);
    }

    public void createAccount(String accountNumber) {
        String sql = "INSERT INTO bank_accounts (account_number, balance, client_id) values (:accountNumber, :balance, :client_id)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("balance", 0);
        paramMap.put("accountNumber", accountNumber);
        paramMap.put("client_id", (int) ((Math.random() * 500) + 1));
        jdbcTemplate.update(sql, paramMap);

    }

    public List<Account> getAllBalances() {

        String sql = "SELECT * FROM bank_accounts ";
        List<Account> accounts = jdbcTemplate.query(sql, new HashMap<>(), new AccountRowMapper());
        return accounts;
    }
}
