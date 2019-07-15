package example.demo.controllers;

import example.demo.entities.ExampleEntity;
import example.demo.repo.ExampleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import java.time.Instant;

@Controller
public class ExampleController {
    private final static String client = "client_";
    private static int count = 0;

    @Autowired
    ExampleRepo repository;

    @Autowired
    EntityManager manager;

    @GetMapping(path = "/add")
    public @ResponseBody String addNewEntity (@RequestParam String text, @RequestParam int number) {
        ExampleEntity entity = new ExampleEntity();
        entity.setText(text);
        entity.setNumber(number);
        entity.setModified(false);
        repository.save(entity);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<ExampleEntity> getAllEntities() {
        return repository.findAll();
    }

    @GetMapping(path = "/update")
    public @ResponseBody String updateEntity(@RequestParam long id) {
        long time = Instant.now().getNano();
        ExampleEntity entity = repository.findById(id).orElse(null);
        if (entity == null) return "No entity";
        if (!entity.isModified())
            entity.setModified(true);
        try {
            repository.save(entity);
            System.out.println("Client " + client + count + " saves changes at " + time);
            count++;
        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println("Error at " + time);
        }

        return "OK";
    }
}