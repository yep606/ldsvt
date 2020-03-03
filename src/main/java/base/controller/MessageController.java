package base.controller;

import base.domain.Message;
import base.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    public List<Message> list(){
        return messageRepo.findAll();

    }

    @GetMapping("{id}")
    public Message getMessage(@PathVariable("id") Message message){

        return message;

    }

    @PostMapping
    public Message createMessage(@RequestBody Message message){

        return messageRepo.save(message);

    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb,
                          @RequestBody Message message){

        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){

       messageRepo.delete(message);


    }

}
