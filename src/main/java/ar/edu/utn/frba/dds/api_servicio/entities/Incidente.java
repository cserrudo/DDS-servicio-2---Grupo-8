package ar.edu.utn.frba.dds.api_servicio.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Incidente {
  private Long id;
  private String servicio;
  private String establecimiento;
  private Long usuarioApertura;
  private Long usuarioCierre;
  private LocalDateTime fechaApertura;
  private LocalDateTime fechaCierre;

  public Incidente(Long usuarioApertura, Long usuarioCierre, LocalDateTime fechaApertura, LocalDateTime fechaCierre) {
    this.usuarioApertura = usuarioApertura;
    this.usuarioCierre = usuarioCierre;
    this.fechaApertura = fechaApertura;
    this.fechaCierre = fechaCierre;
  }

  public Incidente() {}

  public Long getUsuarioApertura() {
    return usuarioApertura;
  }

  public void setUsuarioApertura(Long usuarioApertura) {
    this.usuarioApertura = usuarioApertura;
  }

  public Long getUsuarioCierre() {
    return usuarioCierre;
  }

  public void setUsuarioCierre(Long usuarioCierre) {
    this.usuarioCierre = usuarioCierre;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }


  public LocalDateTime getFechaApertura() {
    return fechaApertura;
  }

  public void setFechaApertura(LocalDateTime fechaApertura) {
    this.fechaApertura = fechaApertura;
  }

  public LocalDateTime getFechaCierre() {
    return fechaCierre;
  }

  public void setFechaCierre(LocalDateTime fechaCierre) {
    this.fechaCierre = fechaCierre;
  }

  public String getServicio() {
    return servicio;
  }

  public void setServicio(String servicio) {
    this.servicio = servicio;
  }

  public String getEstablecimiento() {
    return establecimiento;
  }

  public void setEstablecimiento(String establecimiento) {
    this.establecimiento = establecimiento;
  }

  public boolean esSimilar(Incidente incidente) {
    return this.getEstablecimiento().equals(incidente.getEstablecimiento())
            && this.getServicio().equals(incidente.getServicio());
  }
}
