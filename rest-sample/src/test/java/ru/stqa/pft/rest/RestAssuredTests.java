package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase{



    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }


    /**
     * Тест проверяет создание багрепорта по протоколу REST
     */
    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(1259);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("piuPiu Tessst11").withDescription("piu piu description11");
        int issueId = createIssue(newIssue);

        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);

    }



}

