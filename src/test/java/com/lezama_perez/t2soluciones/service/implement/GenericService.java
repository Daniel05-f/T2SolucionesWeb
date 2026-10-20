package com.lezama_perez.t2soluciones.service.implement;

import com.lezama_perez.t2soluciones.repository.IGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static org.aspectj.apache.bcel.Repository.getRepository;

public abstract class GenericService <T, ID> implements IGenericService<T, ID>{
    // Método abstracto que obliga a las clases hijas a proveer su repositorio
    protected abstract IGenericRepository <T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(ID id, T t) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new Exception("No such id : " + id));
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
