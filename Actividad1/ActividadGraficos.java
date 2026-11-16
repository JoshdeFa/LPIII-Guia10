package Actividad1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class ActividadGraficos extends JPanel {
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Convertir a Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        
        // Activar Antialiasing para bordes suaves
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Guardar estado original
        AffineTransform originalTransform = g2d.getTransform();

        // 1. Gráfico Simple (Texto y Ovalo base)
        g2d.setColor(Color.BLACK);
        g2d.drawString("Gráficos Avanzados en Java Swing", 20, 20);
        g2d.setColor(Color.RED);
        g2d.fillOval(50, 50, 100, 100);

        // 2. Gráfico Avanzado (Traslación y Rotación)
        g2d.translate(200, 50); // Mover el eje de coordenadas
        g2d.rotate(Math.toRadians(45), 50, 50); // Rotar 45 grados sobre el centro del nuevo círculo
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, 100, 100); // Cuadrado rotado

        g2d.setTransform(originalTransform);
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Transformaciones 2D");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 250);
        ventana.add(new ActividadGraficos());
        ventana.setVisible(true);
    }
}