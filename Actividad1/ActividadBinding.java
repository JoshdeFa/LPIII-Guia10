package Actividad1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class ActividadBinding extends JFrame {
    public ActividadBinding() {
        super("Binding de Datos - Registro de Libro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        //Modelo de datos inicial
        Libro libro = new Libro("Sin título", "Anónimo");

        //Componentes de la Vista
        JTextField txtTitulo = new JTextField(libro.getTitulo(), 15);
        JTextField txtAutor = new JTextField(libro.getAutor(), 15);
        JButton btnActualizar = new JButton("Sincronizar Modelo");
        JLabel lblResultado = new JLabel("Modelo actual: " + libro.getTitulo());

        // Controlador
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libro.setTitulo(txtTitulo.getText());
                libro.setAutor(txtAutor.getText());
                
                lblResultado.setText("Modelo actualizado: " + libro.getTitulo() + " por " + libro.getAutor());
                System.out.println("LOG -> Libro actualizado: " + libro.getTitulo());
            }
        });

        add(new JLabel("Título:"));
        add(txtTitulo);
        add(new JLabel("Autor:"));
        add(txtAutor);
        add(btnActualizar);
        add(lblResultado);
    }

    public static void main(String[] args) {
        new ActividadBinding().setVisible(true);
    }
}

// Clase Modelo
class Libro {
    private String titulo;
    private String autor;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
}