package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAO {
    public  void cadastrarAluno(String nome, int idade, double nota){
        try(Connection conn  = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("INSERT INTO alunos (nome, idade, nota) VALUES (?, ?, ?)");){
            sql.setString(1, nome);
            sql.setInt(2, idade);
            sql.setDouble(3, nota);

            sql.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
        }catch (SQLException e){
            System.err.println("Erro ao inserir dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Optional<Aluno> buscarAluno(String nome){
        try(Connection conn = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("SELECT ID, NOME, IDADE, NOTA FROM alunos Where nome = ?")){
            sql.setString(1, nome);

            try(ResultSet rs = sql.executeQuery()){
                if(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("nome");
                    int idade = rs.getInt("idade");
                    double nota = rs.getDouble("nota");

                    Aluno aluno = new Aluno(id ,name, idade,nota);

                    return Optional.of(aluno);
                }
            }
        }catch (SQLException e){
            System.err.println("Erro ao buscar dados: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Aluno> listarAlunos()  {
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("SELECT id, nome, idade, nota FROM alunos")){
            try (ResultSet rs = sql.executeQuery()){
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("nome");
                    int idade = rs.getInt("idade");
                    double nota = rs.getDouble("nota");

                    Aluno aluno = new Aluno(id,name, idade,nota);
                    alunos.add(aluno);

                }
                return alunos;
            }
        }catch (SQLException e) {
            System.err.println("Erro ao listar dados: " + e.getMessage());
            e.printStackTrace();
        }

        return  alunos;
    }

    public void atulizarAluno(Aluno aluno){
        try(Connection conn = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("UPDATE alunos SET nome = ?, idade = ?, nota = ? WHERE id = ?")){

            sql.setString(1, aluno.getName());
            sql.setInt(2, aluno.getAge());
            sql.setDouble(3, aluno.getGrade());
            sql.setInt(4, aluno.getId());

            int linhasAfetadas = sql.executeUpdate();

            if(linhasAfetadas == 1){
                System.out.println("Atualização do cadastro do aluno realizada!");
            }else{
                System.out.println("Aluno não encontrado!");
            }

        }catch (SQLException e) {
            System.err.println("Erro ao atualizar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removerAluno(int id){
        try(Connection conn = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("DELETE FROM alunos WHERE id = ?")
        ) {

            sql.setInt(1, id);

            int linhasAfetadas = sql.executeUpdate();

            if(linhasAfetadas == 1){
                System.out.println("Removendo o cadastro do aluno!");
            }else{
                System.out.println("Aluno não encontrado!");
            }

        }catch (SQLException e) {
            System.err.println("Erro ao remover os  dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
