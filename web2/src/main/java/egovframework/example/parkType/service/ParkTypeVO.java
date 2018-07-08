package egovframework.example.parkType.service;

import java.util.Date;

import egovframework.example.park.service.ParkSearchVO;

public class ParkTypeVO extends ParkSearchVO {

	private static final long serialVersionUID = 1L;

	// 관리번호
	private String code;
	
	// 공원이름
	private String name;
	
	// 등록자id
	private int registeredId;

	// 수정자id
	private int updatedId;
	
	// 등록일시
	private Date registeredAt;
	
	// 수정일시
	private Date updatedAt;

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRegisteredId() {
		return registeredId;
	}

	public void setRegisteredId(int registeredId) {
		this.registeredId = registeredId;
	}

	public int getUpdatedId() {
		return updatedId;
	}

	public void setUpdatedId(int updatedId) {
		this.updatedId = updatedId;
	}

	public Date getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(Date registeredAt) {
		this.registeredAt = registeredAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
