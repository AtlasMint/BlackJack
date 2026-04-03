import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class StyledButtonUI extends BasicButtonUI {

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Dialog", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = c.getWidth();
        int h = c.getHeight();

        if (button.getModel().isPressed()) {
            g2.setColor(new Color(0x1a1a2e));
        } else if (button.getModel().isRollover()) {
            g2.setColor(new Color(0x4a4a6a));
        } else {
            g2.setColor(new Color(0x2e2e4e));
        }

        g2.fillRoundRect(0, 0, w, h, 15, 15);
        g2.setColor(new Color(0xaaaacc));
        g2.drawRoundRect(0, 0, w - 1, h - 1, 15, 15);

        g2.dispose();
        super.paint(g, c);
    }
}
