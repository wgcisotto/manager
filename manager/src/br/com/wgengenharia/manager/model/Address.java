package br.com.wgengenharia.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_ADDRESS")
@SequenceGenerator(name="seqAddress", sequenceName="SEQ_ADDRESS",allocationSize=1)
public class Address {

	@Id
	@Column(name="ID_ADDRESS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqAddress")
	private int id_address;
	@Column(name="STREET")
	private String Street;
	@Column(name="DISTRICT")
	private String district;
	@Column(name="CITY")
	private String citys;
	@Column(name="STATE")
	private String state;
	@Column(name="STREET_NUMBER")
	private Integer number;

	public int getId_address() {
		return id_address;
	}
	public void setId_address(int id_address) {
		this.id_address = id_address;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCitys() {
		return citys;
	}
	public void setCitys(String citys) {
		this.citys = citys;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
