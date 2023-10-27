package br.com.ProjetoTabelaFipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(List<DateFipe> modelos) {
    //Nesta classe vamos modelar os modelos dos veiculos e implementar uma lista DateFipe
}
