package com.esed.challenge.application;

import com.google.gson.JsonObject;

public class ElementV3 implements Element {

    String name, path, mass_unit, family, user;
    Float mass;

    public ElementV3(String name, String path, Float mass, String mass_unit, String family, String user) {
        this.name = name;
        this.path = path;
        this.mass = mass;
        this.mass_unit = mass_unit;
        this.family = family;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Float getMass() {
        return mass;
    }

    public void setMass(Float mass) {
        this.mass = mass;
    }

    public String getMass_unit() {
        return mass_unit;
    }

    public void setMass_unit(String mass_unit) {
        this.mass_unit = mass_unit;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String toString() {
        return "ElementV3 [family=" + family + ", mass=" + mass + ", mass_unit=" + mass_unit + ", name=" + name
                + ", path=" + path + ", user=" + user + "]";
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("path", path);
        json.addProperty("mass", mass);
        json.addProperty("mass_unit", mass_unit);
        json.addProperty("family", family);
        json.addProperty("user", user);
        return json;
    }
}
