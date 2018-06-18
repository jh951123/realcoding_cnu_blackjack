package kr.ac.cnu.web.games.blackjack;

import java.util.Map;

/**
 * Created by rokim on 2018. 5. 27..
 */
public class Evaluator {
    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap, Dealer dealer) {
        this.playerMap = playerMap;
        this.dealer = dealer;
    }

    public boolean evaluate() {
        if (playerMap.values().stream().anyMatch(player -> player.isPlaying())) {
            return false;
        }

        int dealerResult = dealer.getHand().getCardSum();

        if (dealerResult > 21) {
            if (playerMap.values().stream().anyMatch(player -> player.getHand().getIsDouble())) {
                playerMap.forEach((s, player) -> player.DoubleWin());
                playerMap.forEach((s, player) -> player.getHand().setIsDouble());
            }
            else {
                playerMap.forEach((s, player) -> player.win());
            }
            return true;
        }

        playerMap.forEach((s, player) -> {
            int playerResult = player.getHand().getCardSum();
            // player의 card수에 따라 다르게 처리
            if(player.getHand().getCardList().size() == 2){
                if(playerResult == 21){
                    player.blackjackwin();
                }
            }
            else{
                if(playerResult > 21){
                    if(player.getHand().getIsDouble() == true){
                        player.DoubleLost();
                        player.getHand().setIsDouble();
                    }
                    else {
                        player.lost();
                    }
                }
                else if(playerResult > dealerResult){
                    if(player.getHand().getIsDouble() == true){
                        player.DoubleWin();
                        player.getHand().setIsDouble();
                    }
                    else {
                        player.win();
                    }
                }
                else if(playerResult == dealerResult){
                    player.tie();
                }
                else{
                    if(player.getHand().getIsDouble()==true){
                        player.DoubleLost();
                        player.getHand().setIsDouble();
                    }
                    else{
                        player.lost();
                    }
                }
            }
        });

        return true;
    }


}
