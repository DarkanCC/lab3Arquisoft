package com.udea.lab3.dao;

import com.udea.lab3.model.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IDriverDAO extends CrudRepository<Driver, Long> {
    @Query("FROM Driver d WHERE d.rating >= 4")
    public List<Driver> viewBestDrivers();

    @Query("FROM Driver d WHERE d.city=:city")
    public List<Driver> findByCiudad(@Param("city") String city);
}
