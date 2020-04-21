package base.controller;

import base.domain.Message;
import base.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo repo;

    @Autowired
    public MessageController(MessageRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Message> list(){

        return repo.findAll();

    }

    @GetMapping("{id}")
    public Message getOneMessage(@PathVariable("id") Message message){

        return message;

    }

    @PostMapping
    public Message createNew(@RequestBody Message message){

        message.setCreationData(LocalDateTime.now());
        return repo.save(message);

    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDB, @RequestBody Message message){

        BeanUtils.copyProperties(message, messageFromDB, "id");

        return repo.save(messageFromDB);

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){

        repo.delete(message);

    }
}
