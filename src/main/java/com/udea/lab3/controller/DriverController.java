package com.udea.lab3.controller;

import com.udea.lab3.exception.DriverNotFoundException;
import com.udea.lab3.exception.InvalidRating;
import com.udea.lab3.exception.ModelNotFoundException;
import com.udea.lab3.model.Driver;
import com.udea.lab3.service.DriverService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/driver")
@CrossOrigin("*")
@Api(value = "Driver Management System", description = "Operations to drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @ApiOperation(value = "Agregar conductor")
    @PostMapping("/save")
    public long save(
            @ApiParam(value = "Objeto de tipo Driver que se almacenará en la BD", required = true)
            @RequestBody Driver driver) throws InvalidRating {
        if (driver.getRating() > 5) {
            throw new InvalidRating("Rating should be less or equal than 5");
        }
        driverService.save(driver);
        return driver.getIdDriver();
    }

    @ApiOperation(value = "Mostrar todos los conductores", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the source"),
            @ApiResponse(code = 403, message = "The source that you are trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Driver not found")
    })
    @GetMapping("/listAll")
    public Iterable<Driver> listAllDrivers() {
        return driverService.list();
    }

    @ApiOperation(value = "Mostrar un conductor por su ID")
    @GetMapping("/list/{id}")
    public Driver listDriverByID(@ApiParam(value = "Ingrese el ID del conductor a buscar", required = true)
                                 @PathVariable("id") int id) {
        Optional<Driver> driver = driverService.listId(id);
        if (driver.isPresent()) {
            return driver.get();
        }
        throw new ModelNotFoundException("ID de conductor invalida");
    }

    @ApiOperation(value = "Muestra los mejores conductores")
    @GetMapping("/topDrivers")
    public ResponseEntity<List<Driver>> viewBestDrivers() {
        List<Driver> list = driverService.viewBestDrivers();
        return new ResponseEntity<List<Driver>>(list, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Filtrar por ciudad")
    @GetMapping("/byCity/{city}")
    public ResponseEntity<List<Driver>> findByCiudad(@PathVariable("city") String city) {
        List<Driver> drivers = driverService.findByCiudad(city);
        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Actualizar datos de un conductor")
    @PutMapping
    public Driver updateService(@RequestBody Driver driver) {
        return driverService.update(driver);
    }

    @ApiOperation(value = "Eliminar conductor")
    @DeleteMapping("/{id}")
    public String deleteDriver(@PathVariable long id) {
        return driverService.delete(id);
    }
}
