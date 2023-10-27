package br.com.ProjetoTabelaFipe.services;

import java.util.List;

public interface ConvertDate  {
    <T> T obterDados(String json, Class<T> classe);
    <T> List <T> obterList(String json, Class<T>tClass);
}
