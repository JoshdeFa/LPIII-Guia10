package Ejercicio2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroTemperaturas extends JFrame {

    private JTextField[] camposTemperatura;
    private String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    private PanelGraficoLineas panelGrafico;

    public RegistroTemperaturas() {
        super("Registro de Temperaturas - Ejercicio 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 450);
        setLayout(new BorderLayout(10, 10));

        // 1. Panel lateral para ingreso de datos (GridLayout)
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new GridLayout(8, 2, 5, 10));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelEntrada.setBackground(new Color(240, 240, 240));

        camposTemperatura = new JTextField[7];
        
        for (int i = 0; i < 7; i++) {
            panelEntrada.add(new JLabel(diasSemana[i] + ":"));
            camposTemperatura[i] = new JTextField("0");
            panelEntrada.add(camposTemperatura[i]);
        }

        JButton btnMostrarGrafico = new JButton("Mostrar Gráfico");
        panelEntrada.add(new JLabel("")); // Celda vacía para alinear el botón
        panelEntrada.add(btnMostrarGrafico);

        // 2. Panel central donde se dibujará el gráfico
        panelGrafico = new PanelGraficoLineas();
        panelGrafico.setBackground(Color.WHITE);

        // 3. Acción del botón (Binding hacia el gráfico)
        btnMostrarGrafico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] temperaturasIngresadas = new int[7];
                try {
                    // Leer las 7 cajas de texto
                    for (int i = 0; i < 7; i++) {
                        temperaturasIngresadas[i] = Integer.parseInt(camposTemperatura[i].getText());
                    }
                    // Enviar los datos al panel gráfico y forzar el redibujado
                    panelGrafico.setTemperaturas(temperaturasIngresadas);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RegistroTemperaturas.this, 
                        "Error: Por favor, ingrese solo números enteros para las temperaturas.", 
                        "Entrada inválida", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 4. Ensamblar la ventana
        add(panelEntrada, BorderLayout.WEST);
        add(panelGrafico, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new RegistroTemperaturas().setVisible(true);
    }
}

// CLASE PARA EL PANEL DE DIBUJO PERSONALIZADO
class PanelGraficoLineas extends JPanel {
    private int[] temperaturas = new int[7]; // Almacena los datos a graficar
    private String[] diasCortos = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};

    // Método para recibir nuevos datos y actualizar visualmente
    public void setTemperaturas(int[] nuevasTemperaturas) {
        this.temperaturas = nuevasTemperaturas;
        repaint(); // Llama a paintComponent automáticamente
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        // Activar antialiasing para suavizar las líneas y los círculos
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();
        int margen = 40;

        g2d.setColor(Color.BLACK);
        g2d.drawLine(margen, margen, margen, alto - margen); // Eje Y
        g2d.drawLine(margen, alto - margen, ancho - margen, alto - margen); // Eje X
        g2d.drawString("Temp (°C)", 10, margen - 10);

        // Encontrar la temperatura máxima para escalar el gráfico dinámicamente
        int maxTemp = 10;
        for (int t : temperaturas) {
            if (t > maxTemp) maxTemp = t;
        }

        int anchoUtil = ancho - (2 * margen);
        int altoUtil = alto - (2 * margen);
        int separacionX = anchoUtil / 6; 

        int[] puntosX = new int[7];
        int[] puntosY = new int[7];

        for (int i = 0; i < 7; i++) {
            puntosX[i] = margen + (i * separacionX);
            // La coordenada Y se invierte (getHeight() - ...) porque el punto (0,0) está arriba a la izquierda
            puntosY[i] = (alto - margen) - (int) (((double) temperaturas[i] / maxTemp) * altoUtil);

            g2d.setColor(Color.BLACK);
            g2d.drawString(diasCortos[i], puntosX[i] - 10, alto - margen + 20);
        }

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2)); // Línea más gruesa
        for (int i = 0; i < 6; i++) {
            g2d.drawLine(puntosX[i], puntosY[i], puntosX[i + 1], puntosY[i + 1]);
        }

        for (int i = 0; i < 7; i++) {
            g2d.setColor(Color.RED);
            g2d.fillOval(puntosX[i] - 4, puntosY[i] - 4, 8, 8); // Punto

            g2d.setColor(Color.DARK_GRAY);
            g2d.drawString(String.valueOf(temperaturas[i]), puntosX[i] - 5, puntosY[i] - 10); 
        }
    }
}