package test;

public class Main {

    static String processInputNew(String input) {
        String output = null;
        switch(input) {
            case null -> output = "Oops, null";
            case String s when "Yes".equalsIgnoreCase(s) -> output = "It's Yes";
            case String s when "No".equalsIgnoreCase(s) -> output = "It's No";
            case String s -> output = "Try Again";
        }
        return output;
    }

    static double getBalanceWithSwitchPattern(Account account) {
        double result = 0;
        switch (account) {
            case null -> System.out.println("Oops, account is null");
            case SavingsAccount sa -> result = sa.getBalance();
            case TermAccount ta -> result = ta.getBalance();
            case CurrentAccount ca -> result = ca.getBalance();
            default -> result = account.getBalance();
        };
        return result;
    }

    static void printName(Person person){
        switch(person){
            case Person p-> {
                System.out.println(p.name());
            }
            case null -> {
                System.out.println("Object is null");
            }
        }
    }
    public static void main(String[] args){
        printName(null);
        getBalanceWithSwitchPattern(null);
    }
}
