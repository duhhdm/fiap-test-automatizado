package steps;

import com.google.gson.Gson;
import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import model.UsuarioModel;
import org.junit.Assert;
import services.CadastroUsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroUsuarioSteps {

    CadastroUsuarioService cadastroUsuarioService = new CadastroUsuarioService();

    @Dado("que eu tenha os seguintes dados do usuario:")
    public void queEuTenhaOsSeguintesDadosDoUsuario(List<Map<String,String>> rows) {
        for(Map<String, String> columns: rows){
            cadastroUsuarioService.setUsuarioCadastro(columns.get("campo"),columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuario")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuario(String endpoint) {
        cadastroUsuarioService.createUser(endpoint);
    }

    @Entao("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroUsuarioService.response.statusCode());
    }

    @Entao("o status code da resposa deve ser {int}")
    public void oStatusCodeDaResposaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroUsuarioService.response.statusCode());
    }

    @E("a resposta deve receber o campo email com a mensagem {string}")
    public void aRespostaDeveReceberOCampoEmailComAMensagem(String mensagem) {
        Gson gson = new Gson();
        UsuarioModel json = gson.fromJson(cadastroUsuarioService.response.body().jsonPath().prettify(),UsuarioModel.class);
        Assert.assertEquals(mensagem, json.getEmail());
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contrato) throws IOException {
        cadastroUsuarioService.setContract(contrato);
    }

    @Entao("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {

        Set<ValidationMessage> validateResponse = cadastroUsuarioService.validaResponseSchema();
        Assert.assertFalse("O contrato está inválido. Erros encontrados: " + validateResponse, ((Set<?>) validateResponse).isEmpty());
    }
}
