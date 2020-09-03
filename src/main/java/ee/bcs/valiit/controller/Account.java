package ee.bcs.valiit.controller;

import java.math.BigDecimal;

public class Account {
    private Integer id;
    private String accountNumber;
    private BigDecimal accountBalance;
    private Integer client_id;

    public Account(Integer id, String accountNumber, BigDecimal accountBalance, Integer client_id) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.client_id = client_id;
    }

    public Account() {

    }


    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }






    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}