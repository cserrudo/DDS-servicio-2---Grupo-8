package ar.edu.utn.frba.dds.api_servicio.controllers;

import ar.edu.utn.frba.dds.api_servicio.entities.GradoDeConfiabilidad;
import ar.edu.utn.frba.dds.api_servicio.entities.Comunidad;
import ar.edu.utn.frba.dds.api_servicio.service.CalcularConfiabilidadComunidad;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiRequestBody;
import io.javalin.openapi.OpenApiResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import java.util.HashMap;
import java.util.Map;

public class ConfiabilidadComunidadController implements Handler {
  @OpenApi(
      path = "/calcular-confiabilidad-comunidad",
      methods = HttpMethod.POST,
      summary = "Calcular confiabilidad de una comunidad",
      operationId = "calcularConfiabilidadComunidad",
      tags = {"Confiabilidad comunidad"},
      requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Comunidad.class)}),
      responses = {
          @OpenApiResponse(status = "200", content = {@OpenApiContent(from = HashMap.class)}),
          @OpenApiResponse(status = "400", description = "Entrada inválida")
      }
  )
  @Override
  public void handle(Context ctx) throws Exception {
    try {
      Comunidad comunidad = ctx.bodyAsClass(Comunidad.class); // Deserialización
      CalcularConfiabilidadComunidad calculadora = new CalcularConfiabilidadComunidad();
      Double puntos = calculadora.actualizarPuntosDeConfianza(comunidad);
      GradoDeConfiabilidad grado = calculadora.actualizarGradoConfiabilidad(comunidad);
      Boolean estaActivo = calculadora.actualizarActividadComunidad(comunidad);

      Map<String, Object> respuesta = new HashMap<>();
      respuesta.put("puntosDeConfianza", puntos);
      respuesta.put("gradoDeConfiabilidad", grado);
      respuesta.put("activo", estaActivo);

      ctx.status(200).json(respuesta);
    } catch (Exception e) {
      ctx.status(400).result("Error en la solicitud: " + e.getMessage());
    }
  }
}