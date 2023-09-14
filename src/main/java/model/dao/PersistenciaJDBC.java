/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import model.Cargo;
import model.Funcionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Nicolasfmc
 */
public class PersistenciaJDBC implements InterfacePersistencia {
    
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_om_lpoo_20232";
    private Connection con = null;

    
    
    public PersistenciaJDBC() throws Exception{
        
        Class.forName(DRIVER); //carregamento do driver postgresql em tempo de execução
        System.out.println("Tentando estabelecer conexao JDBC com : "+URL+" ...");
            
        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA); 

        
    }

    public Boolean conexaoAberta() {
        
        try {
            if(con != null)
                return !con.isClosed();//verifica se a conexao está aberta
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return false;
        
    }

    @Override
    public void fecharConexao() {        
        
        try{                               
            this.con.close();//fecha a conexao.
            System.out.println("Fechou conexao JDBC");
        }catch(SQLException e){            
            e.printStackTrace();//gera uma pilha de erro na saida.
        } 
        
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void persist(Object o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remover(Object o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<Cargo> listCargo() throws Exception {
        
        List<Cargo> lista;
        PreparedStatement ps = this.con.prepareStatement("select id, descricao from tb_cargo");
        ResultSet rs = ps.executeQuery();
        lista = new ArrayList();
        while(rs.next()){
            Cargo c = new Cargo();
            c.setId(rs.getInt("id"));
            c.setDescricao(rs.getString("descricao"));
            lista.add(c);
        }
        return lista;
    }
    
    public List<Funcionario> listFuncionario() throws Exception {
        
        List<Funcionario> lista;
        PreparedStatement ps = this.con.prepareStatement("select P.nome, P.data_nascimento, P.cpf, P.cep, P.complemento, F.data_admissao, F.data_demissao, F.numero_ctps, F.cargo_id from\n"
        + "tb_funcionario F, tb_pessoa P where P.cpf = F.cpf;");
        ResultSet rs = ps.executeQuery();
        lista = new ArrayList();
        
        List<Cargo> cargos = listCargo();
        
        while(rs.next()){
            Calendar data_nasc = Calendar.getInstance();
            Calendar data_adm = Calendar.getInstance();
            Calendar data_dem = Calendar.getInstance();
            Date data_nasc_D = rs.getDate("data_nascimento");
            Date data_adm_D = rs.getDate("data_admissao");
            Date data_dem_D = rs.getDate("data_demissao");
            data_nasc.setTime(data_nasc_D);
            data_adm.setTime(data_adm_D);
            data_dem.setTime(data_dem_D);
            
            Cargo newC = new Cargo();
            for(Cargo c : cargos) {
                if(c.getId() == rs.getInt("cargo_id")) {
                    newC = c;
                    break;
                }
            }
            
            Funcionario F = new Funcionario(
                rs.getString("cpf"),
                rs.getString("nome"),
                data_nasc,
                rs.getString("cep"),
                rs.getString("complemento"),
                rs.getString("numero_ctps"),
                data_adm,
                data_dem, newC
            );
            lista.add(F);
        }
        return lista;
    }
}
