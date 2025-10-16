package org.example.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExampleSteps provides sample Cucumber step definitions used in the test
 * suite to demonstrate a simple Given/When/Then flow (Wikipedia search in
 * this skeleton project).
 *
 * These steps are intentionally marked as pending and serve as a template for
 * implementing real browser or API interactions in your own project.
 */
public class ExampleSteps {
    
    private final Logger logger = LoggerFactory.getLogger(ExampleSteps.class);

    @Given("user is on the Wikipedia home page")
    public void userIsOnTheWikipediaHomePage() {
        logger.info("helpful message");
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
    }

    @When("user searches for {string}")
    public void userSearchesFor(String arg0) {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
    }

    @Then("the article for {string} should be displayed")
    public void theArticleForShouldBeDisplayed(String arg0) {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
    }
}
