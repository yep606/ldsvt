package base.controller;

import base.domain.Message;
import base.domain.Views;
import base.repo.MessageRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return repo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
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


    @MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    public Message change(Message message)
    {
        return repo.save(message);
    }
}
