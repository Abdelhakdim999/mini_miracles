/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import animatefx.animation.Flash;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Dimassi Abdelhak
 */
public class Game {
    private List<Square> listSquare=new LinkedList<>();
    private Score scorePlayer1;
    private Score scorePlayer2;
    private Score scoreTie;
    private Board board;
    private AnchorPane border;

    public Game(AnchorPane border) {
        this.border=border;
    }
    
    
    public Game createSquare(AnchorPane containerOfX, AnchorPane containerOfO){
        listSquare.add(new Square(containerOfX, containerOfO));
        return this;
    }
    
    public Game createBoard(){
        if(listSquare.size()==Board.size()*Board.size()){
           Square[][] mat=new Square[Board.size()][Board.size()];
            for(int i=0;i<Board.size();i++){
                for(int j=0;j<Board.size();j++){
                    mat[i][j]=listSquare.get(i*Board.size()+j);
                }
            }
            board=new Board(mat);
        }
        return this;
    }
    
    public Game createScorePlayer1(Label scoreValue){
        scorePlayer1=new Score(scoreValue);
        return this;
    }
    
    public Game createScorePlayer2(Label scoreValue){
        scorePlayer2=new Score(scoreValue);
        return this;
    }
    
    public Game createScoreTie(Label scoreValue){
        scoreTie=new Score(scoreValue);
        return this;
    }
    
    public void play(int i,int j){
        if(board.isWinner(Square.Value.X)||board.isWinner(Square.Value.O)){
            loadGame(false);
            return;
        }
        if(board.isPlaine()){
            loadGame(false);
            return;
        }
        if(board.makeValue(i, j)){
            if(board.isWinner(Square.Value.X)){
                board.makeAnimationWinner();
                scorePlayer1.addValue(1);
            }else{
                board.putRandomValue();
                if(board.isWinner(Square.Value.O)){
                    board.makeAnimationWinner();
                    scorePlayer2.addValue(1);
                }else if(board.isPlaine()){
                    new Flash(border).setCycleCount(5).play();
                    scoreTie.addValue(1);
                }
            }
        }
    }
    
    public void loadGame(boolean newGame){
        if(newGame){
            board.loadBoard();
            scorePlayer1.loadScore();
            scorePlayer2.loadScore();
            scoreTie.loadScore();
        }else{
            board.loadBoard();
        }
    }
}
