package MyFarm;

import javax.swing.JButton;

public class ToolButton extends JButton {
    String name;

    public ToolButton(String name){
        super(name);
        this.name = name;
        setFocusable(false);
    }


}