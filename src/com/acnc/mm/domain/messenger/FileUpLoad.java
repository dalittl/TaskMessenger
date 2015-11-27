package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;

public class FileUpLoad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5629090934558000418L;
	
	private String clob;
	
	private Long fileid;
	
	private String newName;
	
	private String oldFileName;
	
	private Long catId;
	
	private Date created;
	
	public FileUpLoad(){
		
	}
	
public FileUpLoad(String clob, Long fileid, String newName, String oldFileName, Long catId, Date created ){
	
	this.clob=clob;
	this.fileid=fileid;
	this.newName=newName;
	this.oldFileName=oldFileName;
	this.catId=catId;
	this.created=created;
		
	}

public String getClob() {
	return clob;
}

public void setClob(String clob) {
	this.clob = clob;
}

public Long getFileid() {
	return fileid;
}

public void setFileid(Long fileid) {
	this.fileid = fileid;
}

public String getNewName() {
	return newName;
}

public void setNewName(String newName) {
	this.newName = newName;
}

public String getOldFileName() {
	return oldFileName;
}

public void setOldFileName(String oldFileName) {
	this.oldFileName = oldFileName;
}

public Long getCatId() {
	return catId;
}

public void setCatId(Long catId) {
	this.catId = catId;
}

public Date getCreated() {
	return created;
}

public void setCreated(Date created) {
	this.created = created;
}

   

}
