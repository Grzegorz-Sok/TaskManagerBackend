package pl.sokolowski.spring.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sokolowski.spring.dao.entity.Employee;
import pl.sokolowski.spring.dao.entity.Task;
import pl.sokolowski.spring.dao.repository.EmployeeRepo;

import java.util.Optional;

@Service
public class EmployeeManager {

    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeManager(EmployeeRepo employeeRepo) { this.employeeRepo = employeeRepo; }

    public Optional<Employee> findById(Long id){
        return employeeRepo.findById(id);
    }

    public Iterable<Employee> findAll(){
        return employeeRepo.findAll();
    }

    public Employee save(Employee employee){
        return employeeRepo.save(employee);
    }

    public void delete (Long id){
        employeeRepo.deleteById(id);
    }

    public Employee assignTask(Long id, Task task){
        Optional <Employee> employee = employeeRepo.findById(id);
        employee.ifPresent(employee1 -> employee1.setTask(task));
        return employeeRepo.save(employee.orElseThrow(IllegalArgumentException::new));
    }
}
