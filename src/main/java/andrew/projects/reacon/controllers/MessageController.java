package andrew.projects.reacon.controllers;

import andrew.projects.reacon.entities.Conversation;
import andrew.projects.reacon.entities.Message;
import andrew.projects.reacon.repos.ConversationRepo;
import andrew.projects.reacon.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {
    @Autowired
    MessageRepo messageRepo;
    @GetMapping(path = "/messages")
    public @ResponseBody
    Iterable<Message> getAllMessagesById() {
        return messageRepo.allMessagesInChat(1);
    }
}
