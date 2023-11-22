package UI.Listener;

import UI.Pane.MapPane;
import java.awt.event.*;

public class MapPaneListener extends MouseAdapter {
    private MapPane map;
    public MapPaneListener(MapPane map) {
        this.map = map;
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        int scrollDirection = e.getWheelRotation();


        if(scrollDirection == -1) {
            map.drawingPanel.scrollMeasure = Math.min(1,map.drawingPanel.scrollMeasure + 0.05);
        }else if(scrollDirection == 1) {
            map.drawingPanel.scrollMeasure = Math.max(0,map.drawingPanel.scrollMeasure - 0.05);
        }
        
        map.drawingPanel.repaint();
    }
}
