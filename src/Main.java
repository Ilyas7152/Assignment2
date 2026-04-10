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
            System.out.println("1.ADD NEW ACCOUNT");
            System.out.println("2.Display all");
            System.out.println("3.Search by username");
            System.out.println("4.Exit");
            System.out.println("5. Deposit money");
            System.out.println("6.Withdraw money");
            System.out.println("7.Undo");
            System.out.println("8.Last transaction:");
            System.out.println("9.Add bill payment");
            System.out.println("10.Process bill payment");
            System.out.println("11.Display remained");
            System.out.println("12.Approvement from admin");
            System.out.println("13.Display pending requests");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                 addAccount();
                 break;
                case 2:
                   displayInfo();
                    break;
                case 3:
               searchUser();
               break;
                case 4:
                    System.exit(0);
                case 5:
                    depositMoney();
                    break;

                case 6:
                  withdrawMoney();
                    break;
                case 7:
                    undoTrans();
                    break;
                case 8:
                 lastTrans();
                    break;
                case 9:
                   addBill();
                    break;

                case 10:
              processBillPayment();
                    break;

                case 11:
                    remainedBills();
                default:
                    System.out.println("Invalid option.");

                    break;
                case 12:
                approvementFromAdmin();
                    break;
                    case 13:
                    displayRemained();
                    break;


            }
        }
    }
    public static void addAccount(){
        System.out.println("Enter username");
        String username = scanner.nextLine();
        accountRequests.add(new BankAccount("?", username, 0.0));
        System.out.println("Request submitted");

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
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(withdrawUser)) {
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
}
