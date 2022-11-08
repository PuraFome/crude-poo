package BD;

import Classes.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CadastroDAO {
        public void salvarCadastro(Login l){
            String sql = "INSERT INTO projetoCRUD.login (nome, login, senha) values (?, ?, ?)";
            
            Connection conn = null;
            PreparedStatement pstm = null;
            
            try{
                conn = ConnectionFactory.criarConexao();
                pstm = conn.prepareStatement(sql);
                
                pstm.setString(1, l.nome);
                pstm.setString(2, l.login);
                pstm.setString(3, l.senha);
                
                pstm.execute();  
                JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Erro cadastrar: "+e);
            } finally {
                try {
                    if (pstm != null){
                        pstm.close();
                    }
                    
                    if (conn != null){
                        conn.close();
                    }
                } catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Erro cadastrar, encerrar conexão: "+e);
                }
            }
            
        }
        
        public boolean autenticaUsuarioBoleano(Login login) throws Exception{
            String sql = "Select * from projetoCRUD.login where login = ?  and senha = ? ";
            
            Connection conn = null;
            PreparedStatement pstm = null;
            boolean resp = false;
            
            try{
                conn = ConnectionFactory.criarConexao();
                pstm = conn.prepareStatement(sql);
                
                pstm.setString(1, login.login);
                pstm.setString(2, login.senha);
                
                ResultSet result = pstm.executeQuery();
                
                
                if ( result.next() )
                    login.nome = result.getString("nome");
                    resp = true;
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "UsuárioDAO: " + e);
            }
            return resp;            
        }        
}
