package Test5;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class FifthTest {
public static void main(String[] args) {

JFrame fr = new JFrame("PanelGame");
fr.setSize(550,400);

fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
fr.getContentPane().setBackground(new Color(255, 255, 255));

DrawAll panel1 =new DrawAll();

panel1.setOpaque(false);

fr.add(panel1);

fr.setVisible(true);

    }
    
}


//memory current puzzle
class Datas extends JPanel {
    //0=BLUE 1=RED 2=YELLOW -1 is Wall
    static double[][] map={
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // 7*7
        {-1., 0.,0.,0.,0.,0., -1.},
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1., 2.,2.,2.,2.,2., -1.},
        {-1., 0.,0.,0.,0.,0., -1.},
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}
    };

    double[][] firstMap={
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // 7*7
        {-1., 0.,0.,0.,0.,0., -1.},
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1., 2.,2.,2.,2.,2., -1.},
        {-1., 0.,0.,0.,0.,0., -1.},
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}
    };

    int MX = map[0].length,MY = map.length;
    double panelSize=40.;
    
    Datas(){
        
    }
    
    public void MakeCode(){ 
        for(int i=1;i<MY-1;i++){
            for(int k=1 ;k<MX-1;k++){
                System.out.print(map[i][k]%3 +" ");
            }
        System.out.println("");
        }
    System.out.println("your PuzzeleCode!!");
    }

    public void Reset(){
        for(int i=1 ; i<MY-1;i++){
            for(int k=1; k<MX-1;k++){
                map[i][k]=firstMap[i][k];
            }
        }
    }

    public void ColorChange(double x,double y,int cp){
        int xIndex=(int)((x-60.)/panelSize);
        int yIndex=(int)((y-60.)/panelSize);
        if(cp==0){
            map[yIndex][xIndex-1]=(map[yIndex][xIndex-1]+1.)%3.;
            map[yIndex][xIndex+1]=(map[yIndex][xIndex+1]+1.)%3.;
            map[yIndex+1][xIndex]=(map[yIndex+1][xIndex]+1.)%3.;
            map[yIndex-1][xIndex]=(map[yIndex-1][xIndex]+1.)%3.;
        }else{
            map[yIndex-1][xIndex-1]=(map[yIndex-1][xIndex-1]+1.)%3.;
            map[yIndex+1][xIndex+1]=(map[yIndex+1][xIndex+1]+1.)%3.;
            map[yIndex+1][xIndex-1]=(map[yIndex+1][xIndex-1]+1.)%3.;
            map[yIndex-1][xIndex+1]=(map[yIndex-1][xIndex+1]+1.)%3.;
        }
    }

    @Override
    public void paint(Graphics g){ 
        g.setColor(Color.black);
        g.drawRect(100, 100,(int)panelSize*5, (int)panelSize*5);//frame
        for(int i =1; i<MY-1;i++){
            for(int k =1;k<MX-1;k++){
                switch((int)(map[i][k])){
                case 0:
                    g.setColor(Color.blue);
                    g.fillRect(100+40*(k-1),100+40*(i-1), 40, 40);
                    break;
                case 1:
                    g.setColor(Color.red);
                    g.fillRect(100+40*(k-1),100+40*(i-1), 40, 40);
                    break;
                case 2:
                    g.setColor(Color.yellow);
                    g.fillRect(100+40*(k-1),100+40*(i-1), 40, 40);
                    break;
                }
            }
        }
    }
}
class ChangingPanel extends JPanel{
    int xp,yp,cp=1;

    public void Setp(double x ,double y,int cp){
        xp=(int)((x-60.)/40.);
        yp=(int)((y-60.)/40.);
        this.cp =cp;
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D)g;
        BasicStroke wideStroke = new BasicStroke(2);
		g2.setStroke(wideStroke);
        if(cp==1){
            g2.drawRect(100+40*(xp-2),100+40*(yp-1), 40, 40);
            g2.drawRect(100+40*(xp-1),100+40*(yp), 40, 40);
            g2.drawRect(100+40*(xp),100+40*(yp-1), 40, 40);
            g2.drawRect(100+40*(xp-1),100+40*(yp-2), 40, 40);
        }else{
            g2.drawRect(100+40*(xp-2),100+40*(yp), 40, 40);// left down
            g2.drawRect(100+40*(xp),100+40*(yp), 40, 40);// right down
            g2.drawRect(100+40*(xp),100+40*(yp-2), 40, 40);// right up
            g2.drawRect(100+40*(xp-2),100+40*(yp-2), 40, 40);// left up
        }
        g.setColor(Color.white);
        g.fillRect(0,0,101,101);
        g.fillRect(50, 50, 50, 300); // left
        g.fillRect(100,50,210,50); // up
        g.fillRect(50,300,260,50); //under
        g.fillRect(300,50,41,300); // right
}

}
class DontRepeatOb extends JPanel {
    int[] xTri={55+355,63+355,70+355};
    int[] yTri={346-100,330-100,348-100};
    int dot =0;
    DontRepeatOb(){
    }
    
    public void PlusDot(){ 
        dot+=1;
            }
     public void ToZeroDot(){
        dot=0;
    }
    @Override
public void paint(Graphics g){
    g.setColor(new Color(240,200,200));// user menu frame
    g.fillRect(341, 10, 200, 340);
    
    g.setColor(Color.black);
    g.drawString("Reset",399,260); // reset message
    g.drawString("Check",449,260); // check message

    g.fillRect(390, 200, 50, 50);// reset botton ->390 200   30 300
    g.setColor(Color.green);
    g.drawArc(395, 205 ,40, 40, 0, 360);//+5 +5
    g.fillPolygon(xTri,yTri, 3);// <-

    g.setColor(Color.black);// Check botton ->
    g.drawRect(442, 200, 50, 50);//    442  200     30 230
    g.setColor(Color.red);
    g.drawArc(442,200 ,49, 49, 0, 360);
    g.setColor(Color.blue);
    g.drawLine(449, 207,483 , 242);    // +7 +7 -8 -8
    g.drawLine(483, 207, 449, 242);// -8 +7  +7 -8<-


    g.setColor(Color.black); // puzzlepanel frame
    g.drawRect(390, 50, 100, 100);    

    Font fo = new Font("DailogInput",Font.BOLD,16);
    g.setFont(fo);
    g.drawString("STAGE  ",404,40);


    // B->R->Y  from 374 to 409
    
    g.drawString(">>    >>",391+dot,175);
    g.setColor(Color.blue);
    g.fillRect(390,160 ,20, 20);
    g.setColor(Color.red);
    g.fillRect(430, 160, 20, 20);
    g.setColor(Color.yellow);
    g.fillRect(470, 160, 20, 20);
    }
}

class UserInterface extends JPanel{
    QuizPanels panels = new QuizPanels();
    double[][][] puzzle=panels.puzzles;
    int MX=puzzle[0][0].length,
    MY=puzzle[0].length;
    UserInterface(){
    }

    @Override
    public void paint(Graphics g){
        for(int i =1; i<MY-1;i++){
            for(int k =1;k<MX-1;k++){
                switch((int)(puzzle[AnsMessage.stage-1][i][k])){
                    case 0:
                        g.setColor(Color.blue);
                        g.fillRect(390+20*(k-1),50+20*(i-1), 20, 20);
                        break;
                    case 1:
                        g.setColor(Color.red);
                        g.fillRect(390+20*(k-1),50+20*(i-1), 20, 20);
                        break;
                    case 2:
                        g.setColor(Color.yellow);
                        g.fillRect(390+20*(k-1),50+20*(i-1), 20, 20);
                        break;
                }
            }
        }
    }
}


class AnsMessage extends JPanel {
    QuizPanels panels = new QuizPanels();

    double[][][] puzzle=panels.puzzles;
    static int ans=0,stage=1;
    int dot=0,
    MY=puzzle[0].length,
    MX=puzzle[0][0].length; 

    AnsMessage(){
        //System.out.println("All of stage is "+puzzle.length);
        
    }

public boolean AnsCheck(int stage){
    //System.out.println("checking puzzele index is "+(stage-1));
    //System.out.println("before ans is "+ans);
    for(int i =1; i<MY-1;i++){
        for(int k =1;k<MX-1;k++){
            if(puzzle[stage-1][i][k] != Datas.map[i][k]){
                return false;
            }
        }
    }
    return true;   
}

public void AnsAdd(boolean tf){
    if(tf && ans<puzzle.length && (stage - ans)==1){
        ans+=1;
    }
    //System.out.println(" ans is "+ans);
    
}

public void DotAni(){
    dot+=1;
}

public void NextStage(){
        dot=0;
        stage+=1;
       // System.out.println("stage is "+stage);   
}

    @Override
public void paint(Graphics g){
    if(ans==stage){
        g.setColor(new Color(0,0,0));
        g.fillRect(100,40,200,50); 
        g.setColor(new Color(255,0,255));//pink
        g.drawRect(100,40,40,50);
        g.drawRect(140,40,40,50);
        g.drawRect(180,40,40,50);
        g.drawRect(220,40,40,50);
        g.drawRect(260,40,40,50);

        //C
        g.fillRect(100,40,dot,dot);
        g.fillRect(110,50,3*dot,3*dot);
        g.fillRect(100,80,dot,dot);
        //x 140 y 40 L
        g.fillRect(150,40,3*dot,4*dot);
        //x 180 E
        g.fillRect(190,50,3*dot,dot);
        g.fillRect(190,70,3*dot,dot);
        // x 220 A
        g.fillRect(220,40,dot,dot);
        g.fillRect(250,40,dot,dot);
        g.fillRect(230,50,2*dot,dot);
        g.fillRect(230,70,2*dot,2*dot);
        // x 260 R
        g.fillRect(290,40,dot,dot);
        g.fillRect(270,50,2*dot,dot);
        g.fillRect(270,70,dot,2*dot);
        g.fillRect(280,80,dot,dot);
        g.fillRect(290,60,dot,dot);
        
        // answer botton
        g.fillRect(341, 300, 50, 50);
        g.setColor(new Color(0,0,0));
        
        g.drawString("Next Game" ,341, 362);
    }else{        

        //STAGE number
        g.setColor(Color.black);
        Font fo = new Font("DailogInput",Font.BOLD,16);
        g.setFont(fo);
        g.drawString(String.valueOf(stage),470,40);

    }
  }
}

class DrawAll extends JPanel implements MouseMotionListener,MouseListener,Runnable{
    int cp=1;  //change pattern
    Datas data = new Datas();
    AnsMessage aM = new AnsMessage();
    DontRepeatOb dOb = new DontRepeatOb();
    UserInterface UI = new UserInterface();
    ChangingPanel CP = new ChangingPanel(); 
    QuizPanels QP = new QuizPanels();
    double[][][] puzzle = QP.puzzles;
    Thread th1;

    DrawAll(){
        addMouseListener(this); 
        addMouseMotionListener(this); 
        th1 = new Thread(this);
        th1.start();
    }
    @Override
    public void mouseMoved(MouseEvent e) { 
        double xp=e.getPoint().getX();
        double yp=e.getPoint().getY();
        if(100.<xp && xp<300. && 100.<yp && yp < 300.){
            CP.Setp(xp,yp,cp);
            repaint();
        }
    }


  @Override
   public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) { }
    
        @Override
    public void mouseEntered(MouseEvent e) { }
    
        @Override
    public void mouseExited(MouseEvent e) { }
    
        @Override
    public void mousePressed(MouseEvent e) {
        double xp=e.getPoint().getX();
        double yp=e.getPoint().getY();
        // panel color change

        if(100.<xp && xp<300. && 100.<yp && yp < 300.){
            cp=(cp+1)%2;
            CP.Setp(xp,yp,cp);
            data.ColorChange(xp, yp,cp);
            //System.out.println(xp+" "+yp+" clicked!");
            //data.MakeCode();
            repaint();
        }
        // Reset 
    
        //    390 200  length50
        if(390.<xp && xp<440. && 200.<yp && yp<250.){

            data.Reset();
            //System.out.println("Return to Begining!");
            repaint();
        }
        // Check Answer
        //    442  200
        if(442.<xp && xp<492. && 200.<yp && yp<250.){
            //System.out.println("Check your Answer!");
            aM.AnsAdd(aM.AnsCheck(AnsMessage.stage));
            repaint();            
        }
        // go to nextstage
        if(341.<xp && xp<391. && 300.<yp && yp<350.&& AnsMessage.stage<puzzle.length){
            if(AnsMessage.ans==AnsMessage.stage){
                    //data.Reset();
                    //System.out.println("Go to NextStage!");
                    aM.NextStage();                   
                    repaint();
            }
        }
    }
    
        @Override
    public void mouseReleased(MouseEvent e) {}
    
    public void paintComponent(Graphics g){
        dOb.paint(g);
        data.paint(g);
        UI.paint(g);
        CP.paint(g);
        aM.paint(g);
    }
    public void runSequence(){
        synchronized(this){
            if(AnsMessage.ans==AnsMessage.stage && aM.dot<10){
                aM.DotAni();
                repaint();
                sleep(80);
                //System.out.println("RunRun");
            }
            if(dOb.dot<42){
                dOb.PlusDot();
                repaint();
                sleep(100);
            }else{
                dOb.ToZeroDot();
            }

        }
    }
    @Override
    public void run(){
        while(true){
            runSequence(); 
        }
    }

    public void sleep(int time){
        try { Thread.sleep(time); }
        catch (InterruptedException e) {}
      }
}

class QuizPanels {
    double[][][] puzzles= {
    {
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // s1
        {-1., 0.,0.,0.,0.,0., -1.},
        {-1., 1.,2.,2.,2.,1., -1.},
        {-1., 2.,0.,2.,0.,2., -1.},
        {-1., 0.,1.,1.,1.,0., -1.},  
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}
    },{
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // s2
        {-1., 0.,0.,0.,0.,0., -1.},
        {-1., 1.,2.,1.,2.,1., -1.},
        {-1., 2.,2.,2.,2.,2., -1.},
        {-1., 0.,1.,0.,1.,0., -1.},
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}
    },{
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // s3
        {-1., 0.,0.,0.,0.,0., -1.},
        {-1., 1.,2.,1.,2.,1., -1.},
        {-1., 2.,2.,2.,2.,2., -1.},
        {-1., 0.,1.,1.,1.,0., -1.},
        {-1., 1.,2.,1.,2.,1., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}
    },{
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // s4
        {-1., 0.,1.,0.,1.,0., -1.},
        {-1., 1.,2.,1.,2.,1., -1.},
        {-1., 2.,1.,0.,1.,2., -1.},
        {-1., 0.,2.,1.,2.,0., -1.},
        {-1., 1.,0.,2.,0.,1., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}
    },{
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // s5
        {-1., 1.,0.,1.,0.,1., -1.},
        {-1., 1.,0.,1.,0.,1., -1.},
        {-1., 0.,2.,0.,2.,0., -1.},
        {-1., 1.,0.,1.,0.,1., -1.},
        {-1., 1.,0.,1.,0.,1., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}
    },{
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // s6
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1., 1.,0.,1.,0.,1., -1.},
        {-1., 0.,1.,0.,1.,0., -1.},
        {-1., 1.,0.,1.,0.,1., -1.},
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}
    },{
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // s7
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1., 1.,2.,2.,2.,1., -1.},
        {-1., 0.,2.,0.,2.,0., -1.},
        {-1., 1.,2.,2.,2.,1., -1.},
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}
    },{
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // s8
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1., 1.,0.,2.,0.,1., -1.},
        {-1., 0.,2.,0.,2.,0., -1.},
        {-1., 1.,0.,2.,0.,1., -1.},
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.},
    },{
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // s9
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1., 2.,0.,2.,0.,2., -1.},
        {-1., 0.,0.,0.,0.,0., -1.},
        {-1., 2.,0.,2.,0.,2., -1.},
        {-1., 1.,1.,1.,1.,1., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.},
    },{
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.}, // s10
        {-1., 2.,2.,2.,2.,2., -1.},
        {-1., 2.,2.,2.,2.,2., -1.},
        {-1., 2.,2.,2.,2.,2., -1.},
        {-1., 2.,2.,2.,2.,2., -1.},
        {-1., 2.,2.,2.,2.,2., -1.},
        {-1.,-1.,-1.,-1.,-1.,-1.,-1.},
    }

    };
  }
