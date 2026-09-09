package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class AlunoNaoEncontrado extends RuntimeException{
    public AlunoNaoEncontrado(String message){
        super(message);
    }
}

class  AlunoNuloException extends RuntimeException{
    public AlunoNuloException(String message){
        super(message);
    }
}

class NomeInvalidoException extends RuntimeException{
    public NomeInvalidoException(String message){
        super(message);
    }
}

class IdadeInvalidaException extends  RuntimeException {
    public IdadeInvalidaException(String message) {
        super(message);
    }
}

class NotaInvalidaException extends RuntimeException {
    public NotaInvalidaException(String message){
        super(message);
    }
}

class AlunoDuplicadoException extends  RuntimeException{
    public  AlunoDuplicadoException(String message){
        super(message);
    }
}

class Aluno{

    private int id;
    private String name;
    private int age;
    private double grade;

    Aluno(int id,String nome, int age, double grade) {
        this.id = id;;
        this.name = nome;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public int getAge() {
        return age;
    }

    public int getId() { return id;}

    public void setName(String name) { this.name = name;}

    public void setAge(int age) { this.age = age; }

    public void setGrade(double grade) { this.grade = grade; }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Aluno {nome= " + name + ", idade=" + age + ", nota=" + grade + "}";
    }
}

class GerenciarAlunos {
    private List<Aluno> alunos;

    GerenciarAlunos() {
        this.alunos = new ArrayList<>();
    }

    public void cadastrar(Aluno aluno){
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
        Optional<Aluno> resultado = buscarPorNome(aluno.getName());
        if (resultado.isPresent()) {
            throw new AlunoDuplicadoException("Aluno já cadastrado!");
        }

        alunos.add(aluno);
    }

    public List<Aluno> listarTodosOsAlunos(){
        return alunos.stream().toList();
    }

    public Optional<Aluno> buscarPorNome(String name){
        return alunos.stream()
                .filter(aluno -> aluno.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public double calcularMedia(){
        return alunos.stream()
                .mapToDouble(Aluno::getGrade)
                .average()
                .orElse(0.0);
    }

    public List<Aluno> listarAprovados(){
        return  alunos.stream().filter( aluno -> aluno.getGrade() >= 7.0).toList();
    }

    public List<Aluno> listarReprovados(){
        return alunos.stream().filter(aluno -> aluno.getGrade() < 7.0).toList();
    }

    public List<Aluno> listarPorNota(){
        return  alunos.stream().sorted(Comparator.comparing(Aluno::getGrade).reversed()).toList();
    }

    public List<Aluno> listarPorNome(){
        return alunos.stream().sorted(Comparator.comparing(Aluno::getName)).toList();
    }

    public void removerAluno(String name){
        Optional<Aluno> resultado = buscarPorNome(name);
        Aluno alunoEncontrado = resultado.orElseThrow(() -> new AlunoNaoEncontrado("Aluno não encontrado!"));
        alunos.remove(alunoEncontrado);
    }

    public void exportarRelatorio(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio.txt"))){
            writer.write("RELATÓRIO DE ALUNOS");
            writer.newLine();
            writer.newLine();
            writer.write("Aprovados: ");
            writer.newLine();
            writer.newLine();
            for( Aluno aluno : listarAprovados()){
                writer.write(aluno.getName() + " - Idade: "+ aluno.getAge() + " - Nota: " + aluno.getGrade());
                writer.newLine();
            }
            writer.newLine();
            writer.write("Reprovados: ");
            writer.newLine();
            writer.newLine();
            for( Aluno aluno : listarReprovados()){
                writer.write(aluno.getName() + " - Idade: "+ aluno.getAge() + " - Nota: " + aluno.getGrade());
                writer.newLine();
            }
            writer.newLine();
            writer.write(String.format("MÉDIA DA TURMA: %.2f", calcularMedia()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarAluno(String nomeAtual, String novoNome, int novaIdade, double novaNota){
        Optional<Aluno> resultado = buscarPorNome(nomeAtual);
        Aluno alunoEncontrado = resultado.orElseThrow(() -> new AlunoNaoEncontrado("Aluno não encontrado!"));

        if(novoNome.isBlank()){
            throw  new NomeInvalidoException("Nome inválido do aluno!");
        }

        if(novaIdade <= 0){
            throw  new IdadeInvalidaException("Idade inválida!");
        }

        if(novaNota < 0 || novaNota > 10){
            throw new NotaInvalidaException("Nota inválida!");
        }

        Optional<Aluno> nomeDuplicado = buscarPorNome(novoNome);
        if (nomeDuplicado.isPresent() && !nomeDuplicado.get().equals(alunoEncontrado.getName())) {
            throw new AlunoDuplicadoException("Aluno já cadastrado!");
        }

        alunoEncontrado.setName(novoNome);
        alunoEncontrado.setAge(novaIdade);
        alunoEncontrado.setGrade(novaNota);
    }
}