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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Stream {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	int streamId;

	@Column(length = 10)
	@Getter
	@Setter
	private String streamCode;

	@Column(length = 30)
	@Getter
	@Setter
	private String streamName;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stream")
	@Getter
	@Setter
	private List<Student> students;

	@JsonBackReference
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, mappedBy = "streamsInCollege")
	@Getter
	@Setter
	private Set<College> collegesWithStream = new HashSet<College>();

	public Stream() {
		super();
	}

	public Stream(int streamId, String streamCode, String streamName, List<Student> students,
			Set<College> collegesWithStream) {
		super();
		this.streamId = streamId;
		this.streamCode = streamCode;
		this.streamName = streamName;
		this.students = students;
		this.collegesWithStream = collegesWithStream;
	}

	

}
