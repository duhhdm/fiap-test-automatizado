package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.AutenticaUsuarioService;

import java.util.List;
import java.util.Map;

public class AutenticaUsuarioSteps {

    AutenticaUsuarioService autenticaUsuarioService = new AutenticaUsuarioService();

    @Dado("que eu tenha os seguinte dados do usuario que ira autenticar:")
    public void queEuTenhaOsSeguinteDadosDoUsuarioQueIraAutenticar(List<Map<String,String>> rows) {
        for(Map<String, String> columns: rows){
            autenticaUsuarioService.setLoginModel(columns.get("campo"),columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de autenticação")
    public void euEnviarARequisiçãoParaOEndpointDeAutenticação(String endpoint) {
        autenticaUsuarioService.autenticaUsuario(endpoint);
    }

    @Entao("o statuscode da resposta deve ser {int}")
    public void oStatuscodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode,autenticaUsuarioService.response.statusCode());
    }

    @Quando("eu enviar a requisiçao com o EMAIL para o endpoint {string} de deleção de usuario")
    public void euEnviarARequisiçaoComOEMAILParaOEndpointDeDeleçãoDeUsuario(String endpoint) {
        autenticaUsuarioService.deletarUsuario(endpoint);
    }

    @Entao("o status deve ser {int}")
    public void oStatusDeveSer(int statusCode) {
        Assert.assertEquals(statusCode,autenticaUsuarioService.response.statusCode());
    }

    @Dado("que o usuario logado")
    public void queOUsuarioLogado() {

    }

    @Dado("que o usuario logado com o seguinte email:")
    public void queOUsuarioLogadoComOSeguinteEmail(List<Map<String,String>> rows) {
        for(Map<String, String> columns: rows){
            autenticaUsuarioService.setLoginModel(columns.get("campo"),columns.get("valor"));
        }
    }
}
