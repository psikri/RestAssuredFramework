package com.automation.cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/java/features",
plugin="json:target/jsonReports/result.json",
glue="stepDefinitions"
)
public class TestRunner {

}
