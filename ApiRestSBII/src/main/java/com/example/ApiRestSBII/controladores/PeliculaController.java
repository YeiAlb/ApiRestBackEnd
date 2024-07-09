package com.example.ApiRestSBII.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiRestSBII.modelos.Pelicula;
import com.example.ApiRestSBII.repositorios.PeliculaRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PeliculaController {

    PeliculaRepository repositorio;
    
    public PeliculaController(PeliculaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/api/peliculas")
    public List<Pelicula> obtenerPeliculas() {
        return repositorio.findAll();
    }
    
    @GetMapping("/api/pelicula/{id}")
    public ResponseEntity<Pelicula> obtenerPelicula(@PathVariable Long id) {
        Optional<Pelicula> opt = repositorio.findById(id);
        
        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().build();            
        } else {
            return ResponseEntity.ok(opt.get());
        }
    }
    
    @PostMapping("/api/peliculas")
    public ResponseEntity<Pelicula> guardarPelicula(@RequestBody Pelicula pelicula) {
        if (pelicula.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        repositorio.save(pelicula);
        return ResponseEntity.ok(pelicula);
    }
    
    @PutMapping("/api/peliculas")
    public ResponseEntity<Pelicula> actualizarPelicula(@RequestBody Pelicula pelicula) {
        if (pelicula.getId() == null || !repositorio.existsById(pelicula.getId())) {
            return ResponseEntity.badRequest().build();
        }
        repositorio.save(pelicula);
        return ResponseEntity.ok(pelicula);
    }
    
    @DeleteMapping("/api/pelicula/{id}")
    public ResponseEntity<Void> borrarPelicula(@PathVariable Long id) {
        if (id == null || !repositorio.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}