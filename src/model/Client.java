package model;

import helper.Utils;

import java.util.Date;

public class Client {
    private static int counter = 101;
    private int code;
    private String name;
    private String email;
    private String cpf;
    private Date dateOfBirth;
    private Date registerDate;

    public Client(String name, String email, String cpf, Date dateOfBirth) {
        this.code = Client.counter;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.registerDate = new Date();

        Client.counter += 1;
    }

    public int getCode() {
        return this.code;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterDate() {
        return this.registerDate;
    }

    @Override
    public String toString() {
        return "Código: " + this.getCode() +
                "\nNome: " + this.getName() +
                "\nEmail: " + this.getEmail() +
                "\nCPF: " + this.getCpf() +
                "\nData de nascimento: " + Utils.dateToString(this.getDateOfBirth()) +
                "\nData de cadastro: " + Utils.dateToString(this.getRegisterDate());
    }
}
