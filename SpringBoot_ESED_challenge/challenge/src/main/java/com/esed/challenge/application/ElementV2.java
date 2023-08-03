package com.esed.challenge.application;

import com.google.gson.JsonObject;

public class ElementV2 implements Element {

    String name, path, weight_unit, category, user;
    Float weight;

    public ElementV2() {
        this.user = "NO";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setWeight_unit(String weight_unit) {
        this.weight_unit = weight_unit;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String toString() {
        return "ElementV2 [family/category=" + category + ", mass/weight=" + weight + ", mass/weight_unit="
                + weight_unit + ", name=" + name
                + ", path=" + path + ", user=" + user + "]";
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("path", path);
        json.addProperty("mass", weight);
        json.addProperty("mass_unit", weight_unit);
        json.addProperty("family", category);
        json.addProperty("user", user);
        return json;
    }

}
