package bugtrackingsystem.service;

import java.util.List;

public interface Repository<T> {
  List<T> getAll();

  T getById(int id);

  void save(T item);

  void delete(int id);
}
