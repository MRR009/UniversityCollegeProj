package com.stg.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int studentId;

	@Column(length = 35)
	private String studentName;

	@Column(length = 40)
	private String studentEmail;

	@Column(length = 20)
	private String studentUsername;

	@Column(length = 30)
	private String studentPassword;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "strm_code", referencedColumnName = "streamId", nullable = false)
	@JsonBackReference
	private Stream stream;

	public Student() {
		super();
	}

	public Student(int studentId, String studentName, String studentEmail, String studentUsername,
			String studentPassword, Stream stream) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentUsername = studentUsername;
		this.studentPassword = studentPassword;
		this.stream = stream;
	}

}
