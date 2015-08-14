package br.com.wgengenharia.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_BRANCH")
@SequenceGenerator(name="seqBranch", sequenceName="SEQ_BRANCH",allocationSize=1)
public class Branch {

	@Id
	@Column(name="ID_BRANCH")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqBranch")
	private int id_branch;
	
	@Column(name="NAME")
	private String name;
	
	
	
	public int getId_branch() {
		return id_branch;
	}
	public void setId_branch(int id_branch) {
		this.id_branch = id_branch;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
