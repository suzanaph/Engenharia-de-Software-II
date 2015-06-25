package com.mygdx.jogo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.janelas.JanelaMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Jogo extends Game {

    public final static int MAQUINA = 0;
    public final static int HUMANO = 1;
    public final static Color COLORJOGADOR1 = Color.ORANGE;
    public final static Color COLORJOGADOR2 = Color.TEAL;
    public final static Color COLORDAMAJOGADOR1 = Color.MAROON;
    public final static Color COLORDAMAJOGADOR2 = Color.PURPLE;
    public static float tempo;
    private static Jogo instance;
    private Music musica;
    private Sound somPeca;

    private Sound somDama;
    private Sound captura;
    private int dificuldade;
    private boolean somAtivo;
    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private BitmapFont fonte;

    public void setFont(String diretorio) {
        fonte = new BitmapFont(Gdx.files.internal(diretorio + ".fnt"), Gdx.files.internal(diretorio + ".png"), false);
    }

    public BitmapFont getFont() {
        return fonte;
    }

    public static Jogo getInstance() {
        return instance;
    }

    /**
     * @return the dificuldade
     */
    public int getDificuldade() {
        return dificuldade;
    }

    /**
     * @param aDificuldade the dificuldade to set
     */
    public void setDificuldade(int aDificuldade) {
        dificuldade = aDificuldade;
    }

    public Sound getSomPeca() {
        return somPeca;
    }

    public void setSomPeca(String diretorio) {
        somPeca = Gdx.audio.newSound(Gdx.files.internal(diretorio));
    }

    public void setSomCaptura(String diretorio) {
        captura = Gdx.audio.newSound(Gdx.files.internal(diretorio));
    }

    public Sound getSomCaptura() {
        return captura;
    }

    public void setSomDama(String diretorio) {
        somDama = Gdx.audio.newSound(Gdx.files.internal(diretorio));
    }

    public Sound getSomDama() {
        return somDama;
    }

    public Music getMusica() {
        return musica;
    }

    public void setMusica(String url) {
        musica = Gdx.audio.newMusic(Gdx.files.internal(url));
    }

    public Jogo() {

    }

    @Override
    public void create() {

        instance = this;
        setJogador1(HUMANO);
        getJogador1().setTurno(true);
        setJogador2(MAQUINA);
        resetTabuleiro();

        setScreen(new JanelaMenu());
    }

    @Override
    public void render() {
        super.render();
    }

    /**
     * @return the somAtivo
     */
    public boolean isSomAtivo() {
        return somAtivo;
    }

    /**
     * @param somAtivo the somAtivo to set
     */
    public void setSomAtivo(boolean somAtivo) {
        this.somAtivo = somAtivo;
    }

    /**
     * @return the tabuleiro
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    /**
     * @param tabuleiro the tabuleiro to set
     */
    public void resetTabuleiro() {
        this.tabuleiro = Tabuleiro.retornaInstancia();
    }

    /**
     * @return the jogador1
     */
    public Jogador getJogador1() {
        return jogador1;
    }

    /**
     * @param jogador1 the jogador1 to set
     */
    public void setJogador1(int tipo) {
        switch (tipo) {
            case MAQUINA:
                this.jogador1 = new Maquina();
                break;
            case HUMANO:
                this.jogador1 = new Maquina();
                break;
        }
        this.jogador1.setId(1);
    }

    /**
     * @return the jogador2
     */
    public Jogador getJogador2() {
        return jogador2;
    }

    /**
     * @param jogador2 the jogador2 to set
     */
    public void setJogador2(int tipo) {
        switch (tipo) {
            case MAQUINA:
                this.jogador2 = new Maquina();
                break;
            case HUMANO:
                this.jogador2 = new Maquina();
                break;
        }
        this.jogador2.setId(2);
    }
}
