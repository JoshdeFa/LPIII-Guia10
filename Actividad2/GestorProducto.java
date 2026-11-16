package Ejercicio1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorProducto extends JFrame {

    private Producto productoModelo;

    public GestorProducto() {
        super("Gestión de Producto - Ejercicio 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout(10, 10));

        // 1. Inicializar el modelo con valores por defecto
        productoModelo = new Producto("", 0.0, 0, "");

        // 2. Crear panel para el formulario (GridLayout para alinear etiquetas y cajas)
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(4, 2, 10, 10));

        // Campos de texto para la vista
        JTextField txtNombre = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtStock = new JTextField();
        JTextField txtCategoria = new JTextField();

        panelFormulario.add(new JLabel(" Nombre del producto:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel(" Precio:"));
        panelFormulario.add(txtPrecio);
        panelFormulario.add(new JLabel(" Cantidad en Stock:"));
        panelFormulario.add(txtStock);
        panelFormulario.add(new JLabel(" Categoría:"));
        panelFormulario.add(txtCategoria);

        // 3. Crear panel inferior para el botón y el resultado
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BorderLayout(10, 10));
        
        JButton btnActualizar = new JButton("Actualizar Producto");
        JLabel lblResultado = new JLabel("Ingrese los datos y presione Actualizar.", SwingConstants.CENTER);
        lblResultado.setForeground(Color.BLUE);

        panelInferior.add(btnActualizar, BorderLayout.NORTH);
        panelInferior.add(lblResultado, BorderLayout.CENTER);

        // 4. Lógica de Binding (Controlador)
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    productoModelo.setNombre(txtNombre.getText());
                    productoModelo.setPrecio(Double.parseDouble(txtPrecio.getText()));
                    productoModelo.setCantidadStock(Integer.parseInt(txtStock.getText()));
                    productoModelo.setCategoria(txtCategoria.getText());

                    String info = String.format("<html><b>Producto actualizado:</b> %s | <b>Cat:</b> %s | <b>Stock:</b> %d | <b>Precio:</b> $%.2f </html>", 
                                                productoModelo.getNombre(), 
                                                productoModelo.getCategoria(), 
                                                productoModelo.getCantidadStock(), 
                                                productoModelo.getPrecio());
                    lblResultado.setText(info);
                    lblResultado.setForeground(new Color(0, 150, 0)); // Verde de éxito

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Precio y Stock deben ser números válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar los paneles a la ventana
        add(panelFormulario, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new GestorProducto().setVisible(true);
    }
}

// CLASE MODELO (entidad del negocio)
class Producto {
    private String nombre;
    private double precio;
    private int cantidadStock;
    private String categoria;

    public Producto(String nombre, double precio, int cantidadStock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.categoria = categoria;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}