/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.text.SimpleDateFormat;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author 20221PF.CC0037
 */
@Entity
@Table(name = "tb_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {
    
    @Id
    private String cpf;
    
    @Column(nullable = false, length = 50)
    private String nome;
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_nascimento;
    
    @Column(nullable = true, length = 8)
    private String cep;
    
    @Column(nullable = true, length = 100)
    private String complemento;
    
    Pessoa() {}
    
    Pessoa(String cpf, String nome, Calendar data_nascimento, String cep, String complemento) {
        this.cpf = cpf;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.cep = cep;
        this.complemento = complemento;
    }
    
    public String getCPF() {
        return this.cpf;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public Calendar getDataNascimento() {
        return this.data_nascimento;
    }
    
    public String getCEP() {
        return this.cep;
    }
    
    public String getComplemento() {
        return this.complemento;
    }
    
    String mostrar() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        String res = "> " + this.nome + "\n";
        res += " @ CPF: " + this.cpf + "\n";
        res += " @ Data de nascimento: " + sdf.format(this.data_nascimento.getTime()) + "\n";
        res += " @ Complemento: " + this.complemento + "\n";
        res += " @ Complemento: " + this.complemento + "\n";
        return res;
    }
}
