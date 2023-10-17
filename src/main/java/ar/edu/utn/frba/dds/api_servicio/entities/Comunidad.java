package ar.edu.utn.frba.dds.api_servicio.entities;

import java.util.List;

public class Comunidad {
  private Long id;
  private GradoDeConfiabilidad gradoDeConfiabilidad;
  private Double puntosDeConfianza;
  private List<Usuario> usuarios;


  public Comunidad(String nombre, List <Usuario> usuarios) {
    this.usuarios = usuarios;
    this.puntosDeConfianza = 5.0;
  }
  public Comunidad() {}

  public Double getPuntosDeConfianza() {
    return puntosDeConfianza;
  }

  public void setPuntosDeConfianza(Double puntosDeConfianza) {
    this.puntosDeConfianza = puntosDeConfianza;
  }

  public GradoDeConfiabilidad getGradoDeConfiabilidad() {
    return gradoDeConfiabilidad;
  }

  public void setGradoDeConfiabilidad(GradoDeConfiabilidad gradoDeConfiabilidad) {
    this.gradoDeConfiabilidad = gradoDeConfiabilidad;
  }

  public List<Usuario> getUsuarios() {
    return usuarios;
  }

  public void setUsuarios(List<Usuario> usuarios) {
    this.usuarios = usuarios;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }



}
