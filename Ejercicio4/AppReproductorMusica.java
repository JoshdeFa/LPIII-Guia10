package Ejercicio4;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AppReproductorMusica extends JFrame {

    private Clip clip;
    private long posicionActual = 0;

    public AppReproductorMusica() {
        setTitle("Reproductor de Música - Control de Audio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 25));
        
        JButton btnReproducir = new JButton("Reproducir");
        JButton btnPausar = new JButton("Pausar");
        JButton btnReanudar = new JButton("Reanudar");
        
        btnReproducir.addActionListener(e -> reproducirAudio("musica.wav"));
        btnPausar.addActionListener(e -> pausarAudio());
        btnReanudar.addActionListener(e -> reanudarAudio());
        
        add(btnReproducir);
        add(btnPausar);
        add(btnReanudar);
    }

    private void reproducirAudio(String rutaArchivo) {
        try {
            File archivoAudio = new File(rutaArchivo);
            if (!archivoAudio.exists()) {
                JOptionPane.showMessageDialog(this, 
                        "No se encontró el archivo de audio: " + rutaArchivo, 
                        "Archivo no encontrado", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoAudio);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            posicionActual = 0;
            clip.setFramePosition(0);
            clip.start();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(this, 
                    "Error al reproducir el audio: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pausarAudio() {
        if (clip != null && clip.isRunning()) {
            posicionActual = clip.getMicrosecondPosition();
            clip.stop();
        }
    }

    private void reanudarAudio() {
        if (clip != null && !clip.isRunning()) {
            clip.setMicrosecondPosition(posicionActual);
            clip.start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppReproductorMusica().setVisible(true);
        });
    }
}