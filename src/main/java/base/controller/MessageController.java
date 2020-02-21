package base.controller;

import base.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private int count = 4;

    private List<Map<String, String>> list = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "first message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "second message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "third message");
        }});
    }};

    @GetMapping
    public List<Map<String,String>> list(){
        return list;

    }

    @GetMapping("{id}")
    public Map<String, String> getMessage(@PathVariable String id){

        return geMessage(id);

    }

    private Map<String, String> geMessage(@PathVariable String id) {
        return list.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String,String> createMessage(@RequestBody Map<String,String> message){
        message.put("id", String.valueOf(count++));

        list.add(message);

        return message;

    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message){

        Map<String, String> messageFDB = getMessage(id);

        messageFDB.putAll(message);
        messageFDB.put("id", id);

        return messageFDB;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){

        Map<String, String> message = getMessage(id);

        list.remove(message);


    }

}
