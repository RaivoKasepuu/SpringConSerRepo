package ee.bcs.valiit.controller;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setAccountNumber(resultSet.getString("account_number"));
        account.setAccountBalance(resultSet.getBigDecimal("balance"));
        account.setId(resultSet.getInt("id"));
        account.setClient_id(resultSet.getInt("bank_customer_id"));
        return account;
    }


}
