package pl.sokolowski.spring.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Task implements Comparable<Task> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private int priority;
    private String description;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT")
    private Date deadline;

    private String type;
    private String status;
    private String doers;

    @OneToOne
    private Meeting meeting;

    public Task(){}

    public Task(int priority, String description, Date deadline, String type, String status, String doers, Meeting meeting) {
        this.priority = priority;
        this.description = description;
        this.deadline = deadline;
        this.type = type;
        this.status = status;
        this.doers = doers;
        this.meeting = meeting;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoers() {
        return doers;
    }

    public void addDoer(String doer) {
        if (this.doers == null)
            this.doers = doer;
        else if (!this.doers.contains(doer))
            this.doers += ("; "+ doer);
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    @Override
    public int compareTo(Task task) {
        if (getDeadline() == null || task.getDeadline() == null)
                return 0;
        return getDeadline().compareTo(task.getDeadline());
    }
}
