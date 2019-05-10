package ru.stqa.pft.github;

import com.jcabi.github.*;
import jersey.repackaged.com.google.common.collect.ImmutableBiMap;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("37f874afaf70439e77e55260a50fa822418ace13");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("qaokss", "java_training")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableBiMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }

    }
}
