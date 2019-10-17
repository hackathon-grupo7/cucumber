package multibags7;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Stepdefs {

    private final WebDriver driver = new ChromeDriver();

    public Stepdefs() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Dado("que João abriu a página de contato")
    public void que_João_abriu_a_página_de_contato() {
        driver.get("http://multibags.1dt.com.br/shop/store/contactus.html");
    }

    @Dado("os dados informados estão corretos")
    public void os_dados_informados_estão_corretos() {
        driver.findElement(By.id("name")).sendKeys("João da Silva");
        driver.findElement(By.id("email")).sendKeys("joao@dasilva.com");
        driver.findElement(By.id("subject")).sendKeys("Quero trocar");
        driver.findElement(By.id("comment")).sendKeys("Como faço para trocar um produto?");
    }

    @Quando("ele envia aciona a opção de enviar a mensagem para o e-commerce")
    public void ele_envia_aciona_a_opção_de_enviar_a_mensagem_para_o_e_commerce() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.id("submitContact")));
    }

    @Entao("o sistema deve apresentar uma mensagem de envio de contato com sucesso")
    public void o_sistema_deve_apresentar_uma_mensagem_de_envio_de_contato_com_sucesso() {

        new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.id("store.success")).getText().equals("Your message has been sent");
            }
        });
    }

    @Given("João está na tela de Login")
    public void João_está_na_tela_de_Login() {
        driver.get("http://multibags.1dt.com.br/shop/customer/customLogon.html");
    }

    @Dado("os dados de login informados estão corretos")
    public void os_dados_de_login_informados_estão_corretos() {
        driver.findElement(By.id("signin_userName")).sendKeys("sabrina@teste.com.br");
        driver.findElement(By.id("signin_password")).sendKeys("123456");
    }

    @Given("os dados informados estão incorretos")
    public void os_dados_informados_estão_incorretos() {
        driver.findElement(By.id("signin_userName")).sendKeys("sabrina@teste.com.br");
        driver.findElement(By.id("signin_password")).sendKeys("12345");
    }

    @Then("o sistema deve apresentar uma mensagem de erro")
    public void o_sistema_deve_apresentar_uma_mensagem_de_erro() {
        driver.findElement(By.cssSelector("div[class='alert alert-danger']"));
    }

    @When("ele aciona ação de sign in no e-commerce")
    public void ele_aciona_ação_de_sign_in_no_e_commerce() {
        driver.findElement(By.cssSelector("#genericLogin-button")).click();
    }

    @Then("o sistema deve apresentar a tela de dashboard do cliente")
    public void o_sistema_deve_apresentar_a_tela_de_dashboard_do_cliente() {
        new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.getCurrentUrl().startsWith("http://multibags.1dt.com.br/shop/customer/dashboard.html");
            }
        });
    }

    @Given("João acessou o menu My Account")
    public void joão_acessou_o_menu_My_Account() {
        driver.get("http://multibags.1dt.com.br/shop/");
        driver.findElement(By.id("customerAccount")).click();
    }

    @Given("clicou na opção Register")
    public void clicou_na_opção_Register() {
        driver.findElement(By.id("registerLink")).click();
    }

    @Then("o sistema deve apresentar o formulário de informações pessoais")
    public void o_sistema_deve_apresentar_o_formulário_de_informações_pessoais() {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://multibags.1dt.com.br/shop/customer/registration.html" );
    }

    @When("João preenche os dados corretamente")
    public void joão_preenche_os_dados() {
        driver.findElement(By.id("firstName")).sendKeys("Sabrina");
        driver.findElement(By.id("lastName")).sendKeys("Teste2");
        Select drpCountry = new Select(driver.findElement(By.id("registration_country")));
        drpCountry.selectByValue("BR");
        driver.findElement(By.id("hidden_zones")).sendKeys("São Paulo");
        driver.findElement(By.id("emailAddress")).sendKeys(gerarEmail());
        driver.findElement(By.id("password")).sendKeys("teste123");
        driver.findElement(By.id("passwordAgain")).sendKeys("teste123");
    }

    private String gerarEmail() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "") + "_grupo7@teste.com.br";
    }

    @Then("o sistema deve criar a conta")
    public void o_sistema_deve_criar_a_conta() {
        driver.findElement(By.cssSelector("button[class='btn btn-default login-btn']")).submit();
    }

    @Then("redirecionar João para o dashboard")
    public void redirecionar_João_para_o_dashboard() {
        assertTrue(driver.getCurrentUrl().startsWith("http://multibags.1dt.com.br/shop/customer/dashboard.html"));
    }

    @When("João preenche os dados incorretamente")
    public void joão_preenche_os_dados_incorretamente() {
        driver.findElement(By.id("firstName")).sendKeys("João2");
        driver.findElement(By.id("hidden_zones")).sendKeys("São Paulo");
        driver.findElement(By.id("emailAddress")).sendKeys("joao123@teste.com.br");
        driver.findElement(By.id("password")).sendKeys("teste123");
        driver.findElement(By.id("passwordAgain")).sendKeys("teste1234");
        driver.findElement(By.cssSelector("button[class='btn btn-default login-btn']")).submit();
    }

    @Then("o sistema deve apresentar uma mensagem com descrição dos erros")
    public void o_sistema_deve_apresentar_uma_mensagem_com_descrição_dos_erros() {
        driver.findElement(By.cssSelector("div[class='alert alert-error alert-danger form-group']"));
    }

    @Then("identificar os campos inválidos")
    public void identificar_os_campos_inválidos() {
        assertTrue(driver.findElements(By.className("error")).size() !=0 );
    }

    @Given("Sabrina está autenticado em sua conta")
    public void sabrina_está_autenticado_em_sua_conta() {
        driver.get("http://multibags.1dt.com.br/shop/customer/customLogon.html");

        driver.findElement(By.id("signin_userName")).sendKeys("sabrina@teste.com.br");
        driver.findElement(By.id("signin_password")).sendKeys("123456");

        driver.findElement(By.cssSelector("#genericLogin-button")).click();
    }

    @Given("está na página de troca de senhas")
    public void está_na_página_de_troca_de_senhas() {
        //driver.get("http://multibags.1dt.com.br/shop/customer/password.html");
        driver.findElement(By.partialLinkText("Change password")).click();
    }

    @When("ela preenche o formulário de alteração de senhas com dados válidos")
    public void ela_preenche_o_formulário_de_alteração_de_senhas_com_dados_válidos() {
        driver.findElement(By.id("currentPassword")).sendKeys("sabrina@teste.com.br");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("checkPassword")).sendKeys("123456");
    }

    @When("ela preenche o formulário de alteração de senhas com dados inválidos")
    public void ela_preenche_o_formulário_de_alteração_de_senhas_com_dados_inválidos() {
        driver.findElement(By.id("currentPassword")).sendKeys("sabrina@teste.com.br");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("checkPassword")).sendKeys("321456");
    }

    @When("clica em change password")
    public void clica_em_change_password() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.id("submitChangePassword")));
    }

    @Then("o sistema deve apresentar uma mensagem de senha alterada com sucesso")
    public void o_sistema_deve_apresentar_uma_mensagem_de_senha_alterada_com_sucesso() {
        driver.findElement(By.id("store.success")).getText().equals("Request completed with success");
    }

    @Then("o sistema desabilita o botão de submissão do formulário")
    public void o_sistem_desabilita_o_botão_de_submissão_do_formulário() {
        boolean submitButton = driver.findElement(By.id("submitChangePassword")).isEnabled();
        Assert.assertEquals(false, submitButton);
    }

    @Then("a senha deve estar alterada")
    public void a_senha_deve_estar_alterada() {
        driver.findElement(By.partialLinkText("Logout")).click();
        driver.get("http://multibags.1dt.com.br/shop/customer/customLogon.html");

        driver.findElement(By.id("signin_userName")).sendKeys("sabrina@teste.com.br");
        driver.findElement(By.id("signin_password")).sendKeys("123456");

        driver.findElement(By.cssSelector("#genericLogin-button")).click();
    }

    @Then("uma mensagem sobre a falha será mostrada")
    public void uma_mensagem_sobre_a_falha_será_mostrada() {
        boolean submitButton = driver.findElement(By.id("formError")).isDisplayed();
        Assert.assertEquals(true, submitButton);
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}
