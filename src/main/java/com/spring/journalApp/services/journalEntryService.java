package com.spring.journalApp.services;

import com.spring.journalApp.entity.JournalEntry;
import com.spring.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import com.spring.journalApp.repository.JournalEntryRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
public class journalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private userService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Something has gone wrong in the saving process",e);
        }

    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
            boolean remove=false;
        try {
            User user = userService.findByUserName(userName);
            remove = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(remove){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);

            }
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error has occured while deleting journal",e);
        }
        return remove;
    }

}
