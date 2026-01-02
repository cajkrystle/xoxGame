package fromGPT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGame extends JPanel implements ActionListener, KeyListener {
    private int playerX = 250;  // Player's initial position
    private int playerY = 450;
    private int obstacleX = (int) (Math.random() * 500);
    private int obstacleY = 0;
    private boolean gameOver = false;
    private Timer timer;

    public SimpleGame() {
        timer = new Timer(20, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 600, 500);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over!", 220, 250);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(playerX, playerY, 50, 50);  // Player

            g.setColor(Color.RED);
            g.fillRect(obstacleX, obstacleY, 50, 50);  // Obstacle
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        obstacleY += 5;

        if (obstacleY > 500) {
            obstacleY = 0;
            obstacleX = (int) (Math.random() * 500);
        }

        if (new Rectangle(playerX, playerY, 50, 50).intersects(new Rectangle(obstacleX, obstacleY, 50, 50))) {
            gameOver = true;
            timer.stop();
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT && playerX > 0) {
                playerX -= 20;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && playerX < 550) {
                playerX += 20;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Java Game");
        SimpleGame game = new SimpleGame();
        frame.add(game);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
