package Ejercicio3;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AppEfectosSonido extends JFrame {

    public AppEfectosSonido() {
        setTitle("Reproductor de Efectos de Sonido");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        
        setLayout(new GridLayout(3, 1, 10, 10));
        
        JButton btnAplausos = new JButton("Aplausos");
        JButton btnCampana = new JButton("Campana");
        JButton btnExplosion = new JButton("Explosión");
        
        btnAplausos.addActionListener(e -> reproducirSonido("aplausos.wav"));
        btnCampana.addActionListener(e -> reproducirSonido("campana.wav"));
        btnExplosion.addActionListener(e -> reproducirSonido("explosion.wav"));
        
        add(btnAplausos);
        add(btnCampana);
        add(btnExplosion);
    }

    private void reproducirSonido(String rutaArchivo) {
        try {
            File archivoAudio = new File(rutaArchivo);
            if (!archivoAudio.exists()) {
                JOptionPane.showMessageDialog(this, 
                        "No se encontró el archivo de audio: " + rutaArchivo, 
                        "Archivo no encontrado", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoAudio);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            clip.setFramePosition(0);
            clip.start();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(this, 
                    "Error al reproducir el audio: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppEfectosSonido().setVisible(true);
        });
    }
}