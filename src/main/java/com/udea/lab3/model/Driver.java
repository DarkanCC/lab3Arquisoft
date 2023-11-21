package com.udea.lab3.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@ApiModel(description = "Atributos del conductor")
public class Driver implements Serializable {
    @ApiModelProperty(notes = "ID del conductor en la BD")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_conductor")
    private Long idDriver;

    @ApiModelProperty(notes = "Nombre del conductor")
    @Column(name = "nombre", nullable = false, length = 80)
    private @NonNull String name;

    @ApiModelProperty(notes = "Ciudad en que se encuentra el conductor")
    @Column(name = "ciudad_actual", nullable = false, length = 80)
    private @NonNull String city;

    @ApiModelProperty(notes = "Correo del conductor")
    @Column(name = "email", nullable = false, length = 80)
    private @NonNull String email;

    @ApiModelProperty(notes = "Numero de telefono del conductor")
    @Column(name = "celular", nullable = false, length = 80)
    private @NonNull int phone;

    @ApiModelProperty(notes = "Calificacion promedio del conductor")
    @Column(name = "calificacion", nullable = false)
    @Min(value = 0, message = "Rating should be more or equal than 0")
    @Max(value = 5, message = "Rating should be less or equal than 5")
    private @NonNull float rating;

    @ApiModelProperty(notes = "Cedula del conductor")
    @Column(name = "cedula", nullable = false, length = 20)
    private @NonNull long cedula;

    @ApiModelProperty(notes = "Placa del vehículo del conductor")
    @Column(name = "placa", nullable = false, length = 10)
    private @NonNull String plate;

    @ApiModelProperty(notes = "Numero de servicios hechos por el conductor")
    @Column(name = "nro_servicios", nullable = false)
    private @NonNull int services;

    @ApiModelProperty(notes = "Numero de felicitaciones del conductor")
    @Column(name = "nro_felicitaciones", nullable = false)
    private @NonNull int congrats;

    @ApiModelProperty(notes = "Numero de amonestaciones del conductor")
    @Column(name = "nro_amonestaciones", nullable = false)
    private @NonNull int complaints;
}
