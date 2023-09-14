/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.*;
import java.text.*;
import javax.persistence.*;

@Entity
@Table(name = "tb_funcionario")
@DiscriminatorValue("F")
public class Funcionario extends Pessoa {
    
    @Column(nullable = false, length = 100)
    private String numero_ctps;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_admissao;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_demissao;
    
    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;
    
    public String getCTPS() {
        return this.numero_ctps;
    }
    
    public Calendar getDataAdmissao() {
        return this.data_admissao;
    }
    
    public Calendar getDataDemissao() {
        return this.data_demissao;
    }
    
    public Cargo getCargo() {
        return this.cargo;
    }
    
    Funcionario() {}
    
    public Funcionario(String cpf, String nome, Calendar data_nascimento, String cep, String complemento, String numero_ctps, Calendar data_admissao, Calendar data_demissao, Cargo cargo) {
        super(cpf, nome, data_nascimento, cep, complemento);
        this.numero_ctps = numero_ctps;
        this.data_admissao = data_admissao;
        this.data_demissao = data_demissao;
        this.cargo = cargo;
    }
    
    public String mostrarFuncionario()  {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
        return "Meu nome é " + this.getNome() + ", nasci em " + format.format(this.getDataNascimento().getTime()) + " e essas sao minhas informacoes basicas\n"
        + " >> CPF: " + this.getCPF() + "\n"
        + " >> CEP: " + this.getCEP() + "\n"
        + " >> Complemento: " + this.getComplemento() + "\n"
        + " >> Numero CTPS: " + this.getCTPS() + "\n"
        + "Me empregaram em " + format.format(this.getDataAdmissao().getTime()) + " como "
        + this.getCargo().getDescricao() + ", mas fui demitido em " + format.format(this.getDataDemissao().getTime()) + "... :(\n";
    }
}