package ee.bcs.valiit.controller;

public class Account {
    private Integer id;
    private String accountNumber;
    private Integer amount;
    private Integer client_id;

    public Account(Integer id, String accountNumber, Integer amount, Integer client_id) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.amount = amount;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}