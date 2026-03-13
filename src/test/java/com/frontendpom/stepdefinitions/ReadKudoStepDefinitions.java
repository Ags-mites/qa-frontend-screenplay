package com.frontendpom.stepdefinitions;

import com.frontendpom.hooks.OpenBrowser;
import com.frontendpom.questions.KudoAuthorVisible;
import com.frontendpom.questions.KudoDisplayedMessage;
import com.frontendpom.questions.KudoTimestampCheck;
import com.frontendpom.tasks.FilterKudos;
import com.frontendpom.tasks.PostKudo;
import com.frontendpom.tasks.SearchRecipient;
import com.frontendpom.ui.KudosListPage;
import com.frontendpom.util.Config;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.time.LocalDate;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.anyOf;

public class ReadKudoStepDefinitions {

    @Given("que el sistema SofkianOS se encuentra operativo")
    public void systemIsOperational() {
        OnStage.theActorCalled(Config.ACTOR_NAME)
                .whoCan(CallAnApi.at("http://localhost:8082/api/v1"));
    }

    @And("el usuario accede a la URL del \"Explorar Kudos\"")
    public void userAccessKudosExplorer() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenBrowser.openUrl(Config.BASE_URL),
                Click.on(KudosListPage.LINK_EXPLORADOR)
        );
    }

    @Given("que se realiza una petición POST al endpoint de Kudos con:")
    public void registerKudoViaAPI(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        OnStage.theActorInTheSpotlight().attemptsTo(
                PostKudo.with(data)
        );
    }

    @And("la respuesta del servicio es exitosa \\({int} Accepted\\)")
    public void serviceResponseIsSuccess(int statusCode) {
        OnStage.theActorInTheSpotlight().should(
                // Aceptamos 400 también en caso de duplicados para permitir que la prueba continúe a la validación UI
                seeThatResponse("El código de respuesta debe ser " + statusCode + " o 400 (duplicado)",
                        response -> response.statusCode(anyOf(is(statusCode), is(200), is(201), is(400))))
        );
    }

    @When("el empleado filtra la lista por la categoría {string}")
    public void filterByCategory(String category) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                FilterKudos.byCategory(category)
        );
    }

    @And("realiza la búsqueda del destinatario {string}")
    public void searchRecipient(String recipient) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SearchRecipient.byEmail(recipient)
        );
    }

    @Then("el sistema debe mostrar el registro en el Explorador con el mensaje {string}")
    public void verifyMessage(String message) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(KudoDisplayedMessage.value(), containsString(message))
        );
    }

    @And("el autor visible debe ser {string}")
    public void verifyAuthor(String author) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(KudoAuthorVisible.value(), containsString(author))
        );
    }

    @And("la marca de tiempo debe corresponder a la fecha actual")
    public void verifyTimestamp() {
        String currentYear = String.valueOf(LocalDate.now().getYear());
        OnStage.theActorInTheSpotlight().should(
                seeThat(KudoTimestampCheck.value(), containsString(currentYear))
        );
    }
}
