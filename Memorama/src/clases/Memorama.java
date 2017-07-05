/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Random;

/**
 *
 * @author UTNG
 */
public class Memorama {

    private String[] tablero = new String[28];
    private String jugador1;
    private String jugador2;
    private int paresJ1;
    private int paresJ2;
    private int catego;
    private boolean turnoJ1; //true=tira el jugador1, false=Tira el jugador2
    private boolean banNuevo; //false=No se ha registrado los datos de un nuevo juego
    // true ya se registraron los datos de un nuevo juego
    private int tiro1; //primer tiro
    private int tiro2;// segundo tiro
    private boolean banTiro1 = true; // true=tiro1, false=tiro2
    private boolean[] volteadas = new boolean[28];
    private int nSel; // numero de tarjetas seleccionadas
    private int fondo=1;
    private boolean banInicio;
    

    public Memorama() {
        paresJ1 = 0;
        paresJ2 = 0;
        catego = 0;
        turnoJ1 = true;
        banNuevo=false;
        banTiro1=true;
        nSel=0;
        for(int i=0;i<28;i++){
        volteadas[i]=false;
    }
    }
    public void inicializar (){// permite iniciar el juego
        paresJ1 = 0;
        paresJ2 = 0;
        //catego = 0;
        turnoJ1 = true;
        banNuevo=false;
        banTiro1=true;
        nSel=0;
        for(int i=0;i<28;i++){
        volteadas[i]=false;
    }
    }

    public void setFondo(int fondo) {
        this.fondo = fondo;
    }

    public int getFondo() {
        return fondo;
    }

    public void setBanInicio(boolean banInicio) {
        this.banInicio = banInicio;
    }

    public boolean isBanInicio() {
        return banInicio;
    }
    

    public boolean isBanTiro1() {
        return banTiro1;
    }

    public int getTiro1() {
        return tiro1;
    }

    public int getTiro2() {
        return tiro2;
    }

    public void setTiro(int tiro) {
        if (!volteadas[tiro]) { // no ha sido volteada esa tarjeta?
            if (banTiro1) {
                tiro1 = tiro;
                banTiro1 = false;
            } else {
                tiro2 = tiro;
                banTiro1 = true;
            }
            volteadas[tiro] = true;
            nSel++;

        }
    }

    public boolean verificar() {//verifica quien volteo un par de tarjetas para que pueda seguir jugando.
        if (nSel != 0 && tablero[tiro1].equals(tablero[tiro2])) {
            if (turnoJ1) { // turno del jugador 1
                paresJ1++;
                nSel = 0;
            } else {
                paresJ2++;
                nSel = 0;
            }
            return true;
        } else {
            volteadas[tiro1] = false;//pone disponibles ambas tarjetas
            volteadas[tiro2] = false;
            turnoJ1 = !turnoJ1; // cambio de turno
            nSel = 0;
            return false;
        }
    }

    public int getnSel() {
        return nSel;
    }

    public void setBanNuevo(boolean banNuevo) {
        this.banNuevo = banNuevo;
    }

    public boolean isBanNuevo() {
        return banNuevo;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public void setCatego(int catego) {
        this.catego = catego;
    }

    public int getCatego() {
        return catego;
    }

    public String getNomCatego() {//muestra e nombre y las categorias que tenemos disponibles 
        if (catego == 1) {
            return "Famosos";

        } else if (catego == 2) {
            return "Caricaturas";
        } else if (catego == 3) {
            return "Equipos";
        }else if (catego == 4){
            return "Frutas";
        }else {
            return null;
        }
    }

    public String getJugador1() {
        return jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public int getParesJ1() {
        return paresJ1;
    }

    public int getParesJ2() {
        return paresJ2;
    }

    public String getTablero(int pos) {
        return tablero[pos];
    }

    /**
     * metodo que devuelve el ganador del juego.
     *
     * @return 1=jugador #1, 2=jugador #2, 3=empate, 0=Aun no termina el juego
     */
    public int ganador() {
        if (paresJ1 + paresJ2 == 14) {
            if (paresJ1>paresJ2) {
                return 1; //gano el juador 1
            } else if (paresJ2>paresJ1){
                return 2; //gana el 2
            }else{
                return 3; // empate
            }
        } else {
            return 0; // aun no termina el juego
        }
    }

    public boolean isTurnoJ1() {
        return turnoJ1;
    }

    public void seleccionarImagenes() {
        /*
        metodo con el cual podemos elegir la categoria y que esta tenga asignada 
        correctamente las imagenes que pertenecen a dicha caegoria,
        en este jurgo se presentaran siete categotias diferentes
        */
        boolean[] seleccionadas = new boolean[30];
        Random r1 = new Random();
        int pos = 0;
        int i = 0;
        switch (catego) {
            case 1: //famosos
                while (i < 14) {
                    pos = r1.nextInt(30);
                    if (!seleccionadas[i]) {
                        tablero[i] = "/famosos/c" + (i + 1) + ".jpg";
                        tablero[i + 14] = "/famosos/c" + (i + 1) + ".jpg";
                        seleccionadas[i] = true;
                        i++;
                    }
                }
                break;
            case 2://caricaturas
                while (i < 14) {
                    pos = r1.nextInt(30);
                    if (!seleccionadas[i]) {
                        tablero[i] = "/caricaturas/c" + (i + 1) + ".jpg";
                        tablero[i + 14] = "/caricaturas/c" + (i + 1) + ".jpg";
                        seleccionadas[i] = true;
                        i++;
                    }

                }
                break;
                case 3://logo de equipos de futbol
                while (i < 14) {
                    pos = r1.nextInt(30);
                    if (!seleccionadas[i]) {
                        tablero[i] = "/eqipo/a" + (i + 1) + ".jpg";
                        tablero[i + 14] = "/eqipo/a" + (i + 1) + ".jpg";
                        seleccionadas[i] = true;
                        i++;
                    }

                }
                break;
                  case 4:
                while (i < 14) { // frutas en forma de caricatura
                    pos = r1.nextInt(30);
                    if (!seleccionadas[i]) {
                        tablero[i] = "/Frutas/a" + (i + 1) + ".jpg";
                        tablero[i + 14] = "/Frutas/a" + (i + 1) + ".jpg";
                        seleccionadas[i] = true;
                        i++;
                    }

                }
                break;
        
        }
    }

    public void mezclarImagenes() {// mezcla todas la imagenes por categoria 
        Random r1 = new Random();
        int x;
        int y;
        String tempo = "";
        for (int i = 0; i < 500; i++) {
            x = r1.nextInt(28);
            y = r1.nextInt(28);
            tempo = tablero[x];
            tablero[x] = tablero[y];
            tablero[y] = tempo;

        }
    }

}
