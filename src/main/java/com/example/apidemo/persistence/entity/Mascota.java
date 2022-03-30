package com.example.apidemo.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name="mascota")
@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmascota", nullable = false)
    private Long id;


    @Column(name="nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name="propietario")
    private Cliente propietario;

    @Column(name="vacunado")
    private Boolean vacunado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public void setPropietario(Cliente propietario) {
        this.propietario = propietario;
    }

    public Boolean getVacunado() {
        return vacunado;
    }

    public void setVacunado(Boolean vacunado) {
        this.vacunado = vacunado;
    }
}
