
package test;

import model.Cargo;
import model.Funcionario;
import model.dao.PersistenciaJDBC;
import java.util.List;
import java.io.*;
import org.junit.Test;

/**
 *
 * @author telmo
 */
public class TestPersistenceJDBC {
    
    //@Test
    public void testConexao() throws Exception  {
        
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via jdbc");
            
            persistencia.fecharConexao();
            
        }else{
            System.out.println("Nao abriu a conexao com o BD via jdbc");
        }  
    }
    
    //@Test
    public void testListPersistenciaCargo() throws Exception {
        
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            
            List<Cargo> lista = persistencia.listCargo();
            if(!lista.isEmpty()){
                for(Cargo c : lista){
                    System.out.println(c.getDescricao() + " (Id: " + c.getId() + ")");
                }
            } else{
                System.out.println("Não tem cargos registrados... ),:");
            }
            persistencia.fecharConexao();
        } else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }
    
    @Test
    public void testListPersistenciaFuncionario() throws Exception {
        
        System.setOut(new PrintStream(System.out, true, "UTF8"));
        
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            
            List<Funcionario> lista = persistencia.listFuncionario();
            if(!lista.isEmpty()){
                for(Funcionario f : lista){
                    System.out.println(f.mostrarFuncionario());
                }
            } else{
                System.out.println("Não tem funcionários registrados... ),:");
            }
            persistencia.fecharConexao();
        } else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }
    
    
    /*/@Test
    public void testListPersistenciaJogadorPatente() throws Exception {
        
        // recupera a lista de jogadores
        
        //imprimir na tela os dados de cada jogador e as suas respectivas patentes
        
        //alterar o jogador ao algum dado da tabela associativa.    

        //remove as patentes do jogador (tb_jogador_patente), uma a uma 
                
        //caso a lista de jogadores esteja vazia, insere um ou mais jogadores , bem como, vincula ao menos uma patente no jogador (tb_jogador_patente)        
                       
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JDBC");
            
            List<Jogador> list = persistencia.listJogadores();
            
            if(!list.isEmpty()){
                
                for(Jogador j : list){
                    
                    System.out.println("Jogador : "+j.getNickname());
                    System.out.println("Endereço: "+j.getEndereco().getCep());
                    
                    if(j.getPatentes() != null) {
                        for(Patente p : j.getPatentes()){

                            System.out.println("\tPatente : "+ p.getNome());

                        }
                    }
                    
                    j.setPontos(999);
                    
                    persistencia.persist(j);
                    
                    System.out.println(" Jogador alterado : "+ j.getNickname());
                    
                    persistencia.remover(j);
                    
                    System.out.println(" Jogador removido : "+ j.getNickname());
                                        
                }
                                                                           
            }else{
                
                Jogador j = new Jogador();
                j.setNickname("teste@");
                j.setSenha("123456");
                j.setPontos(0);
                Endereco end = new Endereco();
                end.setCep("99010010");
                
                persistencia.persist(end);                        
                j.setEndereco(end);
                
                Patente p = new Patente();
                p.setNome("Pantente de teste");
                persistencia.persist(p);
                
                j.setPatente(p);
                persistencia.persist(j);
                
                System.out.println("Incluiu o jogaador "+ j.getNickname()+ " com "+ j.getPatentes().size() + " patentes.");
                
                
            }
                    
            persistencia.fecharConexao();
            
        }else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
        
        
    }
    
    
    private Artefato getArtefato(PersistenciaJDBC persistencia, Integer id, String tipo) throws Exception {
        
        Artefato a = (Artefato) persistencia.find(Artefato.class, id);
        if(a == null){
            if(tipo.equals("A")){                
                Arma arma = new Arma();
                arma.setNome("Revolver");
                arma.setPeso(1.5f);
                arma.setValor(1500f);
                arma.setComprimento_cano(1.2f);
                arma.setTipo(Tipo.FOGO);
                arma.setTipoArtefato("A");
                persistencia.persist(arma);                             
                return arma;
            }else{
                Municao municao = new Municao();
                municao.setNome("Bala");
                municao.setPeso(0.5f);
                municao.setValor(25.5f);
                municao.setExplosiva(Boolean.FALSE);
                municao.setCalibre(Calibre.C03);
                municao.setTipoArtefato("M");
                persistencia.persist(municao);
                return municao;
            }                
            
        }
        
        return a;
        
    }
    
    private Endereco getEndereco(PersistenciaJDBC persistencia, Integer id) throws Exception {
        
        Endereco e = (Endereco) persistencia.find(Endereco.class, id);
        if(e == null){            
                e = new Endereco();
                e.setCep("99010011");
                e.setComplemento("nenhum");                
                persistencia.persist(e);                             
                                        
        }
        
        return e;
        
    }
    

    //@Test
    public void testListPersistenciaJogadorArtefato() throws Exception {
        
        // 1) Atividade de revisão para a avaliação da primeira etapa. 
     
        // recupera a lista de jogadores
        
        //imprimir na tela os dados de cada jogador e as suas respectivos artefatos (arma e/ou municao)
            

        //remove os artefatos do jogador (tb_jogador_artefato), um a um
                
        //caso a lista de jogadores esteja vazia, gera um jogador contendo dois artefatos. 
        
        
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via jdbc");
            
            List<Jogador> list = persistencia.listJogadores();
            
            if(list == null || list.isEmpty()){
                
                Jogador j = new Jogador();
                j.setNickname("fulano@");
                j.setSenha("123456");
                j.setPontos(0);
                j.setEndereco(getEndereco(persistencia, 1));
                j.setArtefato(getArtefato(persistencia, 1, "A"));
                
                persistencia.persist(j);
                
                System.out.println("Cadastrou o jogador "+j.getNickname());
                
                
            }else{
                
                System.out.println("Listagem de jogadores cadastrados:");
                for(Jogador j : list){
                    
                    System.out.println("\tJogador: "+j.getNickname());                    
                    System.out.println("\t\tArtefatos:");
                    if(j.getArtefatos() != null)
                        for(Artefato a: j.getArtefatos()){
                            System.out.println("\t\t\tArtefato "+a.getNome());
                        }
                    
                    persistencia.remover(j);
                    System.out.println("Removeu o jogador "+j.getNickname());
                }
                                
            }
            
            persistencia.fecharConexao();
            
        }else{
            System.out.println("Nao abriu a conexao com o BD via jdbc");
        } 
    }
            
    
    //@Test
    public void testListPersistenciaCompra() throws Exception {
     
        
         // 2) Atividade de revisão para a avaliação da primeira etapa. 
         
        // recupera a lista de compras
        
        //imprimir na tela os dados de cada compra e as seus respectivos itens

        //remova os itens e a compra
                
        //caso a lista de compra esteja vazia, gera uma compra contendo itens. 
        
    }*/
   
}
