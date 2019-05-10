package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {


    /**
     * Метод получает список багрепортов в формате json с помощью GET запроса
     * @return множестсво объектов типа Issue
     */
    protected Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json?limit=500").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }


    /**
     * Метод создаёт новый багрепорт с помощью POST запроса
     * @return id созданного багрепорт
     */
    protected int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given()
                .params("subject", newIssue.getSubject())
                .params("description", newIssue.getDescription())
                .post("http://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();

    }

    /**
     * Метод проверяет статус багрепорта
     * @param issueId - id багрепорта
     * @return возвращется true, если статус дефекта == "Open" или "Re-opened"
     */
    public boolean isIssueOpen(int issueId) throws IOException {
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json?limit=500").asString();
        JsonElement parse = new JsonParser().parse(json);
        JsonElement issues = parse.getAsJsonObject().get("issues");
        JsonElement issue = issues.getAsJsonArray().get(0);
        String issue_state = issue.getAsJsonObject().get("state_name").toString();
        return issue_state.equals("Open") || issue_state.equals("Re-opened");
    }

    /**
     * При вызове этого метода тест будет пропущен, если статус дефекта == "Open" или "Re-opened"
     * @param issueId  id багрепорта
     */
    public void skipIfNotFixed(int issueId) throws IOException {
        if (!isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }


}
