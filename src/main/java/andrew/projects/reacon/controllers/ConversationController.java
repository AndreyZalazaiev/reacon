package andrew.projects.reacon.controllers;

import andrew.projects.reacon.entities.Conversation;
import andrew.projects.reacon.entities.User;
import andrew.projects.reacon.repos.ConversationRepo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ConversationController {
    @Autowired
    ConversationRepo conversationRepo;
    @GetMapping(path = "/chats")
    public @ResponseBody
    Iterable<Conversation> getAllChats() {
        return conversationRepo.findAllChatsForUserById(1);
    }
    @GetMapping(path = "/users")
    public @ResponseBody
    Iterable<User> getAllUsersInChat() {
        return conversationRepo.findAllUsersInChat(2);
    }

}
