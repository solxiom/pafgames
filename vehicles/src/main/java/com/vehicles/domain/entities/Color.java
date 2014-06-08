

package com.vehicles.domain.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author kavan
 */
@Document
public class Color {
    @Id
    private com.vehicles.domain.enums.ColorName name;

    public Color(com.vehicles.domain.enums.ColorName name) {
        this.name = name;
    }

    public com.vehicles.domain.enums.ColorName getName() {
        return name;
    }

    public void setName(com.vehicles.domain.enums.ColorName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Color{" + "name=" + name + '}';
    }   
}
