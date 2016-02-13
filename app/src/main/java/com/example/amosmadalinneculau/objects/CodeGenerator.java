package com.example.amosmadalinneculau.objects;

import java.util.Random;

/**
 * Created by Amos Madalin Neculau on 13/02/2016.
 */
public class CodeGenerator {
    public char[] characters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','x','y','z'};
    public Random charRnd;
    public String code;
    public Random integerRnd;

    public CodeGenerator(){

        charRnd = new Random();
        integerRnd = new Random();

        code = "";
        for(int i=0; i<2; ++i)
            code += characters[charRnd.nextInt(24)];

        for(int i=0; i<4; ++i)
            code += integerRnd.nextInt(9);
    }

    public String getCode(){
        return code;
    }

}
