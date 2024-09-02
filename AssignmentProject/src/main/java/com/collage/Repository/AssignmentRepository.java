package com.collage.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collage.Dto.AssignmentDto;
import com.collage.Entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, String> {
	List<Assignment> findByClientId(String clientId);
	List<Assignment> findByWriterId(String writerId);

}
