package ar.edu.utn.frba.dds.api_servicio.service;

import ar.edu.utn.frba.dds.api_servicio.entities.Comunidad;
import ar.edu.utn.frba.dds.api_servicio.entities.GradoDeConfiabilidad;
import ar.edu.utn.frba.dds.api_servicio.service.CalcularConfiabilidadUsuario;
import ar.edu.utn.frba.dds.api_servicio.entities.Usuario;

import java.util.List;

public class CalcularConfiabilidadComunidad {
  CalcularConfiabilidadUsuario calculador = new CalcularConfiabilidadUsuario();
  public Boolean actualizarActividadComunidad(Comunidad comunidad){
    return actualizarGradoConfiabilidad(comunidad) != GradoDeConfiabilidad.NO_CONFIABLE;
  }
  public GradoDeConfiabilidad actualizarGradoConfiabilidad(Comunidad comunidad){
    Double puntaje = actualizarPuntosDeConfianza( comunidad);
    if(puntaje<2){
      return GradoDeConfiabilidad.NO_CONFIABLE;
    }else if (puntaje>=2 && puntaje<=3){
      return GradoDeConfiabilidad.CON_RESERVAS;
    } else if (puntaje>3 && puntaje<5) {
      return GradoDeConfiabilidad.CONFIABLE_NIVEL_1;
    } else {
      return  GradoDeConfiabilidad.CONFIABLE_NIVEL_2;
    }
  }
  public Double actualizarPuntosDeConfianza(Comunidad comunidad){
    long cantidadUsuariosConReservas = comunidad.getUsuarios().stream()
        .filter(u -> GradoDeConfiabilidad.CON_RESERVAS.equals(u.getGradoConfiabilidad()))
      .count();

    return puntosDeConfianzaSinDescontar(comunidad) - cantidadUsuariosConReservas * 0.2;
  }
  public Double puntosDeConfianzaSinDescontar(Comunidad comunidad){
    double sumaPuntos = comunidad.getUsuarios().stream()
            .mapToDouble(usuario -> calculador.actualizarPuntajeConfianza(usuario))
            .sum();


    return sumaPuntos / comunidad.getUsuarios().size();
  }
}


