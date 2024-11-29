/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.*;
public class ConectaBD {
    public Connection conexao(){
 String banco = "jdbc:sqlite:teste.db";
 String usuario = "CREATE TABLE IF NOT EXISTS usuario (id integer, nome string, email string, senha string)";
 Connection con = null;
 try{
 con = DriverManager.getConnection(banco);
 Statement st = con.createStatement();
 st.execute(usuario);
 } catch(SQLException e){
 System.out.println("Erro ao tentar conectar ao banco " + e.getMessage());
 }
 System.out.println("Conectado ao banco de dados com sucesso!");
 return con;
 }

}
