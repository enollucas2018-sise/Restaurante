/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sise.restaurante.utils;

/**
 *
 * @author User02
 */
public class HashTest {
        public static void main(String[] args) {
        String hash = HashUtil.generarHashSHA1("abc123");
        System.out.println("Hash SHA-1 de abc123: " + hash);
    }

}
