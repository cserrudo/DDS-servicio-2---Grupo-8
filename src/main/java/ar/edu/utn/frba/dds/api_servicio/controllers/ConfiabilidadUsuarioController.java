package ar.edu.utn.frba.dds.api_servicio.controllers;

import ar.edu.utn.frba.dds.api_servicio.entities.GradoDeConfiabilidad;
import ar.edu.utn.frba.dds.api_servicio.entities.Usuario;
import ar.edu.utn.frba.dds.api_servicio.service.CalcularConfiabilidadUsuario;
import io.javalin.http.Context;
import io.javalin.http.Handler;
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
import java.util.Map;public class ConfiabilidadUsuarioController implements Handler {
  @OpenApi(
      path = "/calcular-confiabilidad-usuario",
      methods = HttpMethod.POST,
      summary = "Calcular confiabilidad de un usuario",
      operationId = "calcularConfiabilidadUsuario",
      tags = {"Confiabilidad usuario"},
      requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Usuario.class)}),
      responses = {
          @OpenApiResponse(status = "200", content = {@OpenApiContent(from = HashMap.class)})
      }
  )
  public void handle(Context ctx) throws Exception {
    try {
      Usuario usuario = ctx.bodyAsClass(Usuario.class); // Deserialización
      CalcularConfiabilidadUsuario calculadora = new CalcularConfiabilidadUsuario();
      Double puntos = calculadora.actualizarPuntajeConfianza(usuario);
      GradoDeConfiabilidad grado = calculadora.actualizarGradoConfiabilidad(usuario);
      Boolean estaActivo = calculadora.actualizarActividadUsuario(usuario);

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