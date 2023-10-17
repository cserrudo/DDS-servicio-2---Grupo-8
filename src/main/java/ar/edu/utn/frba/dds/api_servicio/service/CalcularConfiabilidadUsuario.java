package ar.edu.utn.frba.dds.api_servicio.service;

import ar.edu.utn.frba.dds.api_servicio.entities.GradoDeConfiabilidad;
import ar.edu.utn.frba.dds.api_servicio.entities.Incidente;
import ar.edu.utn.frba.dds.api_servicio.entities.Usuario;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import java.time.Duration;
import java.util.List;

public class CalcularConfiabilidadUsuario {
  private boolean tuvoIncidenteSemanal = false;
  public Boolean actualizarActividadUsuario(Usuario usuario){
      return actualizarGradoConfiabilidad(usuario) != GradoDeConfiabilidad.NO_CONFIABLE;
  }

  public GradoDeConfiabilidad actualizarGradoConfiabilidad(Usuario usuario){
    Double puntaje = usuario.getPuntosDeConfianza();
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

  public Double actualizarPuntajeConfianza(Usuario usuario) {
      tuvoIncidenteSemanal = false;
      List <Incidente> incidentes = usuario.getIncidentes();

    incidentes.stream().forEach(incidente ->
                                descontarPuntaje(usuario,
                                puntosDeConfianza(incidentes,
                                incidente, usuario)));

    return usuario.getPuntosDeConfianza();
  }

  public Double puntosDeConfianza(List <Incidente> incidentes, Incidente incidente, Usuario usuario){

      if (esAperturaFraudulenta(incidente, usuario) || esCierreFraudulento(incidente, incidentes, usuario)){
          return 0.2;
      }else{
        if(esIncidenteSemanal(incidente) && !tuvoIncidenteSemanal){
              tuvoIncidenteSemanal = true;
              return -0.5;
        }
      }
      return 0.0;
  }
    private boolean esIncidenteSemanal(Incidente incidente) {
        LocalDate fechaApertura = incidente.getFechaApertura().toLocalDate();
        LocalDate inicioDeLaSemana = fechaApertura.with(DayOfWeek.MONDAY);
        LocalDate finDeLaSemana = fechaApertura.with(DayOfWeek.SUNDAY);

        return !incidente.getFechaCierre().toLocalDate().isBefore(inicioDeLaSemana) &&
                !incidente.getFechaCierre().toLocalDate().isAfter(finDeLaSemana);
    }


  private boolean esCierreFraudulento(Incidente incidente1, List<Incidente> todosLosIncidentes, Usuario usuario) {
    for (Incidente incidente2 : todosLosIncidentes) {
      if (esCierreFraudulento(incidente1, incidente2, usuario)) {
        return true;
      }
    }
    return false;
  }

  private boolean esAperturaFraudulenta(Incidente incidente, Usuario usuario) {
    Long diferencia = obtenerDiferencia(incidente.getFechaApertura(), incidente.getFechaCierre());
    return diferencia < 3 && incidente.getUsuarioCierre() == usuario.getId();
  }
  private boolean esCierreFraudulento(Incidente incidente1, Incidente incidente2, Usuario usuarioActual) {
    long diferencia = obtenerDiferencia(incidente1.getFechaCierre(), incidente2.getFechaApertura());

    return diferencia < 3
            && incidente1.getId()!= incidente2.getId() &&
            incidente1.esSimilar(incidente2) &&
            incidente1.getUsuarioCierre() == usuarioActual.getId();
  }

  private Long obtenerDiferencia(LocalDateTime unaFecha, LocalDateTime otraFecha) {
    return Duration.between(unaFecha, otraFecha).toMinutes();
  }
  private void descontarPuntaje(Usuario usuario, Double puntos) {
     usuario.setPuntosDeConfianza(usuario.getPuntosDeConfianza() - puntos);
  }

}

