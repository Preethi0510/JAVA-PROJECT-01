package project.expensetracker;
import java.time.LocalDate;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ExpenseAllocation service=new ExpenseAllocation();
        int choice;
        do {
            System.out.println("\n1. Add Expense");
            System.out.println("2. Display All Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Categorize Expense");
            System.out.println("5. Calculate Total Expense");
            System.out.println("6. Find Highest Expense");
            System.out.println("7. Category-wise Expenses");
            System.out.println("8. View Expenses by Category");
            System.out.println("9. Exit");
            System.out.println("Enter choice:");
            choice=sc.nextInt();
            try {
                switch(choice) {
                    case 1:
                        System.out.println("Enter expense ID:");
                        int id=sc.nextInt();
                        System.out.println("Enter description:");
                        String description=sc.next();
                        System.out.println("Enter amount:");
                        double amount=sc.nextDouble();
                        System.out.println("Enter category:");
                        Category category=Category.valueOf(sc.next().toUpperCase());
                        System.out.println("Enter date yyyy-mm-dd:");
                        LocalDate date=LocalDate.parse(sc.next());
                        service.addExpense(new Expense(id,description,amount,category,date));
                        break;
                    case 2:
                        service.displayExpenses();
                        break;
                    case 3:
                        System.out.println("Enter expense ID:");
                        int deleteId=sc.nextInt();
                        service.deleteExpense(deleteId);
                        break;
                    case 4:
                        System.out.println("Enter expense ID:");
                        int categoryId=sc.nextInt();
                        System.out.println("Enter new category:");
                        Category newCategory=Category.valueOf(sc.next().toUpperCase());
                        service.categorizeExpense(categoryId,newCategory);
                        break;
                    case 5:
                        service.calculateTotal();
                        break;
                    case 6:
                        service.findHighestExpense();
                        break;
                    case 7:
                        service.categoryWiseExpenses();
                        break;
                    case 8:
                        System.out.println("Enter category:");
                        Category searchCategory=Category.valueOf(sc.next().toUpperCase());
                        service.showCategory(searchCategory);
                        break;
                    case 9:
                        System.out.println("Thank you");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        } while(choice!=9);
        sc.close();
    }
}