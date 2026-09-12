    package org.example;

    public class Main {
        public static void main(String[] args) {
           AlunoService alunoService = new AlunoService(new AlunoDAO());
            Aluno aluno = new Aluno("asfdfa",15, 9.0);
        }
    }
