package com.mycompany.outrun;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {

    //Variables del coche
    double posXcoche = 250;
    double posYcoche = 520;
    double incXcocheD = 550;
    double incXcocheI = 250;
    double LEVEL2 = 3.75;
    double LEVEL3 = 4.75;
    
    //Variables de los enemigos
    double posXenemigo1 = 300;
    double posXenemigo2 = 600;
    double posYenemigo1 = 0;
    double posYenemigo2 = 400;

    //Variable para contador
    int contador;
    
    //Variable para reinicio
    boolean terminaJuego = true;
    
    //Variable del fondo
    double REPETICIONFONDO = -615;
    
    //Variable para velocidad de coche y fondo
    double VELOCIDAD = 2.5;
    
    
    @Override
    public void start(Stage stage) {
        // Panel principal que contendrá los elementos de la pantalla
        Pane paneRoot = new Pane();
        Scene scene = new Scene(paneRoot, 1000, 615);
        stage.setTitle("Out Run");
        stage.setScene(scene);
        stage.show();
        
        //Audio al juego
        URL urlAudio = getClass().getResource("/Audio/audio.mp3");
        if(urlAudio != null) {
            try {
        AudioClip audioClip1 = new AudioClip(urlAudio.toURI().toString());
        audioClip1.play();
            } catch (URISyntaxException ex) {
        }}
        
        // Cargar la imagen crear objeto ImageView1
        Image img = new Image(getClass().getResourceAsStream("/images/fondo.png"));
        ImageView imgView = new ImageView(img);
        imgView.setLayoutX(0);
        imgView.setLayoutY(0);
        // Añadir el ImageView1 al panel principal de la pantalla
        paneRoot.getChildren().add(imgView);
        
        // Cargar la imagen crear objeto ImageView2
        Image img2 = new Image(getClass().getResourceAsStream("/images/fondo_1.png"));
        ImageView imgView2 = new ImageView(img2);
        imgView2.setLayoutX(0);
        imgView2.setLayoutY(-615);
        // Añadir el ImageView2 al panel principal de la pantalla
        paneRoot.getChildren().add(imgView2);
        
        // Cargar la imagen coche 1
        Image img3 = new Image(getClass().getResourceAsStream("/images/coche1.png"));
        ImageView imgView3 = new ImageView(img3);
        imgView3.setLayoutX(posXenemigo1);
        imgView3.setLayoutY(posYenemigo1);
        Rectangle rectCol1 = new Rectangle(posXenemigo1,posYenemigo1, 90,170);
        Group groupZona1 = new Group();
        groupZona1.getChildren().addAll(imgView3, rectCol1);
        // Hacer invisible el rectángulo
        rectCol1.setFill(Color.TRANSPARENT);
        
        // Añadir el coche 1 al panel principal de la pantalla
        paneRoot.getChildren().add(groupZona1);
        
        // Cargar la imagen coche 2 
        Image img4 = new Image(getClass().getResourceAsStream("/images/coche2.png"));
        ImageView imgView4 = new ImageView(img4);
        imgView4.setLayoutX(posXenemigo2);
        imgView4.setLayoutY(posYenemigo2);
        Rectangle rectCol2 = new Rectangle(posXenemigo2, posYenemigo2, 90,170);
        Group groupZona2 = new Group();
        groupZona2.getChildren().addAll(imgView4, rectCol2);
        // Hacer invisible el rectángulo
        rectCol2.setFill(Color.TRANSPARENT);

        // Añadir el coche 2 al panel principal de la pantalla
        paneRoot.getChildren().add(groupZona2);
        
        //Timline para el scroll de fondo
        Timeline fondo = new Timeline(
            new KeyFrame(Duration.seconds(0.010), (ActionEvent ae) -> {
               
                imgView.setLayoutY(imgView.getLayoutY() + VELOCIDAD);
                imgView2.setLayoutY(imgView2.getLayoutY() + VELOCIDAD);
                
                if (imgView.getLayoutY() == 615) {
                imgView.setLayoutY(-615);
                }
                else {
                    if (imgView2.getLayoutY() == 615) {
                        imgView2.setLayoutY(-615);              
        }}
        }));
        fondo.setCycleCount(Timeline.INDEFINITE);
        fondo.play();
        
        //Creación del coche
        //Elementos del coche
        Circle circleRueda1 = new Circle(25, 50, 20);
        Circle circleRueda2 = new Circle(175, 50, 20);
        Circle circleCabeza1 = new Circle(70, -10, 20);
        Circle circleCabeza2 = new Circle(130, -10, 20);
        Rectangle rectangleCoche = new Rectangle(0, 0, 200, 60);
        Rectangle rectangleCocheN = new Rectangle(0, 15, 200, 10);
        Rectangle rectangleCocheDerecha = new Rectangle(155, -35, 5, 40);
        Rectangle rectangleCocheArriba = new Rectangle(40, -40, 120, 5);
        Rectangle rectangleCocheIzquierda = new Rectangle(40, -35, 5, 40);
        Rectangle rectangleFaro1D = new Rectangle(20, 15, 20, 10);
        Rectangle rectangleFaro2D = new Rectangle(40, 15, 20, 10);
        Rectangle rectangleFaro1I = new Rectangle(140, 15, 20, 10);
        Rectangle rectangleFaro2I = new Rectangle(160, 15, 20, 10);
        Circle circleEspejo1 = new Circle(35, -10, 6);
        Circle circleEspejo2 = new Circle(165, -10, 6);
        Rectangle rectangleCocheCol = new Rectangle(-10, -50, 225, 120);

        // Colorear cada elemento
        circleRueda1.setFill(Color.BLACK);
        circleRueda2.setFill(Color.BLACK);
        circleCabeza1.setFill(Color.BROWN);
        circleCabeza2.setFill(Color.YELLOW);
        rectangleCoche.setFill(Color.RED);
        rectangleCocheN.setFill(Color.BLACK);
        rectangleCocheDerecha.setFill(Color.RED);
        rectangleCocheArriba.setFill(Color.RED);
        rectangleCocheIzquierda.setFill(Color.RED);
        rectangleFaro1D.setFill(Color.YELLOW);
        rectangleFaro2D.setFill(Color.WHITE);
        rectangleFaro1I.setFill(Color.YELLOW);
        rectangleFaro2I.setFill(Color.WHITE);
        circleEspejo1.setFill(Color.BLACK);
        circleEspejo2.setFill(Color.BLACK);
        rectangleCocheCol.setFill(Color.TRANSPARENT);
        
        // Agrupar todos los elementos
        Group groupCochePrincipal = new Group();
        groupCochePrincipal.getChildren().add(circleRueda1);
        groupCochePrincipal.getChildren().add(circleRueda2);
        groupCochePrincipal.getChildren().add(circleCabeza1);
        groupCochePrincipal.getChildren().add(circleCabeza2);
        groupCochePrincipal.getChildren().add(rectangleCoche);
        groupCochePrincipal.getChildren().add(rectangleCocheN);
        groupCochePrincipal.getChildren().add(rectangleCocheDerecha);
        groupCochePrincipal.getChildren().add(rectangleCocheArriba);
        groupCochePrincipal.getChildren().add(rectangleCocheIzquierda);
        groupCochePrincipal.getChildren().add(rectangleFaro1D);
        groupCochePrincipal.getChildren().add(rectangleFaro2D);
        groupCochePrincipal.getChildren().add(rectangleFaro1I);
        groupCochePrincipal.getChildren().add(rectangleFaro2I);
        groupCochePrincipal.getChildren().add(circleEspejo1);
        groupCochePrincipal.getChildren().add(circleEspejo2);
        groupCochePrincipal.getChildren().add(rectangleCocheCol);
        
        // Posicionar el grupo en la pantalla
        groupCochePrincipal.setLayoutX(posXcoche);
        groupCochePrincipal.setLayoutY(posYcoche);
        
        // Añadir el grupo al contenedor principal
        paneRoot.getChildren().add(groupCochePrincipal);
    
        //Movimiento izquierda derecha del coche y boton ENTER para el reinicio
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case RIGHT: // Player 1 Derecha
                    groupCochePrincipal.setLayoutX(posXcoche = incXcocheD);
                    break;
                case LEFT: // Player 1 Izquierda
                    groupCochePrincipal.setLayoutX(posXcoche = incXcocheI);
                    break;   
                case ENTER:
                    if (terminaJuego == false)    
                        this.resetGame();
                        terminaJuego = true;
                        VELOCIDAD = 2.5;
                        URL urlAudio2 = getClass().getResource("/Audio/audio2.mp3");
                            if(urlAudio2 != null) {
                        try {
                    AudioClip audioClip2 = new AudioClip(urlAudio2.toURI().toString());
                    audioClip2.play();
                            } catch (URISyntaxException ex) {
    }}
                        break;
            } 
        });
    
        //Label para el marcador
        HBox tiempo = new HBox();
        tiempo.setAlignment(Pos.CENTER);
        tiempo.setSpacing(10);
        paneRoot.getChildren().add(tiempo);
        
        //Texto que siempre pondra Tiempo:
        Text textContador = new Text("Tiempo:");
        textContador.setFont(Font.font(40));
        textContador.setFill(Color.BLACK);
        textContador.setTranslateX(40);
        textContador.setTranslateX(10);
        tiempo.getChildren().add(textContador);

        //Label para el tiempo
        Label labelContador = new Label();
        labelContador.setText(String.valueOf(contador));
        labelContador.setFont(new Font(40));
        labelContador.setTextFill(Color.BLACK);
        labelContador.setTranslateX(160);
        labelContador.setTranslateY(1);
        paneRoot.getChildren().add(labelContador); 
        
        //Timline para el marcador
        Timeline marcador = new Timeline(        
            new KeyFrame(Duration.seconds(1.00), (ActionEvent ae) -> {
                    labelContador.setText(String.valueOf(contador));
                    contador++;
                    if (terminaJuego == false) 
                    contador = 0;    
                    
            }));
        marcador.setCycleCount(Timeline.INDEFINITE);
        marcador.play();

        //Timline para el movimiento de los coches enemigos
        Timeline enemigos = new Timeline(        
            new KeyFrame(Duration.seconds(0.010), (ActionEvent ae) -> {
                
                posYenemigo1 += VELOCIDAD;
                groupZona1.setLayoutY(posYenemigo1);
                posYenemigo2 += VELOCIDAD;
                groupZona2.setLayoutY(posYenemigo2);
                
                    Random random = new Random();          
                    if (posYenemigo1 == 615) {             
                        int valor = random.nextInt(2); // Genera número entre 0 y 1
                        switch(valor){
                            case 0:
                            posYenemigo1 = -615;
                            groupZona1.setLayoutX(0);
                            break;
                            case 1:
                            posYenemigo1 = -615;
                            groupZona1.setLayoutX(300);
                            break;
                        }}

                    if (posYenemigo2 == 615) {
                        int valor = random.nextInt(2); // Genera número entre 0 y 1
                        switch(valor){
                            case 0:
                            posYenemigo2 = -615;
                            groupZona2.setLayoutX(0);
                            break;
                            case 1:
                            posYenemigo2 = -615;
                            groupZona2.setLayoutX(-300);                        
                            break;                 
                    }}}));
        enemigos.setCycleCount(Timeline.INDEFINITE);
        enemigos.play();
        
        //Timline para el Nivel 2
        Timeline Nivel2 = new Timeline(        
            new KeyFrame(Duration.seconds(0.010), (ActionEvent ae) -> {
                
                if (contador >= 15) {
                    posYenemigo1 += LEVEL2;
                    groupZona1.setLayoutY(posYenemigo1);
                    posYenemigo2 += LEVEL2;
                    groupZona2.setLayoutY(posYenemigo2);
                    }
                                
                Random random = new Random();           
                if (posYenemigo1 >= 615) {                   
                    int valor = random.nextInt(2); // Genera número entre 0 y 1
                    switch(valor){
                        case 0:
                        posYenemigo1 = -615;
                        groupZona1.setLayoutX(0);
                        break;
                        case 1:
                        posYenemigo1 = -615;
                        groupZona1.setLayoutX(300);
                        break;
                    }}
                
                if (posYenemigo2 >= 615) {
                    int valor = random.nextInt(2); // Genera número entre 0 y 1
                    switch(valor){
                        case 0:
                        posYenemigo2 = -615;
                        groupZona2.setLayoutX(0);
                        break;
                        case 1:
                        posYenemigo2 = -615;
                        groupZona2.setLayoutX(-300);                        
                        break;                            
                    }}}));
        Nivel2.setCycleCount(Timeline.INDEFINITE);
        Nivel2.play();
        
        //Timline para el Nivel 3
        Timeline Nivel3 = new Timeline(        
           new KeyFrame(Duration.seconds(0.010), (ActionEvent ae) -> {
                
                if (contador >= 30) {
                    posYenemigo1 += LEVEL3;
                    groupZona1.setLayoutY(posYenemigo1);
                    posYenemigo2 += LEVEL3;
                    groupZona2.setLayoutY(posYenemigo2);    
                    }
                    
                Random random = new Random();            
                if (posYenemigo1 >= 615) {
                    int valor = random.nextInt(2); // Genera número entre 0 y 1
                    switch(valor){
                        case 0:
                        posYenemigo1 = -615;
                        groupZona1.setLayoutX(0);
                        break;
                        case 1:
                        posYenemigo1 = -615;
                        groupZona1.setLayoutX(300);
                        break;
                    }}
                
                if (posYenemigo2 >= 615) {
                    int valor = random.nextInt(2); // Genera número entre 0 y 1
                    switch(valor){
                        case 0:
                        posYenemigo2 = -615;
                        groupZona2.setLayoutX(0);
                        break;
                        case 1:
                        posYenemigo2 = -615;
                        groupZona2.setLayoutX(-300);                        
                        break;                            
                    }}}));
        Nivel3.setCycleCount(Timeline.INDEFINITE);
        Nivel3.play();
       
        //Timline para cuando colisionen los coches resetear el juego
        Timeline juego = new Timeline(
            new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                
                // Parte del código para detectar la colisión de los rectángulo
                Shape zonaColision1 = Shape.intersect(rectCol1, rectangleCocheCol);
                Shape zonaColision2 = Shape.intersect(rectCol2, rectangleCocheCol);
        
                boolean Colision1 = zonaColision1.getBoundsInLocal().isEmpty();
                boolean Colision2 = zonaColision2.getBoundsInLocal().isEmpty();
                
                    if (Colision1 == false) {
                        this.resetGame();
                        }

                    if (Colision2 == false) {
                        this.resetGame();
                    }       
        }));
        juego.setCycleCount(Timeline.INDEFINITE);
        juego.play(); 

    }    
    
        private void resetGame() {      
        posXcoche = 250;
        posYcoche = 520;
        incXcocheD = 550;
        incXcocheI = 250;
        
        posXenemigo1 = 300;
        posXenemigo2 = 600;
        posYenemigo1 = 0;
        posYenemigo2 = 400;
        
        contador = 0;
        terminaJuego = false;  
        VELOCIDAD = 0;
                   
        URL urlAudio = getClass().getResource("/Audio/audio.mp3");
        if(urlAudio != null) {
            try {
        AudioClip audioClip1 = new AudioClip(urlAudio.toURI().toString());
        audioClip1.stop();
            } catch (URISyntaxException ex) {
    }}
        
        }
       
    public static void main(String[] args) {
        launch();
    }

}