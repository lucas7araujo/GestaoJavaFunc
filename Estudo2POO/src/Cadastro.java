// Importação de Bibliotecas

import java.util.Scanner; // Lê entradas
import java.util.ArrayList; // Cria listas dinâmicas

import java.io.FileWriter; // Criação de arquivos TXT
import java.io.IOException; // Tratamento de Erros

import java.io.FileReader; // Lê arquivos.txt carregados
import java.io.BufferedReader; // Lê o conteúdo do arquivo linha por linha

public class Cadastro {

    public static void SalvarArquivo(ArrayList<Funcionario> lista) {
        try {

            FileWriter arquivo = new FileWriter("funcionarios.txt"); // Criação Arquivo TXT

            for (Funcionario funcionario : lista) {
                arquivo.write("Nome: " + funcionario.nome + "\n");
                arquivo.write("Cargo: " + funcionario.cargo + "\n");
                arquivo.write("Matrícula: " + funcionario.matricula + "\n");
                arquivo.write(String.format("Salário: R$ %.2f\n", funcionario.salario));
                arquivo.write("-----------------------------------------\n");
            }

            arquivo.close();
            System.out.println("\nFuncionários Salvos com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static void LerArquivo() {
        try {
            FileReader fr = new FileReader("funcionarios.txt");

            BufferedReader br = new BufferedReader(fr);

            String linha;
            System.out.println(("\nConteúdo do arquivo: \n"));

            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }

            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        // Instanciação do Scanner e criação da lista de funcionários

        Scanner entrada = new Scanner(System.in);
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();

        int cont = 1; // Contador Matrícula

        // Iniciação do switch

        int menu = 0;

        do {

            System.out.print(
                    "\nDigite\n \n1 para Cadastro;\n2 para Exibição;\n3 para busca por matrícula;\n4 para verificação salarial;\n5 para desligamento;\n6 para atualização;\n7 para salvar dados em arquivo TXT;\n8 para leitura;\n9 para sair:\n  \n-> ");
            menu = entrada.nextInt();

            switch (menu) {
                case 1:
                    System.out.print("\nInforme quantos funcionários serão cadastrados: ");
                    int n = entrada.nextInt();
                    entrada.nextLine();

                    // Cadastro dos funcionários

                    for (int i = 0; i < n; i++) {

                        System.out.println(" ");

                        System.out.print("Informe o nome do funcionário: ");
                        String nome = entrada.nextLine();

                        System.out.print("Informe o cargo do funcionário: ");
                        String cargo = entrada.nextLine();

                        System.out.println("Matrícula do funcionário: " + cont);
                        int matricula = cont;
                        cont++;

                        System.out.print("Informe o salário do funcionário: ");
                        double salario = entrada.nextDouble();

                        entrada.nextLine();

                        // Criação do objeto Funcionario e adição à lista

                        Funcionario funcionario = new Funcionario(nome, cargo, matricula, salario);
                        listaFuncionarios.add(funcionario);
                    }
                    break;

                case 2:
                    System.out.println("\nFuncionários Cadastrados:\n ");

                    // Exibição das informações de cada funcionário usando for-each

                    for (Funcionario funcionario : listaFuncionarios) {
                        funcionario.ExibeInfo();
                        System.out.println(" ");
                    }
                    break;

                case 3:
                    System.out.println(" ");
                    System.out.print("Informe a matrícula do funcionário: ");
                    int busca = entrada.nextInt();

                    boolean encontrado = false;

                    for (Funcionario funcionario : listaFuncionarios) {

                        if (funcionario.matricula == busca) {
                            System.out.println("\nFuncionário Encontrado!");
                            System.out.println(" ");
                            funcionario.ExibeInfo();
                            System.out.println(" ");
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado)
                        System.out.println("Nenhum funcionário foi encontrado! ");
                    break;

                case 4:
                    // Cálculo da média salarial por funcionário

                    double mediasalario = 0;

                    for (Funcionario funcionario : listaFuncionarios) {
                        mediasalario += funcionario.salario;
                    }

                    mediasalario /= listaFuncionarios.size();

                    System.out.printf("\nMédia dos salários -> %.2f\n", mediasalario);

                    System.out.println("\nFuncionários com salário acima da média:\n ");

                    // Verificação de quais funcionários possuem salário superior à media salarial
                    // utilizando o for-each

                    for (Funcionario funcionario : listaFuncionarios) {
                        if (funcionario.salario > mediasalario) {
                            funcionario.ExibeInfo();
                            System.out.println(" ");
                        }
                    }
                    break;

                case 5:
                    // Desligamento de funcionário

                    System.out.print("Informe a matrícula do funcionário desligado: ");
                    int desligamento = entrada.nextInt();

                    Funcionario funcionarioRemovido = null; // Variável auxiliar para remoção do funcionário

                    for (Funcionario funcionario : listaFuncionarios) {
                        if (funcionario.matricula == desligamento) {
                            funcionarioRemovido = funcionario;
                        }
                    }
                    if (funcionarioRemovido != null) {
                        listaFuncionarios.remove(funcionarioRemovido);
                        System.out.println("Funcionário removido com sucesso!");
                    } else
                        System.out.println("Funcionário não encontrado.");
                    break;

                case 6:
                    // Edição Funcionário

                    System.out.print("\nInforme a matrícula do funcionário que recebeu atualização: ");
                    int atualizar = entrada.nextInt();
                    entrada.nextLine();

                    boolean achou = false; // Sistema para verificação de matrícula;

                    for (Funcionario funcionario : listaFuncionarios) {
                        if (atualizar == funcionario.matricula) {
                            System.out.print("\nInforme o novo cargo: ");
                            funcionario.cargo = entrada.nextLine();

                            System.out.print("Informe o novo salário: ");
                            funcionario.salario = entrada.nextInt();

                            achou = true;
                            break;
                        }
                    }

                    if (achou)
                        System.out.println("\nFuncionário Atualizado!");
                    else
                        System.out.println("\nFuncionário Não Encontrado!");
                    break;

                case 7:
                    SalvarArquivo(listaFuncionarios);
                    break;

                case 8:
                    LerArquivo();
                    break;

                default:
                    break;
            }

        } while (menu != 8);

        entrada.close();

    }
}
