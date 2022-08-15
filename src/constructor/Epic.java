package constructor;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    protected ArrayList<Integer> subtasks;

    public Epic(String name, String description) {
    super(name, description);
    this.subtasks = new ArrayList<>();
}

    public ArrayList<Integer> getSubtasks() {
        return subtasks;
    }


    public void setSubtasks(ArrayList<Integer> subtasks) {
        this.subtasks = subtasks;
    }


    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask.getId());
    }

    public void removeSubtask(Subtask subtask) {
        subtasks.remove(subtask.getId());
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtasks=" + subtasks +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
