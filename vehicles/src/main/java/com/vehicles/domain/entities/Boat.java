/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.domain.entities;

import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.domain.enums.VehicleType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author kavan
 */
@Document
public class Boat implements Vehicle {

    @Id
    private String id;
    private Color color;
    private final VehicleType vehicleType;
    
    private boolean floats;
    private int periscopes;

    public Boat() {
        this.vehicleType = VehicleType.BICYCLE;
        this.floats = true;
        this.periscopes = 0;
    }

    public Boat(Color color, boolean floats, int periscopes) {
        this();
        this.color = color;
        this.floats = floats;
        this.periscopes = periscopes;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    

    public boolean isFloats() {
        return floats;
    }

    public void setFloats(boolean floats) {
        this.floats = floats;
    }

    public int getPeriscopes() {
        return periscopes;
    }

    public void setPeriscopes(int periscopes) {
        if (this.floats == false) {
            this.periscopes = periscopes;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Boat{" + "floats=" + floats + ", periscopes=" + periscopes + '}';
    }
    

}
