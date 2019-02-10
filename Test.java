import javax.swing.*;
import java.awt.*;

public class Test extends JFrame
{
	static final long serialVersionUID = 0L;
	public Test()
	{
		super("Test");
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 200);
		this.setVisible(true);
		this.setLocation(0, 0);
	}
	public static void main(String[] args) {
		new Test();
	}
}