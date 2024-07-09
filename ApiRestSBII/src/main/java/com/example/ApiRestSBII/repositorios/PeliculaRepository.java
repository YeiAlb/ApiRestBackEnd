package com.example.ApiRestSBII.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiRestSBII.modelos.Pelicula;

//JpaRepository<T, ID> necesita genéricos. Tipo que manejamos: película que hay que importar porque está en otro paquete.
@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{

}
