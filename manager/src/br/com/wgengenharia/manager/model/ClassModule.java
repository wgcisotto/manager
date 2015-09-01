package br.com.wgengenharia.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_CLASS_MODULE")
@SequenceGenerator(name="seqClassModule", sequenceName="SEQ_CLASSMODULE",allocationSize=1)
public class ClassModule {

	@Id
	@Column(name="ID_CLASS_MODULE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqClassModule")
	private int id_class_module;
	
	

}
