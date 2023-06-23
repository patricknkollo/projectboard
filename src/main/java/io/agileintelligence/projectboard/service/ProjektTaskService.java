package io.agileintelligence.projectboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import io.agileintelligence.projectboard.domain.ProjectTask;
import io.agileintelligence.projectboard.repository.ProjektTaskRepository;

@Service
public class ProjektTaskService {

	@Autowired
	ProjektTaskRepository projektTaskRepository;

	public ProjectTask saveorUpdateProjectTask(ProjectTask projectTask) {

		if(projectTask.getStatus()==null || projectTask.getStatus()=="") {
			projectTask.setStatus("TO_DO");
		}
		return projektTaskRepository.save(projectTask);
	}

	public Iterable<ProjectTask> getAll() {

		return projektTaskRepository.findAll();
	}

	public ProjectTask getById(Long id) {

		//return projektTaskRepository.findById(null);
		return projektTaskRepository.getById(id);
	}
	
	public void deleteProjectTask( Long id) {
		
		projektTaskRepository.deleteById(id);
	
	}

}
