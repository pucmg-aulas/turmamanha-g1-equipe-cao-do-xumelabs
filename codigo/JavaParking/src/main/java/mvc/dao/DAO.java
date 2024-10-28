package mvc.dao;

import java.util.List;

public interface DAO<T> {
    void cadastrar(T obj);
    void remover(T obj);
    void atualizar(T objAntigo, T objNovo);
    List<T> listarTodos();
}
