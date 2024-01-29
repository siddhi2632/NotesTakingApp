package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Notes;
import com.example.demo.entity.User;
import com.example.demo.repository.NotesRepository;

@Service
public class NotesServiceImpl implements NotesService
{

	@Autowired
	private NotesRepository notesRepository;
	
	@Override
	public Notes saveNotes(Notes notes) {
		// TODO Auto-generated method stub
		
		return notesRepository.save(notes);
	}

	@Override
	public Notes getNotesById(int id) {
		// TODO Auto-generated method stub
		return notesRepository.findById(id).get();
	}

	@Override
	public List<Notes> getnotesByUser(User user) {
		// TODO Auto-generated method stub
	
		return notesRepository.findByUser(user);
	}

	@Override
	public Notes updateNotes(Notes notes) {
		// TODO Auto-generated method stub
		return notesRepository.save(notes);
	}

	@Override
	public boolean deleteNotes(int id) {
		// TODO Auto-generated method stub
		Notes notes = notesRepository.findById(id).get();
		if(notes != null)
		{
			notesRepository.delete(notes);
			return true;
		}
		else
			return false;
	}

}
