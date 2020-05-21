package pl.sokolowski.spring.dao.entity;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String role;

    @OneToOne
    private Task task;

    public Employee() {}

    public Employee(String name, String surname, String role, Task task) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        if (task.getType() != null && task.getType().equals(this.role))
            this.task = task;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getSurname() + ", id: " + this.getId();
    }
}
