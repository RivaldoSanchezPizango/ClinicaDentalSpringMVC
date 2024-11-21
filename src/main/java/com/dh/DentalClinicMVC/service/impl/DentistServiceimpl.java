package com.dh.DentalClinicMVC.service.impl;

import com.dh.DentalClinicMVC.entity.Dentist;
import com.dh.DentalClinicMVC.exception.ResourceNotFoundException;
import com.dh.DentalClinicMVC.repository.IDentistRepository;
import com.dh.DentalClinicMVC.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistServiceimpl implements IDentistService {

    private IDentistRepository dentistRepository;

    @Autowired
    public DentistServiceimpl(IDentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    public Dentist save(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    @Override
    public Optional<Dentist> findByid(Long id) {
        return dentistRepository.findById(id);
    }

    @Override
    public void update(Dentist dentist) {
        dentistRepository.save(dentist);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        // vamos a buscar por ID el odontologo y si no existe vamos a lanzar la excepcion

        // vamos a buscar el primer odontologo por id
        Optional<Dentist> dentistToLookFor = findByid(id);

        if (dentistToLookFor.isPresent()) {
            dentistRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se pudo eliminar el odontologo con id: " + id);
        }
    }

    @Override
    public List<Dentist> findAll() {
        return dentistRepository.findAll();
    }

    @Override
    public Optional<Dentist> findByRegistration(Integer registration) {
        return dentistRepository.findByRegistration(registration);
    }
}
