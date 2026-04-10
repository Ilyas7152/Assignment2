import java.util.*;

class BankAccount {
    String accountNumber;
    String username;
    double balance;

public BankAccount(String accountNumber,String username,double balance ){
    this.accountNumber=accountNumber;
    this.username=username;
    this.balance=balance;
}
@Override
public String toString(){
    return username +" "+ balance;
}}

