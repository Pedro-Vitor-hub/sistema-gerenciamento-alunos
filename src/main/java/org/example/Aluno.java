package org.example;

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
        this.id = id;
        this.name = nome;
        this.age = age;
        this.grade = grade;
    }

    Aluno(String nome, int age, double grade) {
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

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name;}

    public void setAge(int age) { this.age = age; }

    public void setGrade(double grade) { this.grade = grade; }


    @Override
    public String toString() {
        return "Aluno {nome= " + name + ", idade=" + age + ", nota=" + grade + "}";
    }
}
