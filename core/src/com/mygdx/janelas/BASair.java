/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.janelas;

import com.badlogic.gdx.Gdx;

/**
 *
 * @author fabio
 */
public class BASair extends BotaoAcao{

    public BASair(String arg) {
        super(arg);
    }

    @Override
    public void realizar() {
        Gdx.app.exit();
    }
    
}
