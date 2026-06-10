public class Task {
    private String description;
    private String assignee;
    private boolean complete;

    public Task(String description, String assignee) {
        this.description = description;
        this.assignee = assignee;
        this.complete = false;
    }

    public String getDescription() {
        return description;
    }
    public String getAssignee() {
        return assignee;
    }
    public boolean isComplete() {
        return complete;
    }
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
