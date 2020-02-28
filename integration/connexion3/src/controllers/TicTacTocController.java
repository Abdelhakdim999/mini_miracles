/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Game;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dimassi Abdelhak
 */
public class TicTacTocController implements Initializable {
private Game game;
    @FXML
    private AnchorPane square_top_left_O;
    @FXML
    private AnchorPane square_top_left_X;
    @FXML
    private AnchorPane square_top_left;
    @FXML
    private AnchorPane square_left_O;
    @FXML
    private AnchorPane square_left_X;
    @FXML
    private AnchorPane square_left;
    @FXML
    private AnchorPane square_bottom_right;
    @FXML
    private AnchorPane square_bottom_left_O;
    @FXML
    private AnchorPane square_bottom_left_X;
    @FXML
    private AnchorPane square_bottom_left;
    @FXML
    private AnchorPane square_top;
    @FXML
    private AnchorPane square_top_O;
    @FXML
    private AnchorPane square_top_X;
    @FXML
    private AnchorPane square_O;
    @FXML
    private AnchorPane square_X;
    @FXML
    private AnchorPane square;
    @FXML
    private AnchorPane square_bottom_O;
    @FXML
    private AnchorPane square_bottom_X;
    @FXML
    private AnchorPane square_bottom;
    @FXML
    private AnchorPane square_top_right_O;
    @FXML
    private AnchorPane square_top_right_X;
    @FXML
    private AnchorPane square_top_right;
    @FXML
    private AnchorPane square_right_O;
    @FXML
    private AnchorPane square_right_X;
    @FXML
    private AnchorPane square_right;
    @FXML
    private AnchorPane square_bottom_right_O;
    @FXML
    private AnchorPane square_bottom_right_X;
    @FXML
    private Label score_player1;
    @FXML
    private Label score_tie;
    @FXML
    private Label score_player2;
    public AnchorPane border;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       game=new Game(border);
        inisializeGame();     
        detectedKey();
        game.loadGame(true);
    }    
    public void inisializeGame(){
         game
                 .createScorePlayer1(score_player1)
                 .createScorePlayer2(score_player2)
                 .createScoreTie(score_tie)
                 .createSquare(square_top_left_X, square_top_left_O)
                 .createSquare(square_top_X, square_top_O)
                 .createSquare(square_top_right_X, square_top_right_O)
                 .createSquare(square_left_X, square_left_O)
                 .createSquare(square_X, square_O)
                 .createSquare(square_right_X, square_right_O)
                 .createSquare(square_bottom_left_X, square_bottom_left_O)
                 .createSquare(square_bottom_X, square_bottom_O)
                 .createSquare(square_bottom_right_X, square_bottom_right_O)
                 .createBoard();
    }
    
    public void detectedKey(){
        square_top_left.setOnMouseReleased(e->{game.play(0,0);});
        square_top.setOnMouseReleased(e->{game.play(0,1);});
        square_top_right.setOnMouseReleased(e->{game.play(0,2);});
        square_left.setOnMouseReleased(e->{game.play(1,0);});
        square.setOnMouseReleased(e->{game.play(1,1);});
        square_right.setOnMouseReleased(e->{game.play(1,2);});
        square_bottom_left.setOnMouseReleased(e->{game.play(2,0);});
        square_bottom.setOnMouseReleased(e->{game.play(2,1);});
        square_bottom_right.setOnMouseReleased(e->{game.play(2,2);});
    }
    
}
