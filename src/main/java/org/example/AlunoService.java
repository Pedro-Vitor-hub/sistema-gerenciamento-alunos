package org.example;

import java.util.List;
import java.util.Optional;

public class AlunoService {
    private AlunoDAO alunoDAO;

    public AlunoService(AlunoDAO alunoDAO){
        this.alunoDAO = alunoDAO;
    }

    public void removerAluno(int id){ alunoDAO.removerAluno(id); }

    public double calculaMedia(){ return  alunoDAO.calculaMedia(); }

    public List<Aluno> listarAlunosAprovdos(){ return alunoDAO.listarAlunosAprovados();}

    public List<Aluno> listarAlunosReprovados(){ return alunoDAO.listarAlunosReprovados(); }

    public List<Aluno> listarPorNota(){ return alunoDAO.listarPorNota(); }

    public Optional<Aluno> buscarAluno(String nome){
        return alunoDAO.buscarAluno(nome);
    }

    public List<Aluno> listarTodosOsAlunos(){ return alunoDAO.listarAlunos(); }

    public List<Aluno> listarPorNome() { return  alunoDAO.listarPorNome(); }

    public void cadastrarAluno(Aluno aluno){
        if(aluno == null){
            throw new AlunoNuloException("Aluno não pode ser nulo!");
        }

        if(aluno.getName().isBlank()){
            throw  new NomeInvalidoException("Nome inválido do aluno!");
        }

        if(aluno.getAge() <= 0){
            throw  new IdadeInvalidaException("Idade inválida!");
        }

        if(aluno.getGrade() < 0 || aluno.getGrade() > 10){
            throw new NotaInvalidaException("Nota inválida!");
        }

       int id =  alunoDAO.cadastrarAluno(aluno.getName(), aluno.getAge(), aluno.getGrade());

        aluno.setId(id);
    }

    public void atualizaAluno(Aluno aluno){

        if(aluno == null){
            throw new AlunoNuloException("Aluno não pode ser nulo!");
        }

        if(aluno.getName().isBlank()){
            throw  new NomeInvalidoException("Nome inválido do aluno!");
        }

        if(aluno.getAge() <= 0){
            throw  new IdadeInvalidaException("Idade inválida!");
        }

        if(aluno.getGrade() < 0 || aluno.getGrade() > 10){
            throw new NotaInvalidaException("Nota inválida!");
        }

        Optional<Aluno> resultado = alunoDAO.buscarPorId(aluno.getId());
        if(resultado.isEmpty()){
            throw new AlunoNaoEncontrado("Aluno não encontrado");
        }

        alunoDAO.atualizarAluno(aluno);
    }

}
