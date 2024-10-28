package mvc.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {

    private List<T> listaObjetos;
    private String filePath;

    // Construtor para definir o caminho do arquivo
    public AbstractDAO(String filePath) {
        this.filePath = filePath;
        this.listaObjetos = new ArrayList<>();
        carregarObjetos();
    }

    @Override
    public void cadastrar(T obj) {
        listaObjetos.add(obj);
        salvarObjetos();
    }

    @Override
    public void remover(T obj) {
        listaObjetos.remove(obj);
        salvarObjetos();
    }

    @Override
    public void atualizar(T objAntigo, T objNovo) {
        int index = listaObjetos.indexOf(objAntigo);
        if (index >= 0) {
            listaObjetos.set(index, objNovo);
            salvarObjetos();
        }
    }

    @Override
    public List<T> listarTodos() {
        return listaObjetos;
    }

    // Método para salvar os objetos no arquivo .dat
    protected void salvarObjetos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(listaObjetos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar objetos: " + e.getMessage());
        }
    }

    // Método para carregar os objetos do arquivo .dat
    @SuppressWarnings("unchecked")
    private void carregarObjetos() {
        File arquivo = new File(filePath);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                listaObjetos = (List<T>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar objetos: " + e.getMessage());
            }
        }
    }
}
