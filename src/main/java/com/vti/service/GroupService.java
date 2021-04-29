package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vti.entity.Group;
import com.vti.filter.GroupFilter;
import com.vti.repository.IGroupRepository;
import com.vti.specification.GroupSpecification;

@Service
public class GroupService implements IGroupService {

	@Autowired
	private IGroupRepository repository;

//	@Override
//	public Page<Group> getAllGroups(Pageable pageable, GroupFilter filter, String search) {
//		GroupSpecificationBuilder specification = new GroupSpecificationBuilder(filter);
//		int page = filter.getPage();
//		int pageSize = filter.getPageSize();
//		String type = filter.getType();
//		if (type==null) {
//			pageable = PageRequest.of(page, pageSize, Sort.by("groupID").descending());
//		} else if (type != null && !type.isEmpty() && type.equals("ASC")) {
//			pageable = PageRequest.of(page, pageSize, Sort.by("groupID").ascending());
//		} else {
//			pageable = PageRequest.of(page, pageSize, Sort.by("groupID").descending());
//		}
//		return null;
//	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Group> getAllGroups(Pageable pageable, GroupFilter filter, String search) {
		GroupSpecification minIdSpecification = new GroupSpecification("groupID", ">=", filter.getMinId());
		
		GroupSpecification maxSpecification = new GroupSpecification("groupID", "<=", filter.getMaxId());
		
		GroupSpecification searchSpecification = new GroupSpecification("name", "LIKE", search);
		
		Specification<Group> where = null;
		
		if (filter.getMinId() != null && filter.getMinId() != 0) {
			where = minIdSpecification;
		}

		if (filter.getMaxId() != null && filter.getMaxId() != 0 ) {
			if (where == null) {
				where = maxSpecification;
			} else {
				where = where.and(maxSpecification);
			}
		}

		if (!(StringUtils.isEmpty(search))) {
			if (where == null) {
				where = searchSpecification;
			} else {
				where = where.and(searchSpecification);
			}
		}
		return repository.findAll(where, pageable);
	}

	@Override
	public Group getGroupById(short id) {
		return repository.findById(id).get();
	}

	@Override
	public Group getGroupByName(String groupName) {
		return repository.findByName(groupName);
	}

	@Override
	public void createGroup(Group group) {
		repository.save(group);
	}

	@Override
	public void updateGroup(Group group) {
		repository.save(group);
	}

	@Override
	public void deleteGroup(short id) {
		repository.deleteById(id);
	}

	@Override
	public boolean isGroupExistsByID(short id) {
		return repository.existsById(id);
	}

	@Override
	public boolean isGroupExistsByName(String username) {
		return repository.existsByName(username);
	}

	@Override
	public void deleteGroups(List<Short> ids) {
		repository.deleteByIds(ids);
	}

}
