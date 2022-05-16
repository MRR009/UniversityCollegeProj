package com.stg.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private int collegeId;

	@Column(length = 6)
	@Getter
	@Setter
	private String collegeCode;

	@Column(length = 60)
	@Getter
	@Setter
	private String collegeName;

	@Column(length = 7)
	@Getter
	@Setter
	private String collegeType;

	@Column(length = 35)
	@Getter
	@Setter
	private String collegeLocation;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "uni_code", referencedColumnName = "universityId", nullable = false)
	@JsonBackReference
	@Getter
	@Setter
	private University university;

	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "collegestreamjunc", joinColumns = @JoinColumn(name = "collegeId"), inverseJoinColumns = @JoinColumn(name = "streamId"))
	@Getter
	@Setter
	private Set<Stream> streamsInCollege = new HashSet<Stream>();

	public College() {
		super();
	}

	public College(int collegeId, String collegeCode, String collegeName, String collegeType, String collegeLocation,
			University university, Set<Stream> streamsInCollege) {
		super();
		this.collegeId = collegeId;
		this.collegeCode = collegeCode;
		this.collegeName = collegeName;
		this.collegeType = collegeType;
		this.collegeLocation = collegeLocation;
		this.university = university;
		this.streamsInCollege = streamsInCollege;
	}

	

}
