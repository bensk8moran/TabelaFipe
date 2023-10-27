package br.com.ProjetoTabelaFipe.main;
import br.com.ProjetoTabelaFipe.models.DateFipe;
import br.com.ProjetoTabelaFipe.models.Modelos;
import br.com.ProjetoTabelaFipe.models.Veiculo;
import br.com.ProjetoTabelaFipe.services.ConsumoAPI;
import br.com.ProjetoTabelaFipe.services.ConverteDados;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//classe principal
public class Main {
    //aqui vamos fazer o metodo exebir menu
    Scanner teclado = new Scanner(System.in);

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final String  URL_Base = "https://parallelum.com.br/fipe/api/v1/";
    public void exebirMenu(){
        var menu = """
                *** Opcções ***
                
                Carro
                Moto
                Caminhão

                Digite uma das opções para consulta:
                
                """;
        System.out.println(menu);
        var opcao = teclado.nextLine();

        String endereco;

        if(opcao.toLowerCase().contains("carr")){
            endereco = URL_Base + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_Base + "motos/marcas";
        } else {
            endereco = URL_Base + "caminhoes/marcas";
        }
        var json = consumoAPI.obterDados(endereco);
        System.out.println(json);
        var marcas = conversor.obterList(json, DateFipe.class);
        marcas.stream()
                .sorted(Comparator.comparing(DateFipe::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o codigo da marca para a consulta: ");
        var codigoMarca = teclado.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumoAPI.obterDados(endereco);
        var modelosLista = conversor.obterDados(json, Modelos.class);
        System.out.println("\nModelos dessa marca:");
        modelosLista.modelos().stream()
                .sorted(Comparator.comparing(DateFipe::codigo))
                .forEach(System.out::println);


        //aqui vamos aplicar apenas uma busca pelo nome do veiculo
        System.out.println("Digite o nome do carro a ser buscado");
        var nomeVeiculo = teclado.nextLine();
        List<DateFipe> modelosFiltrados = modelosLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toUpperCase()))
                .collect(Collectors.toList());//aqui é uma noa lista de modelos veiculos
        System.out.println("\nmodelos filtrados");
        modelosFiltrados.forEach(System.out::println);
        //aqui vamos imprimir os modelos filtrados
        System.out.println("digite o codigo do modelo");
        var codigoModelo = teclado.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumoAPI.obterDados(endereco);
        List <DateFipe> anos = conversor.obterList(json, DateFipe.class);
        //aqui vamos representar os anos  da lista codigoModelo ou seja vamos devolver vários dados.

        //Aqui vamos aplicar o record Veiculos e os retornos do JSON
        List<Veiculo> veiculos = new ArrayList<>();
        for ( int i =0; i < anos.size() ; i++){
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumoAPI.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        System.out.println("\nTodos os veículos encontrados por ano: ");
        veiculos.forEach(System.out::println);
    }
}
