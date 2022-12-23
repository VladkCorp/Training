package com.Udm1.BackEndAccountOperations;

import java.util.Arrays;
//Account doesn't store money as of now
public class Account {
    int id;
    //arr size is a placeholder
    Transaction[] transactions = new Transaction[10];
    int transactionIndex = 0;

    Account(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Account ID: " + this.id;
    }

    public class Transaction {

        private Account accountFrom;
        private Account accountTo;
        private double moneyAmount;
        private StandardAccountOperations operation;

        Transaction(Account accountFrom, Account accountTo,
                    double moneyAmount, StandardAccountOperations operation) {
                this.accountFrom = accountFrom;
                this.accountTo = accountTo;
                this.moneyAmount = moneyAmount;
                this.operation = operation;
        }

        double getMoneyAmount(){ return moneyAmount; }
        StandardAccountOperations getOperation(){ return operation; }
        Account getAccountTo(){ return accountTo; }
        Account getAccountFrom(){ return accountFrom; }

        @Override
        public String toString() {
            return "Transaction [accountFrom=" + accountFrom + ", accountTo=" + accountTo
            					+ ", moneyAmount=" + moneyAmount + ", operation=" + operation;
        }
    }

    public void sendMoneyToAccount(Account accountTo, double moneyAmount) {

        if (accountTo != null && moneyAmount > 0) {
            Transaction send = new Transaction(this, accountTo, moneyAmount,
                    StandardAccountOperations.MONEY_TRANSFER_SEND);

            accountTo.receiveMoney(this, moneyAmount);

            if (transactionIndex < transactions.length) {
                this.transactions[transactionIndex] = send;
                this.transactionIndex++;
            }
        } else {
            System.out.println("Sending account not found or requested money is 0");
        } // error here todo
    }

    public void receiveMoney(Account accountFrom, double moneyAmount) {

        if (accountFrom != null && moneyAmount > 0) {
            Transaction receive = new Transaction(accountFrom, this,
                    moneyAmount, StandardAccountOperations.MONEY_TRANSFER_RECEIVE);

            if (this.transactionIndex < this.transactions.length) {
                this.transactions[this.transactionIndex] = receive;
                this.transactionIndex++;
            }
        } else {
            System.out.println("Receiving account not found or requested money is 0");
        }
    }

    public void withdrawMoney (double moneyAmount) {

        if (moneyAmount > 0) {
            Transaction withdraw = new Transaction(this,null,moneyAmount,
                    StandardAccountOperations.WITHDRAW);

            if (transactionIndex < transactions.length) {
                this.transactions[transactionIndex] = withdraw;
                this.transactionIndex++;
            }
        } else {
            System.out.println("Can't withdraw 0 or negative number");
        }
    }

    public Transaction[] getTransactions() throws ArrayIndexOutOfBoundsException {
//TODO EXCEPTION
        if (transactions[0] == null) {
            this.transactions = new Transaction[0];
        }
        return transactions;
    }

    public static void main(String[] args) {
        Account acc1 = new Account(1);
        Account acc2 = new Account(2);

//        acc1.sendMoneyToAccount(acc2,500);
//        acc1.sendMoneyToAccount(acc2,20);
        acc2.withdrawMoney(5);
        System.out.println(Arrays.toString(acc1.getTransactions()));
        System.out.println(Arrays.toString(acc2.getTransactions()));


// TODO array is created regardless of transactions amount! it was 10 but needs to be 0 if Money is 0.
//        shouldNotSendMoneyToAccountIfMoneyIsZero
    }

}
