package model;

import helper.Utils;

public class Account {
    private static int code = 1001;
    private int number;
    private Client client;
    private Double amount = 0.0;
    private Double limit = 0.0;
    private Double totalAmount;

    public Account(Client client) {
        this.number = Account.code;
        this.client = client;
        Account.code += 1;
        this.updateTotalAmount();
    }

    public int getNumber() {
        return number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
        this.updateTotalAmount();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    private void updateTotalAmount() {
        this.totalAmount = this.getAmount() + this.getLimit();
    }

    public void deposit(Double value) {
        if (value > 0) {
            this.amount = this.getAmount() + value;
            this.updateTotalAmount();
            System.out.println("Depósito efetuado com sucesso! ");
        } else {
            System.out.println("Erro ao efetuar depósito. Tente novamente. ");
        }
    }

    public void withdraw(Double value) {
        if (value > 0 && this.getTotalAmount() >= value) {
            if (this.getAmount() >= value) {
                this.amount = this.getAmount() - value;
                this.updateTotalAmount();
                System.out.println("Saque efetuado com sucesso.");
            } else {
                Double remaining = this.getAmount() - value;
                this.limit = this.getLimit() + remaining;
                this.amount = 0.0;
                this.updateTotalAmount();
                System.out.println("Saque efetuado com sucesso!");
            }

        } else {
            System.out.println("Saque não realizado. Tente novamente. ");
        }
    }

    public void transfer(Account destinationAccount, Double value) {
        if (value > 0 && this.getTotalAmount() >= value) {
            if (this.getAmount() >= value) {
                this.amount = this.getAmount() - value;
                destinationAccount.amount = destinationAccount.getAmount() + value;
                this.updateTotalAmount();
                destinationAccount.updateTotalAmount();
                System.out.println("Transferência realizada com sucesso! ");
            } else {
                Double remaining = this.getAmount() - value;
                this.limit = this.getLimit() + remaining;
                this.amount = 0.0;

                destinationAccount.amount = destinationAccount.getAmount() + value;
                this.updateTotalAmount();
                destinationAccount.updateTotalAmount();
                System.out.println("Transferência realizada com sucesso!");
            }
        } else {
            System.out.println("Transferência não realizada. Tente novamente.");
        }
    }

    public String toString() {
        return "Número da conta: " + this.getNumber() +
                "\nCliente: " + this.getClient() +
                "\nSaldo total: " + Utils.doubleToString(this.getTotalAmount());
    }
}
