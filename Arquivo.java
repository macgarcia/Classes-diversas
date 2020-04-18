package com.macgarcia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author macgarcia
 */
public final class Arquivo {

    private String caminho;
    private List<String> conteudoDoArquivo;

    private void lerArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            conteudoDoArquivo = new ArrayList<>();
            reader.lines().forEach(l -> conteudoDoArquivo.add(l));
        } catch (IOException ex) {
        }
    }

    private void escreveNoArquivo(List<String> conteudo) {
        if (conteudoDoArquivo == null) {
            conteudoDoArquivo = new ArrayList<>();
        }
        conteudo.forEach(e -> conteudoDoArquivo.add(e));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (String s : conteudoDoArquivo) {
                writer.append(s).append("\n");
            }
            writer.close();
        } catch (IOException ex) {
        }
    }

    private void criarArquivo() {
        try {
            File file = new File(caminho);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ex) {
        }
    }

    //=======================================================================//
    
    /**
     * @param conteudo -> Conteudo a ser escrito no arquivo    
     * @param caminhoArquivo -> Local até o arquivo
     */
    public void escreverNoArquivo(String conteudo, String caminhoArquivo) {
        this.caminho = caminhoArquivo;
        criarArquivo();
        lerArquivo();
        escreveNoArquivo(Arrays.asList(conteudo));
    }

    public void escreverNoArquivo(List<String> conteudo, String caminhoArquivo) {
        this.caminho = caminhoArquivo;
        criarArquivo();
        lerArquivo();
        escreveNoArquivo(conteudo);
    }

    public List<String> lerDoArquivo(String caminhoArquivo) {
        this.caminho = caminhoArquivo;
        lerArquivo();
        return conteudoDoArquivo;
    }

}
