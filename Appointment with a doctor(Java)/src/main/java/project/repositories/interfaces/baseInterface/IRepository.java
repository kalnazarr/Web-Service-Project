package project.repositories.interfaces.baseInterface;

import java.util.List;

public interface IRepository<E> {
    boolean create(E user);

    E get(int id);

    List<E> getAll();

    boolean delete(int id);

    boolean login(String username, String password);


}