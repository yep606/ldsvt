package base.controller;

import base.domain.Message;
import base.domain.Views;
import base.dto.EventType;
import base.dto.ObjectType;
import base.repo.MessageRepo;
import base.util.WSender;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo repo;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(MessageRepo repo, WSender wsSender) {
        this.repo = repo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOneMessage(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message createNew(@RequestBody Message message) {
        message.setCreationData(LocalDateTime.now());

        Message newMessage = repo.save(message);

        wsSender.accept(EventType.CREATE, newMessage);

        return newMessage;
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDB, @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDB, "id");

        Message updatedMessage = repo.save(messageFromDB);

        wsSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        repo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }

}
