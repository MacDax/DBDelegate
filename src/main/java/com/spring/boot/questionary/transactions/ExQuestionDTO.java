package com.spring.boot.questionary.transactions;

import java.util.Date;

public class ExQuestionDTO {

	private int questionId;
	private int questionTypeCode;
	private int subjectCode;
	private int teacherId;
	private String questionText;
	private Date lastUpdated;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuestionTypeCode() {
		return questionTypeCode;
	}

	public void setQuestionTypeCode(int questionTypeCode) {
		this.questionTypeCode = questionTypeCode;
	}

	public int getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
