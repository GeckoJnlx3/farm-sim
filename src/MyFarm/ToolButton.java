package MyFarm;

import javax.swing.JButton;

/**
 * ToolButton enum - extends JButton
 */
public class ToolButton extends JButton {
    String name;

    /**
     * Constructor for ToolButton
     * @param name text associated with button
     */
    public ToolButton(String name){
        super(name);
        this.name = name;
        setFocusable(false);
    }

    /**
     * changes the button to selected
     */
    void selectButton(){
        setText("selected");
    }

    /**
     * changes the button text back to its name
     */
    void deselectButton(){
        setText(this.name);
    }
    
}
