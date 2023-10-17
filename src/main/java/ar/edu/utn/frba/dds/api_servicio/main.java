package ar.edu.utn.frba.dds.api_servicio;

import ar.edu.utn.frba.dds.api_servicio.controllers.ConfiabilidadComunidadController;
import ar.edu.utn.frba.dds.api_servicio.controllers.ConfiabilidadUsuarioController;
import io.javalin.Javalin;
import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.redoc.ReDocConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;

import static io.javalin.apibuilder.ApiBuilder.*;
public class main {

  public static void main(String[] args){
    Javalin.create(config -> {
      OpenApiConfiguration openApiConfiguration = new OpenApiConfiguration();
      openApiConfiguration.getInfo().setTitle("Descripción de mi API");

      config.plugins.register(new OpenApiPlugin(openApiConfiguration));
      config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
      config.plugins.register(new ReDocPlugin(new ReDocConfiguration()));
    }).routes(() -> {
      get("/", ctx -> ctx.result("Hola Mundo"));
      post("/calcular-confiabilidad-comunidad", new ConfiabilidadComunidadController());
      post("/calcular-confiabilidad-usuario", new ConfiabilidadUsuarioController());
    }).start(8080);

  }
}