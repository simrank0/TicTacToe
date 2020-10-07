import javax.swing.*;
import java.awt.*;
import java.awt.event.*;    

public class game extends JFrame implements ActionListener{
    JFrame newFrame;
    JMenuItem i1,i2;
    JLabel l, lp, lt; 
    Color c; 
    JButton p[] = new JButton[15];
    private String startGame = "X";
    private int xscore = 0;
    private int oscore = 0;

    public void gameScore(){
        lt.setText(String.valueOf(xscore));
        p[4].add(lt);
        lt.setText(String.valueOf(oscore));
        p[9].add(lt);
    }

    ActionListener a = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                if(!((((JButton) e.getSource()).getText()).isEmpty())){
                    return;
                }
                player();
                ((JButton) e.getSource()).setText(startGame);
                if(startGame == "X"){
                    ((JButton) e.getSource()).setForeground(Color.BLUE);
                }else ((JButton) e.getSource()).setForeground(Color.RED);
                ((JButton) e.getSource()).setFont(new Font("Serif", Font.ITALIC , 30));
            }

            Object[] options = {"Yes", "No"};

            int n=3;
            
            if(checkForWin() == true)
            {
                if(((JButton) e.getSource()).getText()=="X"){
                    xscore+=1;
                    p[4].setText(Integer.toString(xscore));
                    p[4].setFont(new Font("Serif", Font.ITALIC , 32));
                }else if(((JButton) e.getSource()).getText()=="O"){
                    oscore+=1;
                    p[9].setText(Integer.toString(oscore));
                    p[9].setFont(new Font("Serif", Font.ITALIC , 32));
                }
                n = JOptionPane.showOptionDialog(game.this, "Want to play another game?","Player " + 
                ((JButton) e.getSource()).getText() + " won the game", JOptionPane.YES_NO_OPTION , 
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }

            if(checkForTie() && !checkForWin()){
                n = JOptionPane.showOptionDialog(game.this, "Want to play another game?", "It's a tie", JOptionPane.YES_NO_OPTION , 
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }

            if(n == 0){
                resetButton();
            }else if(n == 1){
                System.exit(0);  
            } 

        }
    };

    private boolean checkForTie(){
        for(int i = 0; i <= 2; i++)
        {
            if(p[i].getText().isEmpty()){
                return false;
            }
        }
        for(int i = 5; i <= 7; i++)
        {
            if(p[i].getText().isEmpty()){
                return false;
            }
        }
        for(int i = 10; i <= 12; i++)
        {
            if(p[i].getText().isEmpty()){
                return false;
            }
        }
        return true;
    }
    
    public void resetButton()
    {
        for(int i = 0; i <= 2; i++)
        {
            p[i].setText("");
            p[i].setBackground(Color.PINK);
        }
        for(int i = 5; i <= 7; i++)
        {
            p[i].setText("");
            p[i].setBackground(Color.PINK);
        }
        for(int i = 10; i <= 12; i++)
        {
            p[i].setText("");
            p[i].setBackground(Color.PINK);
        }
    }

    public boolean checkForWin(){

        //horizontal win
        if( !(p[0].getText().isEmpty()) && p[0].getText()==p[1].getText() && p[1].getText()==p[2].getText()){
            p[0].setBackground(Color.orange);
            p[1].setBackground(Color.orange);
            p[2].setBackground(Color.orange);
            return true;
        }
        if( !(p[5].getText().isEmpty()) && p[5].getText()==p[6].getText() && p[6].getText()==p[7].getText()){
            p[5].setBackground(Color.orange);
            p[6].setBackground(Color.orange);
            p[7].setBackground(Color.orange);
            return true;
        }
        if( !(p[10].getText().isEmpty()) && p[10].getText()==p[11].getText() && p[11].getText()==p[12].getText()){
            p[10].setBackground(Color.orange);
            p[11].setBackground(Color.orange);
            p[12].setBackground(Color.orange);
            return true;
        }

        //vertical win
        if( !(p[0].getText().isEmpty()) && p[0].getText()==p[5].getText() && p[5].getText()==p[10].getText()){
            p[0].setBackground(Color.orange);
            p[5].setBackground(Color.orange);
            p[10].setBackground(Color.orange);
            return true;
        }
        if( !(p[1].getText().isEmpty()) && p[1].getText()==p[6].getText() && p[6].getText()==p[11].getText()){
            p[1].setBackground(Color.orange);
            p[6].setBackground(Color.orange);
            p[11].setBackground(Color.orange);
            return true;
        }
        if( !(p[2].getText().isEmpty()) && p[2].getText()==p[7].getText() && p[7].getText()==p[12].getText()){
            p[2].setBackground(Color.orange);
            p[7].setBackground(Color.orange);
            p[12].setBackground(Color.orange);
            return true;
        }

        //diagonal win
        if( !(p[0].getText().isEmpty()) && p[0].getText()==p[6].getText() && p[6].getText()==p[12].getText()){
            p[0].setBackground(Color.orange);
            p[6].setBackground(Color.orange);
            p[12].setBackground(Color.orange);
            return true;
        }
        if( !(p[2].getText().isEmpty()) && p[2].getText()==p[6].getText() && p[6].getText()==p[10].getText()){
            p[2].setBackground(Color.orange);
            p[6].setBackground(Color.orange);
            p[10].setBackground(Color.orange);
            return true;
        }
        return false;
    }

    private void player(){
        if(startGame.equals("X")){
            startGame = "O";
        }else startGame = "X";
    }
    public game(){
        newFrame = new JFrame("Tic Tac Tow Game");
        newFrame.setSize(640, 480);
        newFrame.setLayout(new BorderLayout());
        newFrame.setTitle("Tic Tac Toe Game");

        //Making menu
        JMenu mi1=new JMenu("File");  
        JMenu mi2=new JMenu("Help");  
        i1=new JMenuItem("New Game"); 
        i1.addActionListener(this);
        i2=new JMenuItem("About"); 
        i2.addActionListener(this);
        JMenuBar mb=new JMenuBar();
        mi1.add(i1); 
        mi2.add(i2); 
        mb.add(mi1); 
        mb.add(mi2); 
        newFrame.setJMenuBar(mb);

        //adding Label Tic Tac Toe Game
        l=new JLabel("TIC TAC TOE GAME", JLabel.CENTER);
        l.setFont(new Font("Serif", Font.ITALIC, 40));
        l.setForeground(new Color(255, 0, 0, 100));
        l.setBackground(Color.LIGHT_GRAY);
        l.setOpaque(true);

        //Adding Panel for Game board
        lp = new JLabel();
        lp.setLayout(new GridLayout(3,3));
        for(int i = 0; i<15; i++){
            p[i] = new JButton();
            p[i].setBackground(Color.PINK);
            lt = new JLabel("");
            lt.setFont(new Font("Serif", Font.ITALIC , 20));
            p[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            switch(i){
                case 3: lt = new JLabel("Player X");
                case 4:{
                    p[i].setBackground(Color.WHITE); 
                    p[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    lt.setFont(new Font("Serif", Font.ITALIC , 32));
                    p[i].setEnabled( false );
                    break;
                }
                case 8: lt = new JLabel("Player 0");
                case 9:{
                    p[i].setBackground(Color.WHITE); 
                    lt.setFont(new Font("Serif", Font.ITALIC , 32));
                    p[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    p[i].setEnabled( false );
                    break;
                }
                case 13:
                case 14: {
                    p[i].setBackground(Color.WHITE); 
                    lt = new JLabel("");
                    p[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    p[i].setEnabled( false );
                    break;
                }
                default: p[i].addActionListener(a);

            }

            if(i==4){
                // lt=new JLabel();
                p[4].setText(Integer.toString(xscore));
                p[4].setFont(new Font("Serif", Font.ITALIC , 32));
            }
            if(i==9){
                // lt=new JLabel();
                p[9].setText(Integer.toString(oscore));
                p[9].setFont(new Font("Serif", Font.ITALIC , 32));
            }

            p[i].setOpaque(true);
            p[i].add(lt, JPanel.CENTER_ALIGNMENT);
            lp.add(p[i]);
        }

        newFrame.add(l, BorderLayout.PAGE_START);
        newFrame.add(lp);
        newFrame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) { 
        if(e.getSource()==i2){
            JOptionPane.showMessageDialog(game.this, "Developer: Simran Khanna");
        }
        if(e.getSource()==i1){
            xscore=0;
            oscore=0;
            // p[4].setFont(new Font("Serif", Font.ITALIC , 32));
            // p[4].setFont(new Font("Serif", Font.ITALIC , 32));
            p[4].setText(Integer.toString(xscore));
            p[9].setText(Integer.toString(oscore));
            resetButton();
        }
     }
    public static void main(String[] args) {
        new game();
    }
}