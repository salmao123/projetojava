
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author uliss
 */
public class DatabaseConnection {
    private Connection con = null;
    private final String tb = "CREATE TABLE IF NOT EXISTS usuario(id integer primary key, nome string, login string unique, senha string);";

    public Connection conectaDB(String banco){
    try{
    //registra o novo driver de conexão ao banco de dados
    Class.forName("org.sqlite.JDBC");
    con = DriverManager.getConnection("jdbc:sqlite:"+banco+".db");
    Statement st = con.createStatement();
    st.execute(tb);
    System.out.println("Conectado ao banco de dados "+banco);
    } catch (Exception e){
    System.out.println("Erro ao conectar ao banco de dados: " + e);
    System.exit(0);

   }
    return con;

   }
    public void fechaConexao(Connection db){
    try{
        if(db != null){
        db.close();
        System.out.println("Banco de dados desconectado!");

       }
        } catch(Exception e){
        System.out.println("Erro ao fechar o banco de dados: " + e);
        System.exit(0);

       }

   }

    void conectaDB() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}


