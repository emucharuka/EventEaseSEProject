import java.util.ArrayList;

// Abstract class to demonstrate abstraction
public abstract class Event {
    private String id;               // Encapsulation
    private String name;
    private String type;
    private String location;

    private ArrayList<Guest> guests = new ArrayList<>();
    private ArrayList<AgendaItem> agenda = new ArrayList<>();
    private ArrayList<Expense> expenses = new ArrayList<>();
    private ArrayList<Task> tasks = new ArrayList<>();
    private double budgetLimit = 0;

    public Event(String id, String name, String type, String location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getLocation() {
        return location;
    }

    // ===== GUESTS =====
    public void addGuest(String name, String status) {
        guests.add(new Guest(name, status));
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    // ===== AGENDA =====
    public void addAgendaItem(String time, String activity) {
        agenda.add(new AgendaItem(time, activity));
    }

    public ArrayList<AgendaItem> getAgenda() {
        return agenda;
    }

    // ===== BUDGET =====
    public void setBudgetLimit(double limit) {
        this.budgetLimit = limit;
    }
    public double getBudgetLimit() {
        return budgetLimit;
    }
    public void addExpense(String name, double amount) {
        expenses.add(new Expense(name, amount));
    }
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    // ===== TASKS =====
    public void addTask(String description, String assignee) {
        tasks.add(new Task(description, assignee));
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    // Helper method to view tasks
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }

        System.out.println("\n--- TASKS ---");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(i + ". [" +
                    (t.isComplete() ? "DONE" : "TODO") + "] "
                    + t.getDescription()
                    + " (Assignee: " + t.getAssignee() + ")");
        }
    }

    // ===== SUMMARY =====
    public void printSummary() {
        System.out.println("\n=== SUMMARY: " + name + " ===");
        System.out.println("Type: " + type);
        System.out.println("Location: " + location);
        System.out.println("Guests: " + guests.size());
        System.out.println("Agenda Items: " + agenda.size());
        System.out.println("Tasks: " + tasks.size());
        double total = 0;
        for(Expense e : expenses) total += e.getAmount();
        System.out.println("Total Spent: Rs. " + total);
        System.out.println("Budget Limit: Rs. " + budgetLimit);
    }
}



