package com.vti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.vti.dto.GroupDTO;

@Entity
@Table(name = "Group")
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "GroupID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short groupID;

	@Column(name = "GroupName", length = 50, nullable = false, unique = true)
	private String name;

	@Column(name = "`Member`")
	private int member;

	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CreatorID", nullable = false)
	private User creator;

	public Group() {
	}

	public Group(short id, String name, int member, Date createDate, User creator) {
		this.groupID = id;
		this.name = name;
		this.member = member;
		this.createDate = createDate;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public GroupDTO toGroupDTO() {
		return new GroupDTO(groupID, name, member, createDate, creator.getUserID(),creator);
	}

	@Override
	public String toString() {
		return "Group [id=" + groupID + ", name=" + name + ", member=" + member + ", createDate=" + createDate + "]";
	}
	
	

}
