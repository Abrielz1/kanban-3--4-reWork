package constructor;

import status.Status;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {

    //todo сделать мапы и методы:
    //todo 1. 3 хаш мапы. +
    //todo 2. сделать методы гет ид, сет ид, сделать мапы финал и гет сет методы.


    private int idCounter;

    private final Map<Integer, Task> taskDump = new HashMap<>();

    private final Map<Integer, Epic> epicDump = new HashMap<>();

    private final Map<Integer, Subtask> subtaskDump = new HashMap<>();


    public int getIdCounter(int idCounter) {
        return this.idCounter;
    }


    public void setIdCounter(int idCounter) {
        this.idCounter = idCounter;
    }

    public Map<Integer, Task> getTaskDump() {
        return taskDump;
    }


    public Map<Integer, Epic> getEpicDump() {
        return epicDump;
    }


    public Map<Integer, Subtask> getSubtaskDump() {
        return subtaskDump;
    }


    //=========================== Всё связанно только с простой задачей.=================================

    // Метод добавления одиночной задачи:

    public Task add(Task task) {
        task.setId(++idCounter);
        task.setStatus(Status.NEW);
        taskDump.put(task.getId(), task);
        return task;
    }

    //Метод обновления одиночной задачи:

    public Task updateTask(Task task) { //метод по тз
        taskDump.put(task.getId(), task);
        System.out.println("Задача обновлена.");
        return task;
    }

    //Метод удаления одиночной задачи:

    public void removeTask(int id) {
        System.out.println("Задача №" + id + " удалена.");
        taskDump.remove(id);
    }

    //Метод вызова по id одиночной задачи, с занесением в историю:

    public Task getTask(int id) {
        Task task = taskDump.get(id);
        System.out.println(taskDump.values());
        //historyManager.add(task);
        return task;
    }

    //Метод обновления статус одиночной задачи: (опционально)? хотел посмотреть, как можно сделать.

    public void updateTaskStatus(Task task) {
        Task currentTask = taskDump.get(task.getId());
        if (currentTask.getStatus().equals(Status.NEW)) {
            currentTask.setStatus(Status.IN_PROGRESS);
        } else if (currentTask.getStatus().equals(Status.IN_PROGRESS)) {
            currentTask.setStatus(Status.DONE);
        }
        taskDump.put(task.getId(), task);
        System.out.println("Задача обновлена.");
    }

    //Метод распечатки списка простых задач (опционально!)

    public void taskPrint() {
        for (Integer prnt : getTaskDump().keySet()) {
            for (Task prn : getTaskDump().values()) {
                System.out.println(prn);
            }
        }
    }

    //Метод выдачи списка простых задач (опционально!)

    public Task getAllTask(Task task) {
        System.out.println(taskDump.values());
        return task;
    }

//================= Эпики и саб эпики =========================================
    //todo 3. сделать методы добавления задач для, епик(+) и саб таск, через перегрузку методов и увеличивая ид, каждый раз. +

    //Метод создание эпика:

    public Epic add(Epic epic) {
        epic.setId(++idCounter);
        epicDump.put(epic.getId(), epic);
        for (Subtask subtask : subtaskDump.values()) {
            if (epic.getId() == subtask.getEpicId()) {
                updater(epicDump.get(subtask.getEpicId()));
            }
        }
        System.out.println("Эпик №" + epic.getId() + " создан");
        return epic;
    }

    //Метод создание сабтаска:

    public Subtask add(Subtask subtask) {
        subtask.setId(idCounter++);
        subtaskDump.put(subtask.getId(), subtask);
        for (Epic epic : epicDump.values()) {
            if (epic.getId() == subtask.getEpicId()) {
                epic.getSubtasks().add(subtask.getId());
            }
        }
        if (epicDump.containsKey(subtask.getEpicId())) {
            updater(epicDump.get(subtask.getEpicId()));
            //epicUpdater(epicDump.get(subtask.getEpicId()));
        }
        System.out.println("Сабтаск №" + idCounter + " принадлежащий эпику №" + subtask.getEpicId() + " создан");
        return subtask;
    }

    //todo 4. сделать методы обновления задач и сделать метод обновления(пересчёта статусов) вызывание из них.

    //Метод обновления сабтаска

    public void updater(Subtask subtask) {
        Subtask currentSubtask = subtaskDump.get(subtask.getId());
        if (currentSubtask.getStatus().equals(Status.NEW)) {
            currentSubtask.setStatus(Status.IN_PROGRESS);
        } else if (currentSubtask.getStatus().equals(Status.IN_PROGRESS)) {
            currentSubtask.setStatus(Status.DONE);
        }
        //subtaskDump.put(subtask.getId(), subtask);
        epicUpdater(epicDump.get(subtask.getEpicId()));
    }
    //Метод обновления епика, через статусы его сабэпиков

    public Epic updater(Epic epic) {
        if (epicDump.isEmpty()) {
            epic.setStatus(Status.NEW);
            return epic;
        } else {
        }
        for (Integer integer : subtaskDump.keySet()) {
            if (subtaskDump.get(integer).getStatus() == Status.DONE) {
                epic.setStatus(Status.DONE);
                break;
            } else if (subtaskDump.get(integer).getStatus() == Status.IN_PROGRESS) {
                epic.setStatus(Status.IN_PROGRESS);
                return epic;
            } else if (epic.getStatus() == Status.DONE && subtaskDump.get(integer).getStatus() == Status.NEW) {
                epic.setStatus(Status.IN_PROGRESS);
                return epic;
            }
        }
        System.out.println("Эпик обновлён");
        return epic;
    }

// Альтернативный оБновлятор
    public void epicUpdater(Epic epic) {
        if (subtaskDump.isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        } else {
            for (Integer integer : epicDump.keySet()) {
                for (Epic val : epicDump.values()) {
                    if (val.status.equals(Status.NEW)) {
                        return;
                    } else if (val.status.equals(subtaskDump.get(integer).getStatus() != Status.NEW)) {
                        epic.setStatus(Status.IN_PROGRESS);
                        return;
                    } else {
                        epic.setStatus(Status.DONE);
                    }
                }
            }
        }
        System.out.println("Эпик обновлён");
    }

    //Метод считывания эпика по Id, с занесением в историю:

    public Epic getEpicById(int id) {
        Epic epic = epicDump.get(id);
        System.out.println(epicDump.values());
        // метод занесения в историю
        return epic;
    }

    //Метод считывания сабэпика по Id, с занесением в историю:

    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtaskDump.get(id);
        System.out.println(subtask);
        // метод занесения в историю
        return subtask;
    }

    //todo 5. сделать метод удаления епика, с саб тасками.
    //todo 6. сделать метод удаления саб епика из епика по идентификатору.
    //todo 7. сделать метод удаления таска по идентификатору
    //todo 8. сделать метод удаления всех задач по идентификатору
    //todo 9. Получение по идентификатору. +
    //todo 10. Получение списка всех подзадач определённого эпика.

//todo  4.1 Менеджер сам не выбирает статус для задачи.
//  Информация о нём приходит менеджеру вместе с информацией о самой задаче.
//  По этим данным в одних случаях он будет сохранять статус, в других будет рассчитывать.

//todo 4.2 Для эпиков:
//todo если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW.
//todo если все подзадачи имеют статус DONE, то и эпик считается завершённым — со статусом DONE.
//todo во всех остальных случаях статус должен быть IN_PROGRESS.


}