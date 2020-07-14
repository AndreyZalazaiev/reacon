package andrew.projects.reacon.controllers;

import andrew.projects.reacon.entities.Conversation;
import andrew.projects.reacon.entities.Message;
import andrew.projects.reacon.repos.ConversationRepo;
import andrew.projects.reacon.repos.MessageRepo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {
    @Autowired
    MessageRepo messageRepo;
    @GetMapping
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("messages");
        return modelAndView;
    }
    @GetMapping("/all/{idConversation}")
    public Iterable<Message> getMessages(@PathVariable Integer idConversation )
    {
        return messageRepo.allMessagesInChat(idConversation);
    }
    @PostMapping
    public Message create(@RequestBody Message message)
    {
        messageRepo.save(message);
        return message;
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id)
    {
        messageRepo.findById(id)
                .ifPresent(message -> messageRepo.delete(message));
    }
}
