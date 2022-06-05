package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    Todos todos;
    int port;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port);) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //прием данных
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) { //вывод данных
                    String jsonIn = in.readLine(); //в json записываем ту строку которая пришла из BufferedReader
                    String output = checkJson(jsonIn, todos);

                    out.println(output);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private String checkJson(String jsonIn, Todos todos) {
        JsonObject jsonObject = new Gson().fromJson(jsonIn, JsonObject.class); //читаем строку Json
        String type = jsonObject.get("type").getAsString(); //записываем ключ
        String task = jsonObject.get("task").getAsString(); //записываем значение
        if (type.equals("ADD")) { //если ADD
            todos.addTask(task);  //добавляем задачу через метод
        } else if (type.equals("REMOVE")) {
            todos.removeTask(task);
        }
        todos.getAllTasks();
        return todos.getAllTasks();
    }
}
