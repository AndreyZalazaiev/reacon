package andrew.projects.reacon.controllers;

import andrew.projects.reacon.entities.Conversation;
import andrew.projects.reacon.entities.User;
import andrew.projects.reacon.repos.ConversationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("conversations")
public class ConversationController {
    @Autowired
    ConversationRepo conversationRepo;

    @GetMapping("/all/{idUser}")
    public
    Iterable<Conversation> getAllChats(@PathVariable String idUser) {
        return conversationRepo.findAllChatsForUserById(idUser);
    }

    @GetMapping("/users/{idConversation}")
    public
    Iterable<User> getAllUsersInChat(@PathVariable Integer idConversation) {
        return conversationRepo.findAllUsersInChat(idConversation);
    }
    @GetMapping("/search/{groupName}")
    public Iterable<Conversation> search(@PathVariable String groupName){
         return conversationRepo.search(groupName);
    }

  /*  @GetMapping("/last/{idConversation}")
    public @ResponseBody
    Iterable<User> lastMessageInChat(@PathVariable Integer idConversation) {
        return conversationRepo.findLastMessageInChat(idConversation);
    }*/


}
