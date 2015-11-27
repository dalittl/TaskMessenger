package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;

public class FlatFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2825710694940323178L;
	
	private Long fileID;
	//private Blob fileClob;
	private byte[] clobFile;
	private String newName;
	private String oldName;
	private Date dateCreated;
	
	public FlatFile(){
		
	}
	
	public FlatFile(Long fid, byte[] cf, String oN, String nN, Date dc){
		this.fileID = fid;
		//this.fileClob = fc;
		this.clobFile = cf;
		this.newName = nN;
		this.oldName = oN;
		this.dateCreated = dc;
	}
	
	

	public Long getFileID() {
		return fileID;
	}

	public void setFileID(Long fileID) {
		this.fileID = fileID;
	}	

	
	public byte[] getClobFile() {
		return clobFile;
	}

	public void setClobFile(byte[] clobFile) {
		this.clobFile = clobFile;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	

}
