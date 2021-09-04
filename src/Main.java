import java.util.Scanner;

public class Main{
    /**
     * Integrantes do Trabalho: Reynaldo Villar Garavini, Thiago Amado Costa
     */
    public static void main(String[] args) {

        try {
            CRUD crud = new CRUD();
            Scanner in = new Scanner(System.in);
            int menu;

            do{
                System.out.println("\n1- Inserir Cliente");
                System.out.println("2- Imprimir Cliente");
                System.out.println("3- Atualizar Cliente");
                System.out.println("4- Deletar Cliente");
                System.out.println("5- Imprimir Todos");
                System.out.println("0- Sair\n");
                System.out.print("Opção: ");
                menu = in.nextInt();
                //avança para proxima linha
                in.nextLine();
                System.out.print("\n");

                switch(menu) {
                    //Inserir
                    case 1: 
                        //ler cpf
                        System.out.print("CPF..: ");
                        String cpf1 = in.nextLine();
                        System.out.print("\n");
                        //ler nome
                        System.out.print("Nome.: ");
                        String nome1 = in.nextLine();
                        System.out.print("\n");
                        //ler email
                        System.out.print("Email: ");
                        String email1 = in.nextLine();
                        System.out.print("\n");

                        //cria objeto cliente
                        Cliente c1 = new Cliente(-1, cpf1, nome1, email1);
                        
                        //função inserir do CRUD passando o novo cliente como parâmetro
                        crud.create(c1);

                        break;
                    //Imprimir
                    case 2:
                        //ler id
                        System.out.print("ID: ");
                        int id2 = in.nextInt();
                        System.out.print("\n");

                        //função imprimir do CRUD
                        Cliente c2 = crud.read(id2);

                        if(c2 != null) {
                            //imprime informações do cliente na tela
                            System.out.println(c2.imprimeCliente());                            
                        } else {
                            //mensagem de erro
                            System.out.println("Cliente de ID " + id2 + " não existe");
                        }
                        break;
                    //Atualizar
                    case 3: 
                        //ler id
                        System.out.print("ID...: ");
                        int id3 = in.nextInt();
                        System.out.print("\n");
                        //avança para proxima linha
                        in.nextLine();
                        //ler cpf
                        System.out.print("CPF..: ");
                        String cpf3 = in.nextLine();
                        System.out.print("\n");
                        //ler nome
                        System.out.print("Nome.: ");
                        String nome3 = in.nextLine();
                        System.out.print("\n");
                        //ler email
                        System.out.print("Email: ");
                        String email3 = in.nextLine();
                        System.out.print("\n");

                        //cria objeto cliente
                        Cliente c3 = new Cliente(id3, cpf3, nome3, email3);

                        //função atualizar CRUD passando o novo cliente como parâmetro
                        crud.update(c3);

                        break;
                    //Deletar
                    case 4:
                        //ler id
                        System.out.print("ID: ");
                        int id4 = in.nextInt();
                        System.out.print("\n");

                        //função delete CRUD
                        boolean deletado = crud.delete(id4);

                        if(deletado) {
                            System.out.println("Cliente deletado com sucesso!");
                        } else {
                            System.out.println("Erro ao deletar cliente!");
                        }

                        break;

                    case 5:
                        //função ler todos os clientes do CRUD
                        crud.readAll();
                        break;
                    //Sair
                    case 0: 
                        //mensagem de saída do programa
                        System.out.println("Programa Encerrado!");
                        break;
                    //Erro - Número Inválido
                    default:
                        System.out.println("Número Inválido!");
                        break;
                }

            } while(menu!=0);

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}