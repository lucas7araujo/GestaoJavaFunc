// Classe principal do projeto

public class Funcionario {

    // Atributos

    String nome;
    String cargo;
    int matricula;
    double salario;

    // Criação do Objeto

    public Funcionario(String nome, String cargo, int matricula, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.matricula = matricula;
        this.salario = salario;
    }

    // Procedimento para exibir as informações

    public void ExibeInfo() {
        System.out.println("Nome: " + nome);
        System.out.println("Cargo: " + cargo);
        System.out.println("Matrícula: " + matricula);
        System.out.printf("Salário: R$ %.2f\n", salario);
    }
}
