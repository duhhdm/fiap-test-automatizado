package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.LoginModel;

import static io.restassured.RestAssured.given;

public class AutenticaUsuarioService {
    private LoginModel loginModel = new LoginModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Response response;
    String baseUrl = "https://web-port-ed-ehhhbcg3abd0c2bg.canadacentral-01.azurewebsites.net";

    public void setLoginModel(String campo, String value){
        switch (campo) {
            case "email" -> loginModel.setEmail(value);
            case "senha" -> loginModel.setSenha(value);
            default -> throw new IllegalStateException("Unexpected field " + campo);
        }
    }

    public void autenticaUsuario(String endpoint){
        String url = baseUrl+endpoint;
        String bodyToSend = gson.toJson(loginModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void deletarUsuario(String endpoint){
        response =null;
        String url = baseUrl+endpoint+"/"+loginModel.getEmail();
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }
}
