/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mes;

import java.util.Scanner;
/**
 *
 * @author Utilizador
 */
public class Manager {
    
    public static void main(String[] args){
        
        Scanner input = new Scanner(System.in);
        System.out.println("Insert int: ");
        int n = input.nextInt();
        System.out.println("O utilizador inseriu: " + n);
    }
}
