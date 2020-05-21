package pl.sokolowski.spring.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sokolowski.spring.dao.entity.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {
}
