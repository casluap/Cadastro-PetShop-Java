
import javax.swing.JOptionPane;

public class Principal {

    public static CadastroPet leitura(String mensagem) {
        if (!mensagem.equals("")) {
            JOptionPane.showMessageDialog(null, mensagem);
        }
        CadastroPet pet = new CadastroPet();

        pet.setNome(JOptionPane.showInputDialog("Digite o nome do Pet"));
        pet.setRaca(JOptionPane.showInputDialog("Digite a raça"));
        pet.setPeso(Double.parseDouble(JOptionPane.showInputDialog("Digite o peso")));
        pet.setSexo(JOptionPane.showInputDialog("Macho ou Fêmea?"));
        pet.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o código de cadastro")));
        return pet;
    }

    public static void menuExcluir(SistemaPet cadastro) {
        int opcao = -1;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Sistema de cadastro de pet's - excluir"
                    + "\n 1 Chave"
                    + "\n 2 Descritivo"
                    + "\n 3 Voltar  "));

            switch (opcao) {
                case 1: {
                    int chaveExcluir = Integer.parseInt(JOptionPane.showInputDialog("Digite a matrícula para excluir cadastro: "));
                    cadastro.excluirChave(chaveExcluir);
                    break;
                }

                case 2: {
                    String descritivoExcluir = JOptionPane.showInputDialog("Digite o nome para excluir o cadastro: ");
                    cadastro.excluirDescritivo(descritivoExcluir);
                    break;
                }

            }
        } while (opcao != 3);
    }

    public static void menuAlterar(SistemaPet cadastro) {
        int opcao = -1;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Sistema de cadastro de pet's - alterar"
                    + "\n 1 Chave"
                    + "\n 2 Descritivo"
                    + "\n 3 Voltar  "));

            switch (opcao) {
                case 1: {
                    int chaveAlterar = Integer.parseInt(JOptionPane.showInputDialog(" Digite o código do pet a ser atualizado: "));
                    CadastroPet pet = leitura("Digite os novos dados do Pet");
                    if (cadastro.alterarChave(chaveAlterar, pet) == true) {
                        JOptionPane.showMessageDialog(null, "Registro " + chaveAlterar + " atualizado com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registro " + chaveAlterar + " não foi atualizado.");
                    }
                    break;
                }
                case 2: {
                    String descritivoAlterar = JOptionPane.showInputDialog(" Digite o código do pet a ser atualizado: ");
                    CadastroPet pet = leitura("Digite os novos dados do Pet");
                    if (cadastro.alterarDescritivo(descritivoAlterar, pet) == true) {
                        JOptionPane.showMessageDialog(null, "Registro " + descritivoAlterar + " atualizado com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registro " + descritivoAlterar + " não foi atualizado.");
                    }
                    break;
                }
            }
        } while (opcao != 3);
    }

    public static void menuConsultar(SistemaPet cadastro) {
        int opcao = -1;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Sistema de cadastro de pet's - consultar"
                    + "\n 1 Chave"
                    + "\n 2 Descritivo"
                    + "\n 3 Voltar  "));

            switch (opcao) {
                case 1: {
                    int chaveConsultar = Integer.parseInt(JOptionPane.showInputDialog(" Digite o código do Pet a ser consultado: "));
                    CadastroPet pet = cadastro.consultarChave(chaveConsultar);
                    if (pet != null) {
                        JOptionPane.showMessageDialog(null, "Pet consultado: \n" + pet.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Não Achei");
                    }
                    break;
                }
                case 2: {
                    String descritivoConsultar = JOptionPane.showInputDialog(" Digite o nome do Pet a ser consultado: ");
                    CadastroPet pet = cadastro.consultarDescritivo(descritivoConsultar);
                    if (pet != null) {
                        JOptionPane.showMessageDialog(null, "Pet consultado: \n" + pet.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Não Achei");
                    }

                    break;
                }

            }
        } while (opcao != 3);

    }

    public static void menuPropriedades(SistemaPet cadastro) {
        int opcao = -1;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Sistema de cadastro de pet's - propriedades"
                    + "\n 1 Tamanho do arquivo (bytes)"
                    + "\n 2 Quantidade de Pet's"
                    + "\n 3 Limpar registros"
                    + "\n 4 Voltar"));

            switch (opcao) {
                case 1: {
                    cadastro.tamanhoArquivoBytes(cadastro);
                    break;
                }

                case 2: {
                    cadastro.quantidadeCadastro();
                    JOptionPane.showMessageDialog(null, "Existem " + cadastro.quantidadeCadastro() + " Pet's cadastrados no sistema");
                    break;
                }

                case 3: {
                    if (cadastro.limparRegistros() == true) {
                        JOptionPane.showMessageDialog(null, "Registros zerados com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Os registros não foram zerado!");
                    }
                    break;
                }
            }
        } while (opcao != 4);
    }

    public static void main(String Arg[]) {

        SistemaPet cadastro = new SistemaPet();

        int opcao = -1;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Sistema de cadastro de Pet's \n"
                    + " 1 - Incluir \n "
                    + " 2 - Excluir \n "
                    + " 3 - Alterar\n "
                    + " 4 - Consultar \n"
                    + " 5 - Listar Lógico\n "
                    + " 6 - Listar Físico \n "
                    + " 7 - Propriedades \n "
                    + "99 - Sair\n"));

            switch (opcao) {
                case 1: {
                    CadastroPet pet = leitura("");
                    if (cadastro.inserir(pet) == true) {
                        JOptionPane.showMessageDialog(null, "Pet cadastrado com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cadastro não realizado.");
                    }
                    break;
                }

                case 2: {
                    menuExcluir(cadastro);
                    break;
                }

                case 3: {
                    menuAlterar(cadastro);
                }

                case 4: {
                    menuConsultar(cadastro);
                }

                case 5: {
                    String saidalogico = cadastro.listarLogico();
                    JOptionPane.showMessageDialog(null, "Lista Lógico:\n" + saidalogico);
                    break;
                }
                case 6: {
                    String saidafisico = cadastro.listarFisico();
                    JOptionPane.showMessageDialog(null, "Lista Físico:\n" + saidafisico);
                    break;
                }

                case 7: {
                    menuPropriedades(cadastro);
                }

                case 99: {
                    System.out.println("Saindo do Sistema!");
                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                    break;
                }
            }
        } while (opcao != 99);
    }

}
