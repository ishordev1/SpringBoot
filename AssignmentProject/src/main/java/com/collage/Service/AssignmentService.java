package com.collage.Service;

import java.util.List;

import com.collage.Dto.AssignmentDto;

public interface AssignmentService {
public AssignmentDto createAssignment(AssignmentDto assignmentDto,String clientId);
public AssignmentDto updateAssignment(AssignmentDto assignmentDto, String assignmentId);
public AssignmentDto getAssignment(String assignmentId);
public List<AssignmentDto> allAssignment();
public boolean deleteAssignmentById(String assignmentId);
public AssignmentDto writerAcceptAssignment(String assignmentId, String writerId);

public List<AssignmentDto> getAssignmentByClientId(String clientId);
public List<AssignmentDto> getAssignmentAcceptedByWriter(String writerId);


}
