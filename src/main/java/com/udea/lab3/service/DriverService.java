package com.udea.lab3.service;

import com.udea.lab3.dao.IDriverDAO;
import com.udea.lab3.exception.DriverNotFoundException;
import com.udea.lab3.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private IDriverDAO dao;

    public Driver save(Driver t) {
        return dao.save(t);
    }

    public String delete(long id) {
        dao.deleteById(id);
        return "Conductor eliminado";
    }

    public Iterable<Driver> list() {
        return dao.findAll();
    }

    public Optional<Driver> listId(long id) {
        return dao.findById(id);
    }

    public Driver update(Driver t) {
        Driver existingDriver = dao.findById(t.getIdDriver()).orElse(null);
        existingDriver.setName(t.getName());
        existingDriver.setCity(t.getCity());
        existingDriver.setEmail(t.getEmail());
        existingDriver.setPhone(t.getPhone());
        existingDriver.setPlate(t.getPlate());
        existingDriver.setRating(t.getRating());
        existingDriver.setServices(t.getServices());
        existingDriver.setComplaints(t.getComplaints());
        existingDriver.setCongrats(t.getCongrats());
        return dao.save(existingDriver);
    }

    public List<Driver> viewBestDrivers() throws DriverNotFoundException {
        List<Driver> drivers = dao.viewBestDrivers();
        if (!drivers.isEmpty()) {
            return drivers;
        } else {
            throw new DriverNotFoundException("No drivers found with rating >= 4");
        }
    }

    public List<Driver> findByCiudad(String city) {
        List<Driver> drivers = dao.findByCiudad(city);
        if (!drivers.isEmpty()) {
            return drivers;
        } else {
            throw new DriverNotFoundException("No hay conductores disponibles en " + city);
        }
    }
}

