package com.vti.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.Group;
import com.vti.entity.User;

public class GroupDTO {

	private short groupID;

	private String name;

	private int member;

	private Date createDate;

	private short creatorID;

	private User creator;

	public GroupDTO() {
	}

	public GroupDTO(short groupID, String name, int member, Date createDate, short creatorID, User creator) {
		this.groupID = groupID;
		this.name = name;
		this.member = member;
		this.createDate = createDate;
		this.creatorID = creatorID;
		this.creator = creator;
	}

	

	public short getGroupID() {
		return groupID;
	}

	public void setGroupID(short groupID) {
		this.groupID = groupID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	public short getCreatorID() {
		return creatorID;
	}
//
	public void setCreatorID(short creatorID) {
		this.creatorID = creatorID;
	}

//	public User getCreator() {
//		return creator;
//	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Group toGroup() {
		return new Group(groupID, name, member, createDate, creator);
	}

	@Override
	public String toString() {
		return "GroupDTO [groupID=" + groupID + ", name=" + name + ", member=" + member + ", createDate=" + createDate
				+ ", creatorID=" + creatorID + "]";
	}

}
