/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

/**
 *
 * @author 20221PF.CC0037
 */
@Entity
@Table(name = "tb_cliente")
@DiscriminatorValue("C")
public class Cliente extends Pessoa {
    
    @Column(nullable = false, length = 100)
    private String observacoes;
    
    @ManyToMany
    @JoinTable(name = "tb_cliente_veiculos", joinColumns = {@JoinColumn(name = "cpf_cliente")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "placa_veiculo")})       
    private Collection<Veiculo> veiculos;
    
    Cliente() {}
    
    Cliente(String cpf, String nome, Calendar data_nascimento, String cep, String complemento, String observacoes) {
        super(cpf, nome, data_nascimento, cep, complemento);
        this.observacoes = observacoes;
    }
}
