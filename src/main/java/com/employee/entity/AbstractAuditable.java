package com.employee.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditable<U> implements Persistable<String>, Serializable {

	private static final long serialVersionUID = -6615842299925446677L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID")
	@JsonIgnore
	private String id;
	
	@Basic
	@Column(name = "CREATED_BY", updatable = false, nullable= false)
	@JsonIgnore
	@CreatedBy
	private U createdBy;

	@Basic
	@Column(name = "CREATED_ON", updatable = false, nullable= false)
	@JsonIgnore
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;

	@Basic
	@Column(name = "LAST_UPDATED_BY", nullable= false)
	@JsonIgnore
	@LastModifiedBy
	private U lastModifiedBy;

	@Basic
	@Column(name = "LAST_UPDATED_ON", nullable= false)
	@JsonIgnore
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedDate;


	@Version
	@Column(name="VERSION_NUMBER")
	@NotNull
	@JsonIgnore
	private long versionNumber = 1;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final U createdBy) {
		this.createdBy = createdBy;
	}

	
	/*public DateTime getCreatedDate() {
		return createdDate == null? null : new DateTime(createdDate.longValue());
	}

	public void setCreatedDate(final DateTime createdDate) {
		this.createdDate = createdDate == null? null : createdDate.getMillis();
	}*/
	
	/*public DateTime getLastModifiedDate() {
		return lastModifiedDate == null? null : new DateTime(lastModifiedDate.longValue());
	}
	
	public void setLastModifiedDate(final DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate == null? null : lastModifiedDate.getMillis();
	}*/
	
	
	@JsonIgnore
	public boolean isNew() {
		 return null == getId();
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public U getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(long versionNumber) {
		this.versionNumber = versionNumber;
	}

}
