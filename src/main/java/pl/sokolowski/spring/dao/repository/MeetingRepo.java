package pl.sokolowski.spring.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sokolowski.spring.dao.entity.Meeting;

@Repository
public interface MeetingRepo extends CrudRepository<Meeting, Long> {
}
