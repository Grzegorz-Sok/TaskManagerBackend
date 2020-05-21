package pl.sokolowski.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sokolowski.spring.dao.entity.Employee;
import pl.sokolowski.spring.dao.entity.Meeting;
import pl.sokolowski.spring.manager.MeetingManager;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MeetingController {

    private MeetingManager meetingManager;

    @Autowired
    public MeetingController(MeetingManager meetingManager) { this.meetingManager = meetingManager; }

    @GetMapping("/allMeetings")
    public Iterable<Meeting> getAll() {
        return meetingManager.findAll();
    }

    @GetMapping("/getMeeting")
    public Optional<Meeting> getById(@RequestParam Long id){
        return meetingManager.findById(id);
    }

    @PostMapping("/addMeeting")
    public Meeting addMeeting(@RequestBody Meeting meeting){
        return meetingManager.save(meeting);
    }

    @PutMapping("/updateMeeting")
    public Meeting updateMeeting(@RequestBody Meeting meeting){
        return meetingManager.save(meeting);
    }

    @PutMapping("/addParticipants")
    public Meeting addParticipants(@RequestParam Long id, @RequestBody Employee employee) {
        return meetingManager.addParticipant(id, employee);
    }

    @DeleteMapping("/deleteMeeting")
    public void deleteMeeting(@RequestParam Long id){
        meetingManager.delete(id);
    }
}
