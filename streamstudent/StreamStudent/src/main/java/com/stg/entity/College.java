package com.stg.entity;

import java.util.HashSet;
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

import lombok.Data;

@Entity
@Data
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int collegeId;

	@Column(length = 6)
	private String collegeCode;

	@Column(length = 60)
	private String collegeName;

	@Column(length = 7)
	private String collegeType;

	@Column(length = 35)
	private String collegeLocation;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "uni_code", referencedColumnName = "universityId", nullable = false)
	@JsonBackReference
	private University university;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "collegestreamjunc", joinColumns = @JoinColumn(name = "collegeId"), inverseJoinColumns = @JoinColumn(name = "streamId"))
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
