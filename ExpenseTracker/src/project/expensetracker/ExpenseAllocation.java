package project.expensetracker;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
public class ExpenseAllocation {
    private ArrayList<Expense> expenses=new ArrayList<>();
    public ExpenseAllocation() {
        expenses.add(new Expense(101,"Lunch",250,Category.FOOD,LocalDate.of(2026,10,1)));
        expenses.add(new Expense(102,"Cab",100,Category.TRAVEL,LocalDate.of(2026,10,1)));
        expenses.add(new Expense(103,"Dress",1500,Category.SHOPPING,LocalDate.of(2026,10,2)));
        expenses.add(new Expense(104,"Electricity Bill",500,Category.BILLS,LocalDate.of(2026,10,2)));
    }
    public void addExpense(Expense expense) {
        for(Expense e:expenses) {
            if(e.getId()==expense.getId()) {
                throw new RuntimeException("Expense ID already exists");
            }
        }
        expenses.add(expense);
        System.out.println("Expense added successfully");
    }
    public void displayExpenses() {
        if(expenses.isEmpty()) {
            System.out.println("No expenses found");
            return;
        }
        for(Expense e:expenses) {
            System.out.println(e);
        }
    }
    public void deleteExpense(int id) {
        Expense found=null;
        for(Expense e:expenses) {
            if(e.getId()==id) {
                found=e;
                break;
            }
        }
        if(found!=null) {
            expenses.remove(found);
            System.out.println("Expense deleted successfully");
        } else {
            System.out.println("Expense not found");
        }
    }
    public void categorizeExpense(int id,Category category) {
        for(Expense e:expenses) {
            if(e.getId()==id) {
                e.setCategory(category);
                System.out.println("Expense category updated");
                return;
            }
        }
        System.out.println("Expense not found");
    }
    public void calculateTotal() {
        double total=expenses.stream().mapToDouble(e->e.getAmount()).sum();
        System.out.println("Total Expense: ₹"+total);
    }
    public void findHighestExpense() {
        Expense highest=expenses.stream().max((e1,e2)->Double.compare(e1.getAmount(),e2.getAmount())).orElse(null);
        if(highest!=null) {
            System.out.println("Highest Expense:");
            System.out.println(highest);
        } else {
            System.out.println("No expenses found");
        }
    }
    public void categoryWiseExpenses() {
        HashMap<Category,Double> totals=new HashMap<>();
        for(Category category:Category.values()) {
            double total=expenses.stream().filter(e->e.getCategory()==category).mapToDouble(e->e.getAmount()).sum();
            if(total>0) {
                totals.put(category,total);
            }
        }
        for(Category category:totals.keySet()) {
            System.out.println(category+" : ₹"+totals.get(category));
        }
    }
    public void showCategory(Category category) {
        boolean found=false;
        for(Expense e:expenses) {
            if(e.getCategory()==category) {
                System.out.println(e);
                found=true;
            }
        }
        if(!found) {
            System.out.println("No expenses in this category");
        }
    }
}