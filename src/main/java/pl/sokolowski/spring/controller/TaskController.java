package pl.sokolowski.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sokolowski.spring.dao.entity.Employee;
import pl.sokolowski.spring.dao.entity.Meeting;
import pl.sokolowski.spring.dao.entity.Task;
import pl.sokolowski.spring.manager.TaskManager;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private TaskManager taskManager;

    @Autowired
    public TaskController(TaskManager taskManager) { this.taskManager = taskManager; }

    @GetMapping("/allTasks")
    public Iterable<Task> getAll() {
        return taskManager.findAll();
    }

    @GetMapping("/allUnfinishedTasks")
    public Iterable<Task> getAllUnfinished() {
        return taskManager.findAllUnfinished();
    }

    @GetMapping("/allSortedByDate")
    public Iterable<Task> getSortedByDate() {
        return taskManager.findAllSortedByDate();
    }

    @GetMapping("/allSortedByPriority")
    public Iterable<Task> getSortedByPriority() {
        return taskManager.findAllSortedByPriority();
    }

    @GetMapping("/getTask")
    public Optional<Task> getById(@RequestParam Long id){
        return taskManager.findById(id);
    }

    @PostMapping("/addTask")
    public Task addTask(@RequestBody Task task){
        return taskManager.save(task);
    }

    @PutMapping("/assignMeeting")
    public Task assignMeeting(@RequestParam Long id, @RequestBody Meeting meeting){
        return taskManager.assignMeeting(id, meeting);
    }

    @PutMapping("/addDoer")
    public Task addDoer(@RequestParam Long id, @RequestBody Employee employee){
        return taskManager.addDoer(id, employee);
    }

    @PutMapping("/updateTask")
    public Task updateTask(@RequestBody Task task){
        return taskManager.save(task);
    }

    @DeleteMapping("/deleteTask")
    public void deleteTask(@RequestParam Long id){
        taskManager.delete(id);
    }
}
