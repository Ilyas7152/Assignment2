import java.util.*;

class Main {
    static BankAccount[] account = new BankAccount[3];
    static Stack<String> history = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);
    static LinkedList<BankAccount> accounts;
    static Queue<BankAccount> accountRequests= new LinkedList<>();

    public static void main(String[] args) {

        account[0]=new BankAccount("1","Ilya",5000000);
        account[1]=new BankAccount("2","Azeke",50000000);
        account[2]=new BankAccount("3","Ilyas",500000000);
        accounts= new LinkedList<>(Arrays.asList(account));
        while (true) {
            System.out.println("1.Bank");
            System.out.println("2.ATM");
            System.out.println("3.ADMIN AREA");
            System.out.println("4.Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                BankMenu();
                break;
                case 2:
                   AtmMenu();
                case 3:
                    AdminMenu();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    public static void addAccount(){
        System.out.println("Enter username");
        String username = scanner.nextLine();
        accountRequests.add(new BankAccount("?", username, 0.0));
        System.out.println("Request submitted now you need to contact with the admin to approve account");

    }
    public static void displayInfo(){
        for(BankAccount account1 : accounts)System.out.println(account1);
    }
    public static void searchUser(){
        System.out.println("Enter username to search:");
        String searchName = scanner.nextLine();
        boolean found = false;
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(searchName)) {
                System.out.println("Found: " + acc);
                found = true;
                break;

            }
        }
        if (!found) System.out.println("Account not found");

    }
    public static void depositMoney(){
        System.out.println("Enter username");
        String depositUser = scanner.nextLine();
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(depositUser)) {
                System.out.println("Enter amount");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                acc.balance += amount;
                history.push("Deposit " + amount + " to " + acc.username);
                System.out.println("Balance=" + acc.balance);
                break;
            }
        }
    }
    public static  void withdrawMoney(){
        String withdrawUser = scanner.nextLine();
        System.out.println("Enter username");
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(withdrawUser)) {
                System.out.println("Enter amount");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                if (acc.balance >= amount) {
                    acc.balance -= amount;
                    history.push("Withdraw" + amount + "to " + acc.username);
                    System.out.println("New balance = " + acc.balance);
                }
            }
            ;
        }
    }
    public static void undoTrans(){
        if (!history.isEmpty()) {
            String removed = history.pop();
            System.out.println("Undo." + removed + "  removed");
        } else {
            System.out.println("No transactions");
        }
    }
    public static void lastTrans(){
        if (!history.isEmpty()) {
            System.out.println("Last transaction" + history.peek());
        } else {
            System.out.println("No transations yet");
        }

    }
    public static void addBill(){
        System.out.print("Enter bill name : ");
        String billName = scanner.nextLine();
        billQueue.add(billName);
        System.out.println("Added: " + billName);
    }
    public static void processBillPayment(){
        if (!billQueue.isEmpty()) {
            String processedBill = billQueue.poll();
            System.out.println("Processing: " + processedBill);
        } else {
            System.out.println("Queue is empty. No bills to process.");
        }
    }
    public static void remainedBills(){
        if (!billQueue.isEmpty()) {
            System.out.println("Remaining: " + billQueue);
        } else {
            System.out.println("Queue is empty.");
        }


    }
    public static  void  approvementFromAdmin(){
        if(!accountRequests.isEmpty()) {
            BankAccount approved = accountRequests.poll();
            accounts.add(approved);

            System.out.println("Account was approved");

        }
        else{ System.out.println("No pending requests");

        }
    }
    public static void displayRemained(){
        System.out.println("Pending requests" + accountRequests);
    }
    public static void BankMenu(){
        while(true) {
            System.out.println("Choose a function");

            System.out.println("1.Add account");
            System.out.println("2.Deposit money");
            System.out.println("3.Withdraw money");
            System.out.println("4.Show last transaction");
            System.out.println("5.Undo transaction");
            System.out.println("6.Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    lastTrans();
                    break;
                case 5:
                    undoTrans();
                    break;

                default:
                    System.out.println("Invalid option");
                case 6:
                    return;
            }
        }
    }
    public static void AtmMenu(){
        while(true){
        System.out.println("Choose a function");
        System.out.println("1.Add bill payment");
        System.out.println("2.Process bill payment");
        System.out.println("3.Display balance");
            System.out.println("4.Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
        switch(choice) {
            case 1:
                addBill();
                break;
            case 2:
                processBillPayment();
                break;
            case 3:
                displayInfo();
                break;

            default:
                System.out.println("Invalid option");
            case 4:
                return;

        }
        }
    }
    public static void AdminMenu(){
       while(true){
        System.out.println("Choose a function");

        System.out.println("1.Approvement from admin");
        System.out.println("2.Remained bill payment");
        System.out.println("3.Display pending requests;");
        System.out.println("4.Search user by name");
           System.out.println("5.Exit");
           int choice = scanner.nextInt();
           scanner.nextLine();
        switch(choice){
            case 1:
                approvementFromAdmin();
                break;
            case 2:
                remainedBills();
                break;
            case 3:
                displayRemained();
             break;
            case 4:
                searchUser();
                break;
            default: System.out.println("Invalid option");
            case 5:
                return;
        }
    }
}}
