package andrew.projects.reacon.controllers;

import andrew.projects.reacon.entities.Participant;
import andrew.projects.reacon.entities.User;
import andrew.projects.reacon.repos.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("participants")
public class ParticipantConntroller {
    @Autowired
    ParticipantRepo participantRepo;
    @GetMapping("{idConversation}")
    public Iterable<Participant>  getAllParticipants(@PathVariable Integer idConversation)
    {
        return participantRepo.allParticipantsInChat(idConversation);
    }
    @GetMapping("/delete/{idConversation}")
    public Participant  delete(@PathVariable Integer idConversation, @AuthenticationPrincipal User user){
       return participantRepo.deleteByIdConversationAndIdUser(idConversation,user.getIdUser());
    }
    @GetMapping("/add/{idConversation}")
    public Participant add(@PathVariable Integer idConversation, @AuthenticationPrincipal User user){
        Participant p = new Participant();
        p.setIdUser(user.getIdUser());
        p.setIdConversation(idConversation);
        return participantRepo.save(p);
    }
}
