package com.api.apipessoa;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags = "@ApplicationTest",
glue = "com.api.apipessoa.steps", monochrome = true, dryRun = false)
public class ApplicationTest {

}