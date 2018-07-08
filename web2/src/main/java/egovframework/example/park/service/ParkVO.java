package egovframework.example.park.service;

import java.util.Date;

public class ParkVO extends ParkSearchVO {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	// 관리번호
	private String code;
	
	// 공원이름
	private String name;
	
	// 공원구분코드
	private String parkTypeCode;
	
	// 도로명주소
	private String addressRoad;
	
	// 지번주소
	private String addressJibun;
	
	// 위도
	private Double latitude;
	
	// 경도
	private Double longitude;
	
	// 면적
	private Double area;
	
	// 등록자id
	private int registeredId;

	// 수정자id
	private int updatedId;
	
	// 등록일시
	private Date registeredAt;
	
	// 수정일시
	private Date updatedAt;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
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

	public String getParkTypeCode() {
		return parkTypeCode;
	}

	public void setParkTypeCode(String parkTypeCode) {
		this.parkTypeCode = parkTypeCode;
	}

	public String getAddressRoad() {
		return addressRoad;
	}

	public void setAddressRoad(String addressRoad) {
		this.addressRoad = addressRoad;
	}

	public String getAddressJibun() {
		return addressJibun;
	}

	public void setAddressJibun(String addressJibun) {
		this.addressJibun = addressJibun;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
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
