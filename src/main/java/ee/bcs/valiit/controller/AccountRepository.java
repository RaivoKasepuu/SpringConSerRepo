package ee.bcs.valiit.controller;

import liquibase.pro.packaged.S;
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

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accountNumber", account);
        String sql = "SELECT balance FROM bank_account " +
                "WHERE account_number = :accountNumber";

        return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);

    }

    public void updateBalance(String account, BigDecimal balance) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("balance", balance);
        paramMap.put("accountNumber", account);
        String sql = "UPDATE bank_account SET balance = :balance WHERE " +
                "account_number = :accountNumber";
        jdbcTemplate.update(sql, paramMap);

    }

    public void createCustomer(String name, Integer personalId) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("client_name", name);
        paramMap.put("client_id", personalId);
        String sql = "INSERT INTO bank_customer (name, personal_code) values (" +
                ":client_name, " +
                ":client_id)";
        jdbcTemplate.update(sql, paramMap);


    }

    public void createAccount(String accountNumber, Integer id) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("balance", 0);
        paramMap.put("account_number", accountNumber);
        paramMap.put("client_id", id);
        String sql = "INSERT INTO bank_account (account_number, balance, bank_customers_id) values (" +
                ":account_number, " +
                ":balance, " +
                ":client_id)";
        jdbcTemplate.update(sql, paramMap);

    }

    public void historyUpdate (String time, String name, String account, String action) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("time", time);
        paramMap.put("name", name);
        paramMap.put("account", account);
        paramMap.put("action",action);

        String sql = "INSERT INTO history (time, name, account, action) values (" +
                ":time, " +
                ":name, " +
                ":account, " +
                ":action)";
        jdbcTemplate.update(sql, paramMap);

    }


    public List<Account> getAllBalances() {

        String sql = "SELECT * FROM bank_account ";
        List<Account> accounts = jdbcTemplate.query(sql, new HashMap<>(), new AccountRowMapper());
        return accounts;
    }


}
