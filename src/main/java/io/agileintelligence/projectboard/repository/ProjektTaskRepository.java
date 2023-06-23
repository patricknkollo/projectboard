package io.agileintelligence.projectboard.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.agileintelligence.projectboard.domain.ProjectTask;

@Repository
public interface ProjektTaskRepository extends CrudRepository<ProjectTask, Long> {

	 //@Transactional
	 // @Query("select pt from ProjectTask pt where pt.id = :id")
	public ProjectTask getById(Long id);
}
