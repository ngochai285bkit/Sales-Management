package View;

import com.formdev.flatlaf.extras.FlatSVGUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class InformationDialog extends JDialog {
    // attributes

    // contructor
    public InformationDialog(Window owner){
        super(owner);
        this.setModal(true);
        initComponents();
        addEvents();
        showDialog(owner);
    }

    private void initComponents() {
    }

    private void addEvents() {
    }

    private void showDialog(Window owner) {
        this.setTitle("Thông tin");
        this.setIconImages(FlatSVGUtils.createWindowIconImages(Objects.requireNonNull(this.getClass().getResource(
                "/Images/icon.svg"))));
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(800, 550);
        this.setLocationRelativeTo(owner);
        this.setResizable(false);
        this.setVisible(true);
    }
}
