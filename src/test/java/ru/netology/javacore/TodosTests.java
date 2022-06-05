package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class TodosTests {
    Todos todos = new Todos();

    @ParameterizedTest
    @ValueSource(strings = {"гулять", "учеба", "зарядка"})
    public void testADD(String st) {
        todos.addTask(st);
        String expected = todos.getAllTasks();
        Assertions.assertEquals(expected, st);
    }

    @Test
    public void testREMOVE() {
        todos.addTask("читать");
        todos.addTask("играть");
        todos.addTask("спать");
        todos.removeTask("читать");
        String expected = todos.getAllTasks();
        String actual = "играть спать";
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("methodChecks")
    public void testGetAllTasks(String todoList) {
        todos.addTask("гулять");
        todos.addTask("читать");
        todos.addTask("играть");
        todos.addTask("писать");
        String result = todos.getAllTasks();
        Assertions.assertEquals(todoList, result);
    }
    public static Stream<Arguments> methodChecks() {
        return Stream.of(
                Arguments.of("гулять играть писать читать")
        );
    }




}
