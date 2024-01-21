import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GnlForm extends JFrame {

    private JPanel panel1;
    private JButton dersButton;
    private JButton ogrenciButton;
    private JButton ogretimGorevlisiButton;

    public GnlForm() {
        setTitle("Genel Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,200);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(panel1);

        ogrenciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgrenciKayit ogrenciKayitForm = new OgrenciKayit();
                ogrenciKayitForm.setVisible(true);
                setVisible(false);

            }

        });


        dersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DersKayit dersKayit= new DersKayit();
                dersKayit.setVisible(true);
                setVisible(false);

            }
        });
        ogretimGorevlisiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgretimGorevlisi ogretimGorevlisi=new OgretimGorevlisi();
                ogretimGorevlisi.setVisible(true);
                setVisible(false);
            }
        });
    }
    public static void main(String[] args) {

        GnlForm gnlForm = new GnlForm();
        gnlForm.setVisible(true);
    }




    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
