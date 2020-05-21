package pl.sokolowski.spring.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sokolowski.spring.dao.entity.Status;
import pl.sokolowski.spring.dao.entity.Employee;
import pl.sokolowski.spring.dao.entity.Meeting;
import pl.sokolowski.spring.dao.entity.Task;
import pl.sokolowski.spring.dao.repository.TaskRepo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class TaskManager {

    private TaskRepo taskRepo;

    @Autowired
    public TaskManager(TaskRepo taskRepo) { this.taskRepo = taskRepo; }

    public Optional<Task> findById(Long id){
        return taskRepo.findById(id);
    }

    public Iterable<Task> findAll(){
        return taskRepo.findAll();
    }

    public Iterable<Task> findAllUnfinished(){
        Iterable<Task> unfinishedTasks = taskRepo.findAll();
        Iterator<Task> iterator = unfinishedTasks.iterator();
            while(iterator.hasNext()) {
                Task next = iterator.next();
                    if (compareDate(next) || compareStatus(next))
                        iterator.remove();
            }
        return unfinishedTasks;
    }

    public Iterable<Task> findAllSortedByDate(){
        Iterable<Task> unsortedIterable = taskRepo.findAll();
        ArrayList<Task> sortedList= new ArrayList<>();
        unsortedIterable.forEach(sortedList::add);
        Collections.sort(sortedList);
        return sortedList;
    }
    public Iterable<Task> findAllSortedByPriority() {
        Iterable<Task> unsortedIterable = taskRepo.findAll();
        ArrayList<Task> sortedList = new ArrayList<>();
        unsortedIterable.forEach(sortedList::add);
        sortedList.sort(Comparator.comparing(Task::getPriority));
        return sortedList;
    }

    public Task assignMeeting(Long id, Meeting meeting){
        Task task = taskRepo.findById(id).get();
        task.setMeeting(meeting);
        return taskRepo.save(task);
    }

    public Task addDoer(Long id, Employee employee){
        Optional <Task> task = taskRepo.findById(id);
        task.ifPresent( task1 -> task1.addDoer(employee.toString()));
        return taskRepo.save(task.orElseThrow(IllegalArgumentException::new));
    }

    public Task save(Task task){
        return taskRepo.save(task);
    }

    public void delete(Long id){
        taskRepo.deleteById(id);
    }

    public boolean compareDate(Task task){
        return task.getDeadline().after(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
    }

    public boolean compareStatus(Task task) {
        return task.getStatus().toUpperCase().equals(Status.DONE.toString());
    }
}
