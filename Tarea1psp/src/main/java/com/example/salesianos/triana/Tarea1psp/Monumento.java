package com.example.salesianos.triana.Tarea1psp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Monumento {

    @id @GeneratedValue
    private long id;
    private String codigoPais;
    private String nombrePais;
    private String nombreCiudad;
    private long localizacion;
    private String nombre;
    private String descripcion;
    private String url;

}
