package base.controller;

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
    public Map<String, String> getMessage(@PathVariable int id){

        return list.get(id);

    }

    @PostMapping
    public Map<String,String> createMessage(@RequestBody Map<String,String> message){
        message.put("id", String.valueOf(count++));

        list.add(message);

        return message;

    }

}
