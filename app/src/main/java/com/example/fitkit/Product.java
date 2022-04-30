package com.example.fitkit;

import java.util.HashMap;

// Model class to retrieve product information from Firebase Firestore
public class Product {

    private String name;
    private String desc;
    private float price;
    private String model;
    private HashMap<String, String> img_links;

    public Product() {
    }

    public Product(String name, String desc, float price, String model, HashMap<String, String> img_links) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.model = model;
        this.img_links = img_links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public HashMap<String, String> getImg_links() {
        return img_links;
    }

    public void setImg_links(HashMap<String, String> img_links) {
        this.img_links = img_links;
    }
//    private String[] img_links;
//
//    public String getImage(int i) {
//        return img_links[i];
//    }
//
//    public String[] getImages() {
//        return img_links;
//    }
//
//    class Images{
//        private String img1;
//        private String img2;
//
//        public String getImg1() {
//            return img1;
//        }
//
//        public void setImg1(String img1) {
//            this.img1 = img1;
//        }
//
//        public String getImg2() {
//            return img2;
//        }
//
//        public void setImg2(String img2) {
//            this.img2 = img2;
//        }
//    }
//
//    private Images img_links;
//
//    public Images getImages() {
//        return img_links;
//    }
}
