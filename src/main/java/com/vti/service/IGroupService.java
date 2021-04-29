package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;

import com.vti.entity.Group;
import com.vti.filter.GroupFilter;

public interface IGroupService {
//	public Page<Group> getAllGroups(Pageable pageable, GroupFilter filter, String search);//int page, int pageSize, String fieldName, String type);

	Page<Group> getAllGroups(Pageable pageable, GroupFilter filter, String search);
	
	public Group getGroupById(short id);

	public Group getGroupByName(String groupName);

	public void createGroup(Group group);

	public void updateGroup(Group group);

	public void deleteGroup(short id);

	public boolean isGroupExistsByID(short id);

	boolean isGroupExistsByName(String username);
	
	public void deleteGroups(List<Short> ids);

	
}
