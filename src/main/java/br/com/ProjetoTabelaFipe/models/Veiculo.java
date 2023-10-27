package br.com.ProjetoTabelaFipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
        @JsonAlias("Valor") String Valor,
        @JsonAlias("Marca") String Marca,
        @JsonAlias("Modelo") String Modelo,
        @JsonAlias("AnoModelo") Integer Ano,
        @JsonAlias("Combustivel") String tipoCombustivel
) {
}
