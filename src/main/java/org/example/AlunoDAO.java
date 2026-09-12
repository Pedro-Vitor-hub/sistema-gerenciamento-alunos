package org.example;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Statement;

public class AlunoDAO {

    private Aluno mapearAluno(ResultSet rs) throws  SQLException{
        int id = rs.getInt("id");
        String name = rs.getString("nome");
        int idade = rs.getInt("idade");
        double nota = rs.getDouble("nota");

        Aluno aluno = new Aluno(id ,name, idade,nota);
        return  aluno;
    }

    public int cadastrarAluno(String nome, int idade, double nota){
        int result = 0;

        try(Connection conn  = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("INSERT INTO alunos (nome, idade, nota) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);){
            sql.setString(1, nome);
            sql.setInt(2, idade);
            sql.setDouble(3, nota);


            sql.executeUpdate();

            try(ResultSet rs = sql.getGeneratedKeys();){
                if(rs.next()) {
                    result = rs.getInt("id");
                }
            }

        }catch (SQLException e){
            System.err.println("Erro ao inserir dados: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Optional<Aluno> buscarAluno(String nome){
        try(Connection conn = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("SELECT ID, NOME, IDADE, NOTA FROM alunos Where nome = ?")){
            sql.setString(1, nome);

            try(ResultSet rs = sql.executeQuery()){
                if(rs.next()){
                    Aluno aluno  = mapearAluno(rs);
                    return Optional.of(aluno);
                }
            }
        }catch (SQLException e){
            System.err.println("Erro ao buscar dados: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Aluno> buscarPorId(int id){
        try(Connection conn = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("SELECT ID, NOME, IDADE, NOTA FROM alunos Where id = ?")){
            sql.setInt(1, id);

            try(ResultSet rs = sql.executeQuery()){
                if(rs.next()){
                    Aluno aluno  = mapearAluno(rs);
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
                    Aluno aluno  = mapearAluno(rs);
                    alunos.add(aluno);
                }
            }
        }catch (SQLException e) {
            System.err.println("Erro ao listar dados: " + e.getMessage());
            e.printStackTrace();
        }

        return alunos;
    }

    public void atualizarAluno(Aluno aluno){
        try(Connection conn = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("UPDATE alunos SET nome = ?, idade = ?, nota = ? WHERE id = ?")){

            sql.setString(1, aluno.getName());
            sql.setInt(2, aluno.getAge());
            sql.setDouble(3, aluno.getGrade());
            sql.setInt(4, aluno.getId());

           sql.executeUpdate();

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
            sql.executeUpdate();

        }catch (SQLException e) {
            System.err.println("Erro ao remover os  dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public double calculaMedia(){
        try (Connection conn = new Conexao().conexaoSql();
              PreparedStatement sql = conn.prepareStatement("SELECT AVG(nota) AS media FROM  alunos")){
            try (ResultSet rs = sql.executeQuery()){
                rs.next();
                double media = rs.getDouble("media");
                return media;
            }
        }catch (SQLException e){
            System.err.println("Erro ao obter a media das notas: " + e.getMessage());
            e.printStackTrace();
        }

        return 0.0;
    }

    public List<Aluno> listarAlunosAprovados(){
        List<Aluno> alunos = new ArrayList<>();
        try(Connection conn = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("SELECT id, nome, idade, nota FROM alunos WHERE nota >= 7.0")){

            try (ResultSet rs = sql.executeQuery()){
                while (rs.next()){
                    Aluno aluno  = mapearAluno(rs);
                    alunos.add(aluno);
                }
            }

        }catch (SQLException e){
            System.err.println("Erro ao obter os Aluno aprovados: " + e.getMessage());
            e.printStackTrace();
        }
        return alunos;
    }

    public List<Aluno> listarAlunosReprovados(){
        List<Aluno> alunos = new ArrayList<>();
        try(Connection conn = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("SELECT id, nome, idade, nota FROM alunos WHERE nota < 7.0")){

            try (ResultSet rs = sql.executeQuery()){
                while (rs.next()){
                    Aluno aluno  = mapearAluno(rs);
                    alunos.add(aluno);
                }
            }

        }catch (SQLException e){
            System.err.println("Erro ao obter os Aluno Reprovados: " + e.getMessage());
            e.printStackTrace();
        }
        return alunos;
    }

    public List<Aluno> listarPorNota(){
        List<Aluno> alunos = new ArrayList<>();

        try(Connection conn = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("SELECT  id, nome, idade, nota FROM alunos ORDER BY nota DESC ")) {
            try (ResultSet rs = sql.executeQuery()){
                while (rs.next()){
                    Aluno aluno  = mapearAluno(rs);
                    alunos.add(aluno);
                }
            }

        }catch (SQLException e){
            System.err.println("Erro ao obter  a lista dos Alunos:" + e.getMessage());
            e.printStackTrace();
        }

        return  alunos;
    }

    public List<Aluno> listarPorNome(){
        List<Aluno> alunos = new ArrayList<>();

        try(Connection conn = new Conexao().conexaoSql();
            PreparedStatement sql = conn.prepareStatement("SELECT  id, nome, idade, nota FROM alunos ORDER BY nome ASC")){
            try(ResultSet rs =  sql.executeQuery()){
                while (rs.next()){
                    Aluno aluno  = mapearAluno(rs);
                    alunos.add(aluno);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao obter  a lista dos Alunos: " + e.getMessage());
            e.printStackTrace();
        }

        return alunos;
    }
}
