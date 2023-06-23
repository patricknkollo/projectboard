package io.agileintelligence.projectboard.controllerorweb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.agileintelligence.projectboard.domain.ProjectTask;
import io.agileintelligence.projectboard.repository.ProjektTaskRepository;
import io.agileintelligence.projectboard.service.ProjektTaskService;

@RestController
//@Controller
@RequestMapping("/api/board")
@CrossOrigin // pour permettre à REST d'être accessible par d'autres services tels que REACT
public class ProjektTaskController {

	@Autowired
	ProjektTaskService projektTaskService;

	@PostMapping("/save/ppt")
	public ResponseEntity<?> addPTTInDatabase(@Validated @RequestBody ProjectTask projectTask, BindingResult result) {

		//il faudra encore etudier cette partie de plus près
		if(result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<String, String>();

			for (FieldError error: result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());

			}

			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		//-----------------------------------------------------

		ProjectTask newPT = projektTaskService.saveorUpdateProjectTask(projectTask);
		return new ResponseEntity<ProjectTask>(newPT, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public Iterable<ProjectTask> haveAll() {
		return	projektTaskService.getAll();
	}

	/*
	 * @GetMapping("/projecttask/id/{id}") public ResponseEntity<?>
	 * getById(@PathVariable Long id){
	 * 
	 * ProjectTask projectTaskWithId = projektTaskService.getById(id); return new
	 * ResponseEntity<ProjectTask>(projectTaskWithId, HttpStatus.OK);
	 * 
	 * }
	 */
	@DeleteMapping("/{This_id}")
	public ResponseEntity<?> deleteProjectTask(@RequestParam Long this_id) {

		projektTaskService.deleteProjectTask(this_id);
		return new ResponseEntity<String>("task des Projekts wurde gelöscht", HttpStatus.OK);

	}

}