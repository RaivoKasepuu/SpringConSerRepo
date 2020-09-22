// CREATE CUSTOMER
let buttonCustomer = document.getElementById("create_customer");
buttonCustomer.addEventListener('click', function () {
    let createCustomerMessages = document.getElementById("createCustomerMessages");
    let name = document.getElementById("insertName");
    let url = "/createcustomer/" + name.value;
    // alert(url);
    fetch(url, {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(function (response) {
            return "customer is created"; //response.json();
        })
        .then(function (jsonData) {

            createCustomerMessages.innerText = "Done, " + jsonData;
            //   updateBoard();
        })
        .catch(function (err) {
            createCustomerMessages.innerText = "Midagi on pekkis, " + "  url: " + url;
        });
});

// CREATE ACCOUNT
let buttonAccount = document.getElementById("create_account");
buttonAccount.addEventListener('click', function () {
    let createAccountMessages = document.getElementById("createAccountMessages");
    let createAccountNumber = document.getElementById("insertAccountNumber");
    let insertCustomerId = document.getElementById("insertCustomerId");
    let customerId = insertCustomerId.value;
    let url = "/createaccount/" + createAccountNumber.value;
    // alert(url);
    fetch(url, {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: customerId
    })
        .then(function (response) {
            console.log(response);
            return "Account is made"; // response.json();
        })
        .then(function (jsonData) {
            console.log(jsonData);
            createAccountMessages.innerText = "Done, " + jsonData;

        })
        .catch(function (err) {
            createAccountMessages.innerText = 'Midagi on pekkis' + " url: " + url + " account is: " + createAccountNumber.value + " customerId: " + customerId;
        });
});


// GET BALANCE
let buttonBalance = document.getElementById("get_balance");
buttonBalance.addEventListener('click', function () {
    let messages = document.getElementById("messages");
    let account = document.getElementById("getAccount");
    let url = "/getbalance/" + account.value;
    // alert(url);
    fetch(url, {
        method: 'GET',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (jsonData) {
            messages.innerText = "Balance is: " + jsonData;

        })
        .catch(function (err) {
            messages.innerText = 'Midagi on pekkis' + "  url: " + url;
        })
});

// MAKE WITHDRAW
let buttonWithdraw = document.getElementById("make_withdraw");
buttonWithdraw.addEventListener('click', function () {
    let withdrawMessages = document.getElementById("withdrawMessages");
    let withdrawAmount = document.getElementById("withdrawAmount");
    let withdrawAccount = document.getElementById("makeWithdrawAccount");
    let withdraw = withdrawAmount.value;
    let url = "/makewithdraw/" + withdrawAccount.value;
    //alert(url);
    fetch(url, {
        method: 'PUT',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: withdraw
    })


        .then(function (response) {
            console.log(response);
            return "withdraw is made"; //response json();
        })
        .then(function (jsonData) {
            console.log(jsonData);
            withdrawMessages.innerText = "Done, " + jsonData;
            //   updateBoard();
        })
        .catch(function (err) {
            withdraw.innerText = 'Midagi on pekkis' + "  url: " + url + " withdrawAmount: " + withdraw;
        })

});

// MAKE DEPOSIT
let buttonDeposit = document.getElementById("make_deposit");
buttonDeposit.addEventListener('click', function () {
    let depositMessages = document.getElementById("depositMessages");
    let depositAmount = document.getElementById("depositAmount");
    let depositAccount = document.getElementById("makeDepositAccount");
    let deposit = depositAmount.value;
    let url = "/makedeposit/" + depositAccount.value;
    //alert(url);
    fetch(url, {
        method: 'PUT',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: deposit
    })

        .then(function (response) {
            console.log(response);
            return "deposit is made";//response.json();
        })
        .then(function (jsonData) {
            console.log(jsonData);
            depositMessages.innerText = "Done, " + jsonData;

        })
        .catch(function (err) {
            console.log(err);
            depositMessages.innerText = 'Midagi on pekkis' + "  url: " + url + " deposit: " + deposit;
        })

});


// MAKE TRANSFER
let buttonTransfer = document.getElementById("make_transfer");
buttonTransfer.addEventListener('click', function () {
    let transferMessages = document.getElementById("transferMessages");
    let transferAmount = document.getElementById("transferAmount");
    let transferFromAccount = document.getElementById("transferFromAccount");
    let transferToAccount = document.getElementById("transferToAccount");
    let transfer = transferAmount.value;
    let url = "/maketransfer/" + transferFromAccount.value + "," + transferToAccount.value;
    //alert(url);
    fetch(url, {
        method: 'PUT',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: transfer
    })

        .then(function (response) {
            console.log(response);
            return "transfer is made";//response.json();
        })
        .then(function (jsonData) {
            console.log(jsonData);
            transferMessages.innerText = "Done, " + jsonData;

        })
        .catch(function (err) {
            transferMessages.innerText = 'Midagi on pekkis' + "  url: " + url + " transferAmount: " + transfer;
            console.log(err);
        })

});


// GET ALL ACCOUNTS


let buttonGetAllAccounts = document.getElementById("get_all_accounts");
buttonGetAllAccounts.addEventListener('click', function () {
    let board = document.getElementById("board");
    board.innerHTML = "";
    let getAllAccountsMessages = document.getElementById("getAllAccountsMessages");
    let url = "/getallaccounts/";
    // alert(url);
    fetch(url, {
        method: 'GET',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        }

    })
        .then(function (response) {
            return response.json();
        })

        .then(function (data) {
            let html = '';
            for (let i = 0; i < data.length; ++i) {
                let item = data[i];
                html += `\t Account id :${item.id} \t Account number:${item.accountNumber} \t Balance:${item.accountBalance} \t client id:${item.client_id} \n`;

            }
            board.innerHTML = html;


        })
        .catch(function (err) {
            getAllAccountsMessages.innerText = 'Midagi on pekkis' + "  url: " + url;
        })

});
