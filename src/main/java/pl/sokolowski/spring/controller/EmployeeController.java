package pl.sokolowski.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sokolowski.spring.dao.entity.Employee;
import pl.sokolowski.spring.dao.entity.Task;
import pl.sokolowski.spring.manager.EmployeeManager;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private EmployeeManager employeeManager;

    @Autowired
    public EmployeeController(EmployeeManager employeeManager) { this.employeeManager = employeeManager; }

    @GetMapping("/allEmployees")
    public Iterable<Employee> getAll() {
        return employeeManager.findAll();
    }

    @GetMapping("/getEmployee")
    public Optional<Employee> getById(@RequestParam Long id){
        return employeeManager.findById(id);
    }

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeManager.save(employee);
    }

    @PutMapping("/assignTask")
    public Employee updateEmployee(@RequestParam Long id, @RequestBody Task task){
        return employeeManager.assignTask(id, task);
    }

    @DeleteMapping("/deleteEmployee")
    public void deleteEmployee(@RequestParam Long id){
        employeeManager.delete(id);
    }
}
