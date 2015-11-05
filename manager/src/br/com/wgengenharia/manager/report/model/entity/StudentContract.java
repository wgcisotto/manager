package br.com.wgengenharia.manager.report.model.entity;

public class StudentContract {
	
//	private String contract_number;
	private String contratante;
	private String rg;
	private String cpf;
	private String nascimento;
	private String endereco;
	private String bairro;
	private String cidade;
	private String cep;
	private String telefone_res;
	private String telefone_com;
	private String aluno;
	private String aluno_nascimento;

//	public String getContract_number() {
//		return contract_number;
//	}
//	public void setContract_number(String contract_number) {
//		this.contract_number = contract_number;
//	}
	public String getContratante() {
		return contratante;
	}
	public void setContratante(String contratante) {
		if(contratante ==  null){
			this.contratante = "";
		}else{
			this.contratante = contratante;
		}
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		if(rg ==  null){
			this.rg = "";
		}else{
			this.rg = rg;
		}
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		if(cpf ==  null){
			this.cpf = "";
		}else{
			this.cpf = cpf;
		}
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		if(nascimento ==  null){
			this.nascimento = "";
		}else{
			this.nascimento = nascimento;
		}
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		if(endereco ==  null){
			this.endereco = "";
		}else{
			this.endereco = endereco;
		}
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		if(bairro ==  null){
			this.bairro = "";
		}else{
			this.bairro = bairro;
		}
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		if(cidade ==  null){
			this.cidade = "";
		}else{
			this.cidade = cidade;
		}
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		if(cep ==  null){
			this.cep = "";
		}else{
			this.cep = cep;
		}
	}
	public String getTelefone_res() {
		return telefone_res;
	}
	public void setTelefone_res(String telefone_res) {
		if(telefone_res ==  null){
			this.telefone_res = "";
		}else{
			this.telefone_res = telefone_res;
		}
	}
	public String getTelefone_com() {
		return telefone_com;
	}
	public void setTelefone_com(String telefone_com) {
		if(telefone_com ==  null){
			this.telefone_com = "";
		}else{
			this.telefone_com = telefone_com;
		}
	}
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		if(aluno ==  null){
			this.aluno = "";
		}else{
			this.aluno = aluno;
		}
	}
	public String getAluno_nascimento() {
		return aluno_nascimento;
	}
	public void setAluno_nascimento(String aluno_nascimento) {
		if(aluno_nascimento ==  null){
			this.aluno_nascimento = "";
		}else {
			this.aluno_nascimento = aluno_nascimento;
		}
	}
}
