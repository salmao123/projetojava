/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author uliss
 */
public class UsuarioCRUD {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection con = databaseConnection.conectaDB("login");
    public void novoUsuario(Usuario usuario){
        String sql = "INSERT INTO login(id, nome, login, senha) VALUES(null, ?, ?, ?);";
        try{
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setString(2,usuario.getNome());
        ps.setString(3,usuario.getLogin());
        ps.setString(4,usuario.getSenha());
        ps.execute();
        System.out.println("Usuário salvo com sucesso!");
        } catch(SQLException e){
        System.out.println(e.getMessage());
        }
    }
    public void excluirUsuario(int id){
        String sql = "DELETE FROM login WHERE id=?;";
        try{
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Usuário: " + id + " excluído com sucesso!");
        } catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
    public void atualizarUsuario(String nome, String login, String senha){
        String sql = "UPDATE login SET nome=?, login=?, senha=?;";
        try{
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, login);
        ps.setString(3, senha);
        ps.execute();
        System.out.println("Usuário: " + nome + " atulizado com sucesso!");
        } catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
    public Usuario listarUsuarios(){
        String sql = "SELECT * FROM login;";
        List<Usuario> dados = new ArrayList<Usuario>();
        Usuario usuario = null;
        Usuario usuarios = null;
        try{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if( rs == null) {
            System.out.println("A tabela está vazia");
        } else {
            while(rs.next()){
                Usuario Usuarios = new Usuario();
                usuarios.setNome(rs.getString("nome"));
                usuarios.setLogin(rs.getString("login"));
                usuarios.setSenha(rs.getString("senha"));
                dados.add(usuarios);

            }

        }
        } catch(Exception e){
        System.out.println(e.getMessage());

        }
        return usuarios;

   }

    private static class usuarios {

        private static void setNome(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static void setLogin(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static void setSenha(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public usuarios() {
        }
    }
}
