package andrew.projects.reacon.controllers;

import andrew.projects.reacon.entities.Message;
import andrew.projects.reacon.entities.User;
import andrew.projects.reacon.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @RequestMapping(value = "/all", method = { RequestMethod.GET, RequestMethod.POST })
    public Message create(@RequestParam String text, @RequestParam Integer idConversation, @AuthenticationPrincipal User user) {
        Message m = new Message();
        m.setAttachments(null);
        m.setText(text);
        m.setIdConversation(idConversation);
        m.setIdUser(user.getIdUser());
        m.setSentDate(LocalDateTime.now());
        return messageRepo.save(m);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        messageRepo.findById(id)
                .ifPresent(message -> messageRepo.delete(message));
    }
}
