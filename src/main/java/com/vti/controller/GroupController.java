package com.vti.controller;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.GroupDTO;
import com.vti.entity.Group;
import com.vti.entity.User;
import com.vti.filter.GroupFilter;
import com.vti.service.IGroupService;
import com.vti.service.IUserService;

@RestController
@RequestMapping(value = "api/v1/groups")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class GroupController {
	@Autowired
	private IGroupService service;
	
	@Autowired
	private IUserService uservice;

	@GetMapping
	public Page<GroupDTO> getAllGroups(Pageable pageable, 
			GroupFilter filter, 
			@RequestParam(name = "search", required = false) String search) {
		
		Page<Group> pageGroups = service.getAllGroups(pageable, filter, search);
		
		Page<GroupDTO> pageGroupDTO = pageGroups.map(new Function<Group, GroupDTO>() {
			@Override
			public GroupDTO apply(Group group) {
				GroupDTO groupDTO = new GroupDTO(group.getGroupID(), group.getName(), group.getMember(), group.getCreateDate(), group.getCreator().getUserID(), group.getCreator());
				return groupDTO;
			}
		});
		return pageGroupDTO;
		
		//test deploy on  heroku
//		List<GroupDTO> lstGroupsDTO = new ArrayList<>();
//		GroupDTO group1 = new GroupDTO((short) 1, "Group 1", 11, null, (short) 2, null);
//		GroupDTO group2 = new GroupDTO((short) 2, "Group 2", 12, null, (short) 3, null);
//		GroupDTO group3 = new GroupDTO((short) 3, "Group 3", 13, null, (short) 4, null);
//		lstGroupsDTO.add(group1);
//		lstGroupsDTO.add(group2);
//		lstGroupsDTO.add(group3);
//		Page<GroupDTO> pageGroupDTO = new PageImpl<>(lstGroupsDTO, pageable, lstGroupsDTO.size());
//		return pageGroupDTO;
		
	}

	@GetMapping(value = "/{id}")
	public GroupDTO getGroupById(@PathVariable(name = "id") short id) {
		GroupDTO groupDTO = service.getGroupById(id).toGroupDTO();
		return groupDTO;
	}

	@GetMapping(value = "name")
	public GroupDTO getGroupByName(@RequestParam(value = "name", required = true) String groupName) {
		if(service.getGroupByName(groupName)==null) return null;
		GroupDTO groupDTO = service.getGroupByName(groupName).toGroupDTO();
		return groupDTO;
	}
	

	@PostMapping()
	public String createGroup(@RequestBody GroupDTO groupDTO) {
		System.out.println(groupDTO.getCreatorID());
		User user = uservice.getUserById(groupDTO.getCreatorID());
		groupDTO.setCreator(user);
		service.createGroup(groupDTO.toGroup());
		return "{\"message\":" + "\"Create Successfully\"" + "}";
	}

	
	@PutMapping(value = "/{id}")
	public String updateGroup(@PathVariable(name = "id") short id, @RequestBody GroupDTO groupDTO) {
		System.out.println(groupDTO.toString());
		Group grouptmp = service.getGroupById(id);
		groupDTO.setCreateDate(grouptmp.getCreateDate());
		groupDTO.setCreator(grouptmp.getCreator());
		Group group = groupDTO.toGroup();
		group.setGroupID(id);
		service.updateGroup(group);
		return "{\"message\":" + "\"Update Successfully\"" + "}";
	}

	@DeleteMapping(value = "/{id}")
	public String deleteGroup(@PathVariable(name = "id") short id) {
		service.deleteGroup(id);
		return "{\"message\":" + "\"Delete Successfully\"" + "}";
	}

	
	@DeleteMapping
	public void deleteGroups(
			@RequestParam(name = "ids") List<Short> ids) {
		service.deleteGroups(ids);
	}
	
	
	public boolean isGroupExistsByID(short id) {
		return service.isGroupExistsByID(id);
	}

	public boolean isGroupExistsByName(String groupName) {
		return service.isGroupExistsByName(groupName);
	}

}
