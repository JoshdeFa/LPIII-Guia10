package Actividad1;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.io.File;

public class ActividadMultimedia extends JFrame {
    
    private Clip clip; // Objeto Clip a nivel de clase para poder pausarlo

    public ActividadMultimedia() {
        super("Reproductor WAV Básico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLayout(new FlowLayout());

        JButton btnReproducir = new JButton("Reproducir");
        JButton btnDetener = new JButton("Detener");

        btnReproducir.addActionListener(e -> reproducirAudio("file_example_WAV_1MG.wav"));
        btnDetener.addActionListener(e -> detenerAudio());

        add(btnReproducir);
        add(btnDetener);
    }

    private void reproducirAudio(String rutaArchivo) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
            
            File archivo = new File(rutaArchivo);
            if(archivo.exists()) {
                AudioInputStream flujoAudio = AudioSystem.getAudioInputStream(archivo);
                clip = AudioSystem.getClip();
                clip.open(flujoAudio);
                clip.start();
                System.out.println("Reproduciendo: " + rutaArchivo);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el archivo: " + rutaArchivo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void detenerAudio() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            System.out.println("Audio detenido.");
        }
    }

    public static void main(String[] args) {
        new ActividadMultimedia().setVisible(true);
    }
}