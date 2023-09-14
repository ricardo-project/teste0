/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author 20221PF.CC0037
 */
@Entity
@Table(name = "tb_maoObra")
public class Peca {
    
    @Id
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false)
    private Float valor;
    
    @Column(nullable = false, length = 100)
    private String fornecedor;
    
    Peca() {}
    
    Peca(Integer id, String nome, Float valor, String fornecedor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.fornecedor = fornecedor;
    }
    
    String mostrar() {
        String res = "> Peça de id " + this.id + "\n";
        res += " @ Nome: " + this.nome + "\n";
        res += " @ Valor: " + this.valor + "\n";
        res += " @ Fornecedor: " + this.fornecedor + "\n";
        return res;
    }
}
