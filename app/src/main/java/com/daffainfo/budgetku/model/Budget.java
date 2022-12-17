package com.daffainfo.budgetku.model;

public class Budget {
    private int id;
    private String name;
    private String categories;
    private String money;
    private String description;
    private String type;
    private String datetime;

    public Budget(String type, String money) {
        this.categories = type;
        this.money = money;
    }

    public Budget(int id, String name, String categories, String money, String description, String type, String datetime) {
        this.id = id;
        this.name = name;
        this.categories = categories;
        this.money = money;
        this.description = description;
        this.type = type;
        this.datetime = datetime;
    }


    public static Budget create(String name, String categories, String money, String description, String type, String datetime) {
        return new Budget(-1, name, categories, money, description, type, datetime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
