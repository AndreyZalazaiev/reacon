package andrew.projects.reacon.controllers;

import andrew.projects.reacon.entities.Message;
import andrew.projects.reacon.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("messages")
public class MessageController {
    @Autowired
    MessageRepo messageRepo;

    @GetMapping("/all/{idConversation}")
    public @ResponseBody
    Iterable<Message> getMessages(@PathVariable Integer idConversation) {
        return messageRepo.allMessagesInChat(idConversation);
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        messageRepo.save(message);
        return message;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        messageRepo.findById(id)
                .ifPresent(message -> messageRepo.delete(message));
    }
}
