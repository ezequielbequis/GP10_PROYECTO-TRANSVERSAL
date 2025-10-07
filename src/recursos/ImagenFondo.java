package img;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class ImagenFondo extends JDesktopPane {
    private Image imagen;

    public ImagenFondo() {
        // Carga la imagen desde la carpeta "img" (dentro de Source Packages)
        imagen = new ImageIcon(getClass().getResource("/img/windowsXP.jpg")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            // Dibuja la imagen escalada al tamaño actual del panel
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}