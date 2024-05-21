package test;

class Account{
    double getBalance() {
        return 0;
    }
}

class SavingsAccount extends Account {
    double getBalance() {
        return 100;
    }
}
class TermAccount extends Account {
    double getBalance() {
        return 1000;
    }
}
class CurrentAccount extends Account {
    double getBalance() {
        return 10000;
    }
}
