/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pafgames.domain.entities;

import java.util.Date;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author kavan
 */
@Document
public class Feedback {
    
    private UUID uniqueFeedbackNumber;
    private Date date;
    private String text;
    private double stars;

    public UUID getUniqueFeedbackNumber() {
        return uniqueFeedbackNumber;
    }

    public void setUniqueFeedbackNumber(UUID uniqueFeedbackNumber) {
        this.uniqueFeedbackNumber = uniqueFeedbackNumber;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Feedback{" + "uniqueFeedbackNumber=" + uniqueFeedbackNumber + ", date=" + date + ", text=" + text + ", stars=" + stars + '}';
    }
    
    
}
