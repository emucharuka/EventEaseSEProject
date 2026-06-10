import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Event> events = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Create Event");
            System.out.println("2. Select Event to Manage");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    createEventMenu();
                    break;
                case "2":
                    selectEventMenu();
                    break;
                case "3":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static void createEventMenu() {
        System.out.print("Enter Event Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Location: ");
        String location = sc.nextLine();

        System.out.println("Select Event Type:");
        System.out.println("1. Music");
        System.out.println("2. Conference");
        System.out.println("3. Other");
        System.out.print("Enter choice: ");
        String typeChoice = sc.nextLine();

        Event event;
        System.out.println("Enter event ID :");
        String id = sc.nextLine();

        switch (typeChoice) {
            case "1":
                event = new MusicEvent(id, name, location);
                break;
            case "2":
                event = new ConferenceEvent(id, name, location);
                break;
            case "3":
                System.out.print("Enter Custom Type: ");
                String customType = sc.nextLine();
                event = new OtherEvent(id, name, customType, location);
                break;
            default:
                System.out.println("Invalid choice, defaulting to Other.");
                event = new OtherEvent(id, name, "Other", location);
        }

        events.add(event);
        System.out.println("Event Created! ID: " + id);
    }

    private static void selectEventMenu() {
        if (events.isEmpty()) {
            System.out.println("No events found.");
            return;
        }

        System.out.println("\n--- AVAILABLE EVENTS ---");
        for (Event e : events) {
            System.out.println("ID: " + e.getId() + " | Name: " + e.getName());
        }

        System.out.print("Enter Event ID to manage: ");
        String id = sc.nextLine();
        Event selected = null;
        for (Event e : events) if (e.getId().equalsIgnoreCase(id)) selected = e;

        if (selected == null) {
            System.out.println("Event not found.");
            return;
        }
        manageEventMenu(selected);
    }

    private static void manageEventMenu(Event event) {
        while (true) {
            System.out.println("\n--- EVENT DASHBOARD: " + event.getName() + " ---");
            System.out.println("1. Guest List");
            System.out.println("2. Agenda / Schedule");
            System.out.println("3. Budget Tracker");
            System.out.println("4. Task Manager");
            System.out.println("5. View Summary");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    guestMenu(event);
                    break;
                case "2":
                    agendaMenu(event);
                    break;
                case "3":
                    budgetMenu(event);
                    break;
                case "4":
                    taskMenu(event);
                    break;
                case "5":
                    event.printSummary();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    // ===================== GUEST MENU =====================
    private static void guestMenu(Event event) {
        while (true) {
            System.out.println("\n--- GUEST LIST ---");
            System.out.println("1. Add Guest");
            System.out.println("2. View Guests");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Status (PENDING/CONFIRMED): ");
                    String status = sc.nextLine();
                    event.addGuest(name, status);
                    System.out.println("Guest added.");
                    break;
                case "2":
                    System.out.println("Name | Status");
                    for (Guest g : event.getGuests()) {
                        System.out.println(g.getName() + " | " + g.getStatus());
                    }
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    // ===================== AGENDA MENU =====================
    private static void agendaMenu(Event event) {
        while (true) {
            System.out.println("\n--- AGENDA / SCHEDULE ---");
            System.out.println("1. Add Item");
            System.out.println("2. View Schedule");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Time: ");
                    String time = sc.nextLine();
                    System.out.print("Activity: ");
                    String activity = sc.nextLine();
                    event.addAgendaItem(time, activity);
                    System.out.println("Agenda item added.");
                    break;
                case "2":
                    System.out.println("Time | Activity");
                    for (AgendaItem a : event.getAgenda()) {
                        System.out.println(a.getTime() + " | " + a.getActivity());
                    }
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    // ===================== BUDGET MENU =====================
    private static void budgetMenu(Event event) {
        while (true) {
            System.out.println("\n--- BUDGET ---");
            System.out.println("1. Set Limit");
            System.out.println("2. Add Expense");
            System.out.println("3. Report");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter budget limit: ");
                    double limit = Double.parseDouble(sc.nextLine());
                    event.setBudgetLimit(limit);
                    System.out.println("Budget set.");
                    break;
                case "2":
                    System.out.print("Expense name: ");
                    String name = sc.nextLine();
                    System.out.print("Amount: ");
                    double amt = Double.parseDouble(sc.nextLine());
                    event.addExpense(name, amt);
                    System.out.println("Expense added.");
                    break;
                case "3":
                    System.out.println("Name | Amount");
                    double total = 0;
                    for (Expense e : event.getExpenses()) {
                        System.out.println(e.getName() + " | " + e.getAmount());
                        total += e.getAmount();
                    }
                    System.out.println("Total spent: Rs. " + total);
                    System.out.println("Remaining: Rs. " + (event.getBudgetLimit() - total));
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }
    // ===================== TASK MENU =====================
    private static void taskMenu(Event event) {
        while(true) {
            System.out.println("\n--- TASKS ---");
            System.out.println("1. Add Task");
            System.out.println("2. Complete Task");
            System.out.println("3. View All");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();
            switch(choice) {
                case "1":
                    System.out.print("Task description: "); String desc = sc.nextLine();
                    System.out.print("Assignee: "); String assignee = sc.nextLine();
                    event.addTask(desc, assignee); System.out.println("Task added."); break;
                case "2":
                    event.viewTasks();
                    System.out.print("Enter Task ID to mark complete: ");
                    int id = Integer.parseInt(sc.nextLine());
                    event.getTasks().get(id).setComplete(true);
                    System.out.println("Task marked complete."); break;
                case "3": event.viewTasks(); break;
                case "4": return;
                default: System.out.println("Invalid input.");
            }
        }
    }
}

