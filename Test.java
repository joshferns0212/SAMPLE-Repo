public class Test extends JFrame implements KeyListener
{
   public Test()
   {
       super("Test");
       this.setBackground(Color.white);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setSize(/*x size*/, /*y size*/);
       this.setVisible(true);
       this.setLocation(/*x location*/, /*y location*/);
       this.addKeyListener(this);
   }
}