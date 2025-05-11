package view;

import helper.Utils;
import model.Account;
import model.Client;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    static String name = "New Bank";
    static Scanner keyboard = new Scanner(System.in);
    static ArrayList<Account> accounts;

    public static void main(String[] args) {
        Bank.accounts = new ArrayList<Account>();
        Bank.menu();
    }

    private static void menu() {
        int option = 0;
        System.out.println("====================================");
        System.out.println("============== ATM =================");
        System.out.println("============ " + Bank.name + " ==============");
        System.out.println("====================================");
        System.out.println("Selecione uma opção no menu: ");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Efetuar saque");
        System.out.println("3 - Efetuar depósito");
        System.out.println("4 - Efetuar transferência");
        System.out.println("5 - Listar contas");
        System.out.println("6 - Sair do sistema");

        try {
            option = Integer.parseInt(Bank.keyboard.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, informe uma opção válida. ");
            Utils.stop(2);
            Bank.menu();
        }

        switch (option) {
            case 1:
                Bank.createAccount();
                break;
            case 2:
                Bank.withdraw();
                break;
            case 3:
                Bank.deposit();
                break;
            case 4:
                Bank.transfer();
                break;
            case 5:
                Bank.listAccounts();
                break;
            case 6:
                System.out.println("Até a próxima!");
                Utils.stop(2);
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
                Utils.stop(2);
                Bank.menu();
                break;
        }
    }

    private static void createAccount() {
        System.out.println("Informe os dados do cliente: ");
        System.out.println("Nome do cliente: ");
        String name = Bank.keyboard.nextLine();

        System.out.println("E-mail do cliente: ");
        String email = Bank.keyboard.nextLine();

        System.out.println("CPF do cliente: ");
        String cpf = Bank.keyboard.nextLine();

        System.out.println("Data de nascimento do cliente: ");
        String birthDate = Bank.keyboard.nextLine();

        Client client = new Client(name, email,cpf, Utils.stringToDate(birthDate));

        Account account = new Account(client);

        Bank.accounts.add(account);
        System.out.println("Conta criada com sucesso!");
        System.out.println("Dados da conta criada: ");
        System.out.println(account);
        System.out.println();

        Utils.stop(4);
        Bank.menu();
    }

    private static void withdraw() {
        System.out.println("Informe o número da conta: ");
        int number = Bank.keyboard.nextInt();
        Account account = Bank.findAccountByNumber(number);

        if (account != null) {
            System.out.println("Informe o valor para saque: ");
            Double value = Bank.keyboard.nextDouble();

            account.withdraw(value);
        } else {
            System.out.println("Não foi possível encontrar a conta: " + number);
        }
        Utils.stop(3);
        Bank.menu();
    }

    private static Account findAccountByNumber(int numberAccount) {
        Account foundAccount = null;
        if (Bank.accounts.size() > 0) {
            for (Account account : Bank.accounts) {
                if (account.getNumber() == numberAccount) {
                    foundAccount = account;
                }
            }
        }
        return foundAccount;
    }

    private static void deposit() {
        System.out.println("Informe o número da conta: ");
        int number = Bank.keyboard.nextInt();

        Account account = Bank.findAccountByNumber(number);

        if (account != null) {
            System.out.println("Informe o valor de depósito: ");
            Double value = Bank.keyboard.nextDouble();
            account.deposit(value);
            Bank.menu();
        } else {
            System.out.println("Não foi encontrada a conta número: " + number);
            Utils.stop(3);
            Bank.menu();
        }
    }
    private static void transfer() {
        System.out.println("Informe o número da sua conta: ");
        int sourceAccount = Bank.keyboard.nextInt();

        Account account = Bank.findAccountByNumber(sourceAccount);

        if (account != null) {
            System.out.println("Informe o número da conta de destino: ");
            int destinationAccount = Bank.keyboard.nextInt();

            Account account2 = Bank.findAccountByNumber(destinationAccount);

            if (account2 != null) {
                System.out.println("Informe o valor da transferência: ");
                Double value = Bank.keyboard.nextDouble();
                account.transfer(account2, value);
            } else {
                System.out.println("A conta destino número " + account2 + " não foi encontrada.");
            }
        } else {
            System.out.println("Não foi possível encontrar a conta: " + sourceAccount);
        }

        Utils.stop(3);
        Bank.menu();


    }
    private static void listAccounts() {
        System.out.println("");
        if (Bank.accounts.size() > 0 ) {
            System.out.println("Listagem de contas");
            System.out.println();
            for (Account account : Bank.accounts){
                System.out.println(account);
                System.out.println();
                Utils.stop(2);
            }
            System.out.println();
        } else  {
            System.out.println("Não existem contas cadastradas ainda.");
        }
        Utils.stop(3);
        Bank.menu();
    }




}
