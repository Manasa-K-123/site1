package com.mysite.core.models;

import org.apache.sling.models.annotations.Model;

import javax.annotation.Resource;

@Model(adaptables = Resource.class,adapters=TestInterface.class)
public class TestService implements TestInterface{
    @Override
    public String getName() {
        return "MHDD";
    }
}
