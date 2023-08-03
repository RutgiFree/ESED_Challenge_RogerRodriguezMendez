package com.esed.challenge.application;

import com.google.gson.JsonObject;

public class ElementV1 implements Element {

    String name, path, weight_unit, tag, user;
    Float weight;

    public ElementV1() {
        this.user = "NO";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setweight(String weight) {
        this.weight = Float.parseFloat(weight);
    }

    public void setweight_unit(String weight_unit) {
        this.weight_unit = weight_unit;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String toString() {
        return "ElementV1 [family/tag=" + tag + ", mass/weight=" + weight + ", mass/weight_unit=" + weight_unit
                + ", name=" + name
                + ", path=" + path + ", user=" + user + "]";
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("path", path);
        json.addProperty("mass", weight);
        json.addProperty("mass_unit", weight_unit);
        json.addProperty("family", tag);
        json.addProperty("user", user);
        return json;
    }
}
