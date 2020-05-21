package pl.sokolowski.spring.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sokolowski.spring.dao.entity.Employee;
import pl.sokolowski.spring.dao.entity.Meeting;
import pl.sokolowski.spring.dao.repository.MeetingRepo;

import java.util.Optional;

@Service
public class MeetingManager {

    private MeetingRepo meetingRepo;

    @Autowired
    public MeetingManager(MeetingRepo meetingRepo) { this.meetingRepo = meetingRepo; }

    public Optional<Meeting> findById (Long id){
        return meetingRepo.findById(id);
    }

    public Iterable<Meeting> findAll (){
        return meetingRepo.findAll();
    }

    public Meeting save(Meeting meeting){
        return meetingRepo.save(meeting);
    }

    public void delete(Long id){
        meetingRepo.deleteById(id);
    }

    public Meeting addParticipant(Long id, Employee employee){
        Optional <Meeting> meeting = meetingRepo.findById(id);
        meeting.ifPresent( meeting1 -> meeting1.addParticipant(employee.toString()));
        return meetingRepo.save(meeting.orElseThrow(IllegalArgumentException::new));
    }
}
