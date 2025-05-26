package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaMenuSTY;
import modelo.*;

/**
 *
 * @author UNIVALLE
 */
public class ControlMenuSTYGui implements ActionListener {

    private VistaMenuSTY vistaMenSTY;

    public ControlMenuSTYGui() {
        this.vistaMenSTY = new VistaMenuSTY();
        this.vistaMenSTY.setVisible(true);

        // Botones
        this.vistaMenSTY.jbtn_vehiculo.addActionListener(this);
        this.vistaMenSTY.jbtn_propietario.addActionListener(this);
        this.vistaMenSTY.jbtn_tarjetaPropiedad.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaMenSTY.jbtn_vehiculo) {
            new ControlVehiculoGui(); // Ahora sin lista
        }

        if (e.getSource() == this.vistaMenSTY.jbtn_tarjetaPropiedad) {
           // new ControlTarjetaPropiedadGui();
        }

        if (e.getSource() == this.vistaMenSTY.jbtn_propietario) {
           // new ControlPropietarioGui();
        }
    }
}
