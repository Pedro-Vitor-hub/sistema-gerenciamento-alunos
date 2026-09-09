    package org.example;

    import java.util.Optional;

    public class Main {
        public static void main(String[] args) {
           AlunoDAO teste = new AlunoDAO();
           Aluno aluno1 = new Aluno(1,"Pedro",26,9.5);
           teste.atulizarAluno(aluno1);
           teste.removerAluno(1);
        }
    }
