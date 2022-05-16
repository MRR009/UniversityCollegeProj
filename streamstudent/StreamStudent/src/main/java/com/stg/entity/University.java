package com.stg.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
public class University {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private int universityId;
	
	@Column(length = 6)
	@Getter @Setter private String universityCode;
	
	@Column(length = 60)
	@Getter @Setter private String universityName;
	
	@Column(length = 35)
	@Getter @Setter private String universityLocation;
	
	@Column(length = 35)
	@Getter @Setter private String specialization;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "university")
	@Getter @Setter private List<College> colleges;

	public University() {
		super();
	}

	public University(int universityId, String universityCode, String universityName, String universityLocation,
			String specialization, List<College> colleges) {
		super();
		this.universityId = universityId;
		this.universityCode = universityCode;
		this.universityName = universityName;
		this.universityLocation = universityLocation;
		this.specialization = specialization;
		this.colleges = colleges;
	}

}
