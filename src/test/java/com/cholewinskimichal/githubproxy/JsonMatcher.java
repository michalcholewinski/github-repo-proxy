package com.cholewinskimichal.githubproxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JsonMatcher {
    public static Matcher<String> matchesJsonFile(final String path) {

        return new BaseMatcher<String>() {

            @Override
            public boolean matches(Object jsonString) {
                try {
                    String json = FileUtils.readFileToString(new File(path), "UTF-8");
                    JSONAssert.assertEquals(json, (String) jsonString, false);
                } catch (JSONException | IOException e) {
                    log.error(e.getMessage(), e);
                    return false;
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {}
        };
    }
}
