package repositorios.estacionamento;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class RepositorioCsvBase<T> {

    private final String caminhoArquivo;
    protected static final String DELIMITADOR = ";";

    public RepositorioCsvBase(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    protected abstract String cabecalho();
    protected abstract String serializar(T entidade);
    protected abstract T desserializar(String[] campos);

    protected void salvarNoArquivo(T entidade){
        try{
            Path caminho = Paths.get(this.caminhoArquivo);
            if (!Files.exists(caminho)){
                Files.write(caminho, List.of(this.cabecalho(), this.serializar(entidade)));
            } else {
                Files.write(caminho, List.of(this.serializar(entidade)),
                        StandardOpenOption.APPEND);
            }
        } catch (IOException e){
            throw new RuntimeException("[REPO] Erro ao salvar arquivo" + e.getMessage());
        }
    }

    protected List<T> lerTodos() {
        try {
            Path caminho = Paths.get(caminhoArquivo);
            if (!Files.exists(caminho)) return new ArrayList<>();

            return Files.readAllLines(caminho).stream()
                    .skip(1) // pula cabeçalho
                    .map(linha -> desserializar(linha.split(DELIMITADOR, -1)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("[REPO] Erro ao ler arquivo", e);
        }
    }

    protected void reescrever(List<T> entidades) {
        try {
            List<String> linhas = new ArrayList<>();
            linhas.add(cabecalho());
            entidades.forEach(e -> linhas.add(serializar(e)));
            Files.write(Paths.get(caminhoArquivo), linhas);
        } catch (IOException e) {
            throw new RuntimeException("[REPO] Erro ao reescrever arquivo", e);
        }
    }
}
