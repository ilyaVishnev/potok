package com;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class ConfigTest {

    @Test
    public void loadingMap() {
        Config config = new Config("app.properties");
        config.load();
        Assert.assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }
}
