package UI.Listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Game.Player;
import UI.Pane.ShopPane;

public class ShopCardListener extends MouseAdapter{
    int price;
    int index;
    ShopPane shopPane = ShopPane.getInstance();
    Player player = Player.getInstance();
    public ShopCardListener(int price, int index) {
        this.price = price;
        this.index = index;
    }
    public void mousePressed(MouseEvent e) {
        if(player.canTakeGold(price) == false) return; 
        player.takeGold(price);
        shopPane.buyItem(index);
    }
}
