package ar.edu.utn.frba.dds.api_servicio.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
  private Long id;
  private Double puntosDeConfianza;
  private GradoDeConfiabilidad gradoConfiabilidad;
  private List<Incidente> incidentes;

  public GradoDeConfiabilidad getGradoConfiabilidad() {
    return gradoConfiabilidad;
  }

  public void setGradoConfiabilidad(GradoDeConfiabilidad gradoConfiabilidad) {
    this.gradoConfiabilidad = gradoConfiabilidad;
  }

  public List<Incidente> getIncidentes() {
    return incidentes;
  }

  public void setIncidentes(List<Incidente> incidentes) {
    this.incidentes = incidentes;
  }

  public Usuario(List <Incidente> incidentes) {
    this.incidentes = incidentes;
    this.puntosDeConfianza = 5.0;
  }
  public Usuario() {
    this.puntosDeConfianza = 5.0;
  }

  public Double getPuntosDeConfianza() {
    return puntosDeConfianza;
  }

  public void setPuntosDeConfianza(Double puntosDeConfianza) {
    this.puntosDeConfianza = puntosDeConfianza;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }





}