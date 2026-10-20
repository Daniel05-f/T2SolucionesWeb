package com.lezama_perez.t2soluciones.service.implement;

import com.lezama_perez.t2soluciones.model.TourPackage;
import com.lezama_perez.t2soluciones.repository.IGenericRepository;
import com.lezama_perez.t2soluciones.repository.ITourPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourPackageService extends GenericService<TourPackage, Integer> implements ITourPackageService {

    private final ITourPackageRepository repo;

    @Override
    protected IGenericRepository<TourPackage, Integer> getRepo() {
        return repo;
    }
}
