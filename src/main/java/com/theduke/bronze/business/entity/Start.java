package com.theduke.bronze.business.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Christoph
 */
@Entity
public class Start implements Serializable {
    
    public enum Typ {
        Bronze, Silber
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull
    @Min(1)
    private int startnummer;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private Typ typ;
    
    @ManyToOne
    @NotNull
    private Gruppe gruppe;
    
    @Min(1)
    private Double angriffszeit;
    
    private Integer strafpunkteAngriff;
    
    @Min(1)
    private Double staffelzeit;
    
    private Integer strafpunkteStaffel;
    
    private Integer alterspunkte;
    
    public boolean abgeschlossen() {
        return Optional.ofNullable(angriffszeit).orElse(0.0) > 0
                && Optional.ofNullable(staffelzeit).orElse(0.0) > 0;
    }

    /**
     * 
     * @return null falls der Start noch nicht abgeschlossen ist  
     */
    public Double calculateGesamtpunkte() {
        if (abgeschlossen()) {
            return gutpunkte() - abzugspunkte();
        } else { 
            return null;
        }
    }
    
    protected double abzugspunkte() {
        return Optional.ofNullable(angriffszeit).orElse(0.0)
                + Optional.ofNullable(strafpunkteAngriff).orElse(0)
                + Optional.ofNullable(staffelzeit).orElse(0.0)
                + Optional.ofNullable(strafpunkteStaffel).orElse(0);
    }
    
    protected int gutpunkte() {
        return 500 + Optional.ofNullable(alterspunkte).orElse(0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStartnummer() {
        return startnummer;
    }

    public void setStartnummer(int startnummer) {
        this.startnummer = startnummer;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }

    public Gruppe getGruppe() {
        return gruppe;
    }

    public void setGruppe(Gruppe gruppe) {
        this.gruppe = gruppe;
    }

    public Double getAngriffszeit() {
        return angriffszeit;
    }

    public void setAngriffszeit(Double angriffszeit) {
        this.angriffszeit = angriffszeit;
    }

    public Integer getStrafpunkteAngriff() {
        return strafpunkteAngriff;
    }

    public void setStrafpunkteAngriff(Integer strafpunkteAngriff) {
        this.strafpunkteAngriff = strafpunkteAngriff;
    }

    public Double getStaffelzeit() {
        return staffelzeit;
    }

    public void setStaffelzeit(Double staffelzeit) {
        this.staffelzeit = staffelzeit;
    }

    public Integer getStrafpunkteStaffel() {
        return strafpunkteStaffel;
    }

    public void setStrafpunkteStaffel(Integer strafpunkteStaffel) {
        this.strafpunkteStaffel = strafpunkteStaffel;
    }

    public Integer getAlterspunkte() {
        return alterspunkte;
    }

    public void setAlterspunkte(Integer alterspunkte) {
        this.alterspunkte = alterspunkte;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Start other = (Start) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
