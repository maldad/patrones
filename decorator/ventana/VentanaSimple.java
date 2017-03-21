import javax.swing.*;
import java.awt.*;
public class VentanaSimple extends JFrame implements Ventana {
  private String nombre;
  private JFrame v;
  private JPanel p;

  public VentanaSimple(String n){
    nombre = n;
    v = new JFrame(n);
    p = new JPanel(new FlowLayout());
    v.add(p);
    v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    v.setSize(320, 240);
    v.setVisible(true);
  }

  @Override
  public void dibujar(){
    System.out.println("Ventana simple");
    System.out.println("Nombre: " + getNombre());
  }

  public String getNombre(){
    return nombre;
  }

  public void add(Object j){
    p.add((JButton)j);
  }
}
