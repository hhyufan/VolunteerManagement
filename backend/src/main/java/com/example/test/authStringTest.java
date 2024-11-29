package com.example.test;

public class authStringTest {
    public static void main(String[] args) {
        String authHeader = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5dWZhbiIsImlhdCI6MTczMjgxNDI1OCwiZXhwIjoxNzMyODE3ODU4fQ.ToP_E21kurhfbM-HzSmaVTrT6xn9IlCgZStNn22KKK8";
        System.out.println(authHeader != null && authHeader.startsWith("Bearer "));
    }
}
