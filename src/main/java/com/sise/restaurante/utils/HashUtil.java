/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sise.restaurante.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author User02
 */
public class HashUtil {
        public static String generarHashSHA1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            return bytesToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar hash SHA-1", e);
        }
    }
    
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    
    public static boolean verificarPassword(String passwordPlano, String hashAlmacenado) {
        String hashGenerado = generarHashSHA1(passwordPlano);
        return hashGenerado.equals(hashAlmacenado);
    }
}
