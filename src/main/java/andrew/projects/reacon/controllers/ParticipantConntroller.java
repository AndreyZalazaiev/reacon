package andrew.projects.reacon.controllers;

import andrew.projects.reacon.entities.Participant;
import andrew.projects.reacon.repos.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    @DeleteMapping("{id}")
            public void delete(@PathVariable Integer id)
    {
        participantRepo.findById(id)
                .ifPresent(participant -> participantRepo.delete(participant));
    }
}
