
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author uliss
 */
public class UsuarioDAO {
    ConectaBD bd = new ConectaBD();
    Connection con = bd.conexao();
    //método para localizar usuário pelo login
    public Integer pesquisaUsuario(String login) throws SQLException{
    String sql = "SELECT * FROM usuario WHERE email = '" + login + "'";
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);
    if(!rs.isBeforeFirst()){
    System.out.println("Não achou o usuário!");
    return 0;
    } else {
    return rs.getInt("id");
    }
    }
    public void atualizaSenha(int id, String senha) {
    String sql = "UPDATE usuario SET senha = '" + senha +"' WHERE id = " +
   id;
    try{
    //evita inserção de código malicioso no banco
        PreparedStatement ps = (PreparedStatement)
       con.prepareStatement(sql);
        ps.executeUpdate();
        System.out.println("Senha atualizada!");
        } catch (Exception e){
        System.out.println("Senha não foi atualizada!");
        }
    }
    public void inserirUsuario(Usuario usuario){
        String sql = "INSERT INTO usuario(id, nome, email, senha) VALUES (?, ?, ?, ?)";
        try{
        //evita inserção de código malicioso no banco
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setInt(1, usuario.getId());
        ps.setString(2, usuario.getNome());
        ps.setString(3, usuario.getEmail());
        ps.setString(4, usuario.getSenha());
        ps.execute();
        System.out.println("Dados do usuário salvos com sucesso!");
        } catch(SQLException e){
        System.out.println("Erro ao salvar dados do usuário!" + e.getMessage());
        }
    }
    public void excluirUsuario(int id){
    String sql ="DELETE FROM usuario WHERE id = ?";
    try{
        //evita inserção de código malicioso no banco
        PreparedStatement ps = (PreparedStatement)
       con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Dados do usuário " + id + " foram excluídos!");
        } catch(SQLException e){
        System.out.println("Erro ao excluir dados do usuário " + id + " " +
       e.getMessage());
        }
    }
    public void atualizarUsuario(int id, String nome, String email, String senha){
        String sql;
        try{
            if(senha.isEmpty()){
                sql = "UPDATE usuario SET nome = ?, email = ?, WHERE id = ?";
                //evita inserção de código malicioso no banco
                PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
                ps.setString(1, nome);
                ps.setString(2, email);
                ps.executeUpdate();
            } else {
                sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
                //evita inserção de código malicioso no banco
                PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
                ps.setString(1, nome);
                ps.setString(2, email);
                ps.setString(3, senha);
                ps.executeUpdate();

           }
            System.out.println("Dados do usuário " + id + " foram atualizados!");
            } catch(SQLException e){
            System.out.println("Erro ao atualizar dados do usuário " + id + " " + e.getMessage());

       }

   }
    public List<Usuario> listarUsuario(){
        List<Usuario> dados = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuario";
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(!rs.isBeforeFirst()){
            System.out.println("A tabela usuário está vazia!");
        } else {
            while(rs.next()){
                Usuario usuarios = new Usuario();
                usuarios.setId(rs.getInt("id"));
                usuarios.setNome(rs.getString("nome"));
                usuarios.setEmail(rs.getString("email"));
                usuarios.setSenha(rs.getString("senha"));
                dados.add(usuarios);

                System.out.println("ID: " + rs.getString("id"));
                System.out.println("NOME: " + rs.getString("nome"));
                System.out.println("E-MAIL: " + rs.getString("email"));
                System.out.println("SENHA: " + rs.getString("senha"));

            }
            }   
        } catch(SQLException e){
        System.out.println("Erro ao listar dados de usuário!");

       }
        return dados;

   }
    public int ultimoId(){
        String sql = "SELECT last_insert_rowid(), id FROM usuario";
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
        if(rs != null){
            //resultset retorna apenas string. Converte de string para int
            return Integer.parseInt(rs.getString("id"));
        }
        } catch(Exception e){
        e.printStackTrace();
        }
        return 0;
    }

}