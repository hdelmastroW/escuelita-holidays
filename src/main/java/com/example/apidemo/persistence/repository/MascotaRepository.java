package com.example.apidemo.persistence.repository;

import com.example.apidemo.persistence.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    Optional<Mascota> findByNombre(String nombre);
    List<Mascota> findByNombreAndVacunado(String nombre, Boolean vacunado);
}
