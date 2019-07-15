package example.demo.repo;

import example.demo.entities.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ExampleRepo extends CrudRepository<ExampleEntity, Long> {

}
