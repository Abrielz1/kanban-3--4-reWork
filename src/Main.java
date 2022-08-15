import constructor.Epic;
import constructor.Subtask;
import constructor.Task;
import constructor.TaskManager;
import status.Status;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
//============= Тут создаём задачи ==================================================================
       Task task0 = new Task("Купить сливочное масло", "Пойти в магазин");
//============= Тут создаём комплексные задачи ======================================================
        Epic epic0 = new Epic("Купить коте корм", "найти где есть нужный корм");
        Epic epic1 = new Epic("Купить вафли", "купить вафли");
//============= Тут создаём подзадачи ===============================================================
        Subtask s0 = new Subtask("Купить коте корм", "найти продавца", epic0.getId());
        Subtask s1 = new Subtask("Купить коте корм", "заказать", epic0.getId());
     Subtask s2 = new Subtask("Купить коте корм", "получить", epic0.getId());
     Subtask s3 = new Subtask("Купить вафли", "заказать вафли", epic1.getId() );

//========== Тут создаём методы простых задач =======================================================
        taskManager.add(task0);

        taskManager.taskPrint();
        taskManager.updateTask(task0);

        taskManager.taskPrint();

        taskManager.updateTaskStatus(task0);
        taskManager.taskPrint();

        taskManager.updateTaskStatus(task0);
        taskManager.taskPrint();

        taskManager.getTask(task0.getId());

        taskManager.getAllTask(task0);

        taskManager.removeTask(1);
//========== Тут создаём методы комплексных простых задач =======================================================

        taskManager.add(epic0);
        taskManager.add(s0);
        taskManager.add(s1);
        taskManager.add(s2);
        taskManager.add(s3);
        taskManager.getEpicById(s0.getEpicId());
        taskManager.getSubtaskById(s0.getId());
        taskManager.getSubtaskById(s1.getId());
        taskManager.getSubtaskById(s2.getId());
     //   s0.setStatus(Status.DONE);
     //   s1.setStatus(Status.IN_PROGRESS);
     //   s2.setStatus(Status.IN_PROGRESS);
        taskManager.updater(s0);
        taskManager.updater(s1);
        taskManager.updater(s2);
        taskManager.getSubtaskById(s0.getId());
        taskManager.getSubtaskById(s1.getId());
        taskManager.getSubtaskById(s2.getId());
        taskManager.updater(epic0);
        //taskManager.epicUpdater(epic0);

        taskManager.getEpicById(s0.getEpicId());
    }
}
