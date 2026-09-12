package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public  Connection conexaoSql(){
        String url = "jdbc:postgresql://localhost:5432/gerenciamento_alunos";
        String user = "";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url,user,password);

            if(conn != null){
                System.out.println("Conexão estabelecida com sucesso!");
            }
            return conn;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            e.printStackTrace();
        }

        return  null;
    }
}
