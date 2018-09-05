package com.example.deepak.localto_doapp;

public class Reslogin {


        String name;
        String pass;
        String token;

        public Reslogin(String name, String pass, String token) {
            this.name = name;
            this.pass = pass;
            this.token = token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "Responcelogin{" +
                    "name='" + name + '\'' +
                    ", pass='" + pass + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }


