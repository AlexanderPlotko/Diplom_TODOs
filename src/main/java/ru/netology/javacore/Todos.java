package ru.netology.javacore;

import java.util.*;

public class Todos {
    protected List<String> todos; //объект класса

    public Todos() {
        this.todos = new ArrayList<>();
    }

    public void addTask(String task) { //добавить задачу
        todos.add(task);
        System.out.println("задача добавлена успешно");

    }

    public void removeTask(String task) { //удалить задачу
        if (todos.contains(task)) { //проверяем содержит ли список введенную задачу
            todos.remove(task); // если ДА, удаляем ее
            System.out.println("задача удалена из списка");
        } else {
            System.out.println("данной задачи нет в списке");
        }
    }

    public String getAllTasks() { //Получить все задачи
        Collections.sort(todos); //сортируем задачи по порядку
        StringBuilder sb = new StringBuilder();
        for (String todo : todos) {
            sb.append(todo);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

}
