package com.example.salesianos.triana.Tarea1psp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MonumentoController {

    private final MonumentoRepository monumentoRepository;

    @GetMapping("/monument")
    public ResponseEntity<List<Monumento>> getAllProducts() {
        List<Monumento> result = monumentoRepository.findAll();
        if (result.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/monument/{id}")
    public ResponseEntity<Monumento> getProductById(@PathVariable Long id) {
        Optional<Monumento> m;
        m = monumentoRepository.findById(id);
        if (m.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(m.get());
    }

    @PostMapping("/monument")
    public ResponseEntity<Monumento> addMonument(@RequestBody Monumento monument) {
        if (StringUtils.hasText(monument.getNombre())){
            return ResponseEntity.status(201)
                    .body(monumentoRepository.save(monument));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/monument/{id}")
    public ResponseEntity<Monumento> updateMonument(
            @PathVariable Long id,
            @RequestBody Monumento monument) {
        return monumentoRepository.findById(id)
                .map(monumento -> {
                    monument.setCodigoPais(monument.getCodigoPais());
                    return ResponseEntity.ok(monumentoRepository.save(monument));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        monumentoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
