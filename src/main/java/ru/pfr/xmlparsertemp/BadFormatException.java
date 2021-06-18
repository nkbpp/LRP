/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.pfr.xmlparsertemp;

/**
 *
 * @author 041SlavkovIA
 */
public class BadFormatException extends Exception{

    public BadFormatException() {
        super("Некорректный входной файл");
    }
    
}
