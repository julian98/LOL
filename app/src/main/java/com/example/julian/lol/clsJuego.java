package com.example.julian.lol;

import android.media.MediaPlayer;
import android.util.Log;

import org.cocos2d.actions.Scheduler;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Label;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

import java.util.TimerTask;

/**
 * Created by Julian on 21/11/2016.
 */
public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    Sprite ImagenFondo;
    Sprite Jugador;
    Label lblTitulo;
    Sprite Obstaculo;

    public clsJuego(CCGLSurfaceView VistaDelJuego) {
        _VistaDelJuego=VistaDelJuego;
    }

    public void ComenzarJuego(){
        Director.sharedDirector().attachInView(_VistaDelJuego);

        PantallaDelDispositivo=Director.sharedDirector().displaySize();
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }



    private Scene EscenaDelJuego(){
        Scene EscenaADevolver;
        EscenaADevolver= Scene.node();
        CapaDeFondo MiCapaFondo=new CapaDeFondo();
        CapaFrente MiCapaFrente =new CapaFrente();
        EscenaADevolver.addChild(MiCapaFondo, -10);
        EscenaADevolver.addChild(MiCapaFrente, 10);
        return EscenaADevolver;
    }

    class CapaDeFondo extends Layer {

        public CapaDeFondo() {
            MediaPlayer mpMusicaFondo;
            mpMusicaFondo=MediaPlayer.create(_VistaDelJuego, R.raw.musica);
            mpMusicaFondo.start();

        PonerImagenFondo();
    }
    private void PonerImagenFondo() {

        ImagenFondo=Sprite.sprite("fondo.png");
        ImagenFondo.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height/2);
        ImagenFondo.runAction(ScaleBy.action(0.01f, 2.0f, 2.0f));
        super.addChild(ImagenFondo);
    }
}
class CapaFrente extends Layer {
    public CapaFrente() {
        PonerJugadorPosicionInicial();
        PonerTitulo();
        PonerObstaculo1();
        /*final TimerTask PonerObstaculos;
        PonerObstaculos = new TimerTask() {
            @Override
            public void run() {
                PonerObstaculo1();
            }
        };
        RelojPonedorDeObstaculos= new Scheduler.Timer();
        RelojPonedorDeObstaculos.schedule(PonerObstaculos, 0, 1000);*/
    }
    private void PonerJugadorPosicionInicial(){
        Jugador=Sprite.sprite("personaje.png");
        Jugador.setPosition(100, 300);
        super.addChild(Jugador);
    }
    private void PonerObstaculo1() {
        Obstaculo = Sprite.sprite("obstaculo.png");
        CCPoint PosicionIncial;
        PosicionIncial = new CCPoint();
        PosicionIncial.y= PantallaDelDispositivo.height/2;
        PosicionIncial.x= PantallaDelDispositivo.width/2;
        Obstaculo.setPosition(PosicionIncial.x, PosicionIncial.y);
        /*
        Float PosicionFinalX, PosicionFinalY;
        PosicionFinalX=0f;
        PosicionFinalY=PosicionFinalY;
        Obstaculo.runAction(MoveTo.action(3, PosicionFinalX, PosicionFinalY));
         */
        super.addChild(Obstaculo);
    }
    private void PonerTitulo()
    {
        lblTitulo=Label.label("Marathon", "verdana", 30);
        float AltoTitulo;
        AltoTitulo=lblTitulo.getHeight();
        lblTitulo.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height-AltoTitulo/2);
        super.addChild(lblTitulo);
    }
}



}

