package MyFarm;

import MyFarm.land.Land;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel
{
    JLabel gameOver = new JLabel("BRUH!!!");
    JButton restart = new JButton("Start a New Game");
    public GameOverPanel(MyFarmModel model, MyFarmView view){
        this.setPreferredSize(new Dimension(800,460));
        this.setBackground(Palette.SELECTED.getColor());
        this.setLayout(new BorderLayout());

        gameOver.setIcon(Icons.GAME_OVER.getImageIcon());

        restart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.resetModel();
                view.resetPanels();

                view.cardLayout.next(view.mainPanel);
            }
        });

        this.add(gameOver, BorderLayout.CENTER);
        this.add(restart, BorderLayout.SOUTH);
    }
}
