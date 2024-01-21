import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class OgretimGorevlisi extends JFrame{


    private String Ogretmenno;
    private String Ogretmenadsoyad;
    private List Ogretmenaldigiders;
    private String Ogretmenbolum;
    private JPanel panel1;
    private JTextField textField1;
    private JButton anaformButton;
    private JButton dersKayıtButton;
    private JTextField textField2;
    private JTextField textField4;
    private JButton kayitYapButton;
    private JButton kayitAraButton;
    private JTextField textField5;
    private JButton ogrenciKayitButton;
    private JComboBox comboBox1;
    private JList list1;

    private void setComboBoxItemsFromTxt(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                comboBox1.addItem(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Hata: Veriler okunamadı", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    public OgretimGorevlisi() {
        this("DefaultNo", "DefaultName", new ArrayList<>(), "DefaultBolum");
        setTitle("Genel Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200,200);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(panel1);
        setComboBoxItemsFromTxt("OgretmenKayit.txt");
        ogrenciKayitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("OgretmenKayit.txt"))) {
                    writer.write(textField1.getText()+textField2.getText()+textField4.getText()+textField5.getText()+"\n");
                    System.out.println("Not defterine yazma işlemi başarılı!");
                } catch (IOException b) {
                    System.err.println("Hata: " + b.getMessage());
                }

            }
        });

        dersKayıtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DersKayit dersKayit=new DersKayit();
                dersKayit.setVisible(true);
                setVisible(false);
            }
        });
        ogrenciKayitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgrenciKayit ogrenciKayit=new OgrenciKayit();
                ogrenciKayit.setVisible(true);
                setVisible(false);
            }
        });
        anaformButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GnlForm gnlForm = new GnlForm();
                gnlForm.setVisible(true);
                setVisible(false);
            }
        });
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> resultList = new JList<>(listModel);
        kayitAraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = textField5.getText();
                DefaultListModel<String> listModel = (DefaultListModel<String>) resultList.getModel();

                // Read content from the file and search for the text
                try (BufferedReader reader = new BufferedReader(new FileReader("OgretmenKayit.txt"))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        char[] charList = line.toCharArray();
                        StringBuilder word = new StringBuilder();

                        for (int i = 0; i < charList.length; i++) {
                            if (charList[i] == '\'') {
                                i++; // Move to the next character after the opening single quote
                                while (i < charList.length && charList[i] != '\'') {
                                    word.append(charList[i]);
                                    i++;
                                }
                                // Assuming you want to add the word to the list if it matches searchText
                                if (word.toString().equals(searchText)) {
                                    listModel.addElement(line);
                                    resultList.setModel(listModel);
                                    list1.setModel(listModel);
                                    return; // Exit the loop if the value is found
                                }
                                word.setLength(0); // Clear the StringBuilder for the next word
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Display a message if the value is not found
                JOptionPane.showMessageDialog(null, "Value not found in OgretmenKayit.txt");
            }
        });
    }




    public OgretimGorevlisi(String ogrno, String ogrAdSoyad,List ogrDersler,String OgrBolum) {
         this.Ogretmenno=ogrno;
         this.Ogretmenadsoyad=ogrAdSoyad;
         this.Ogretmenaldigiders=ogrDersler;
         this.Ogretmenbolum=OgrBolum;
         setComboBoxItemsFromTxt("OgretmenKayit.txt");
        setTitle("Genel Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200,200);
        setLocationRelativeTo(null);

        setContentPane(panel1);
        ogrenciKayitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });





        List<String> item = new ArrayList<>();

        kayitYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Ogretmen_No = textField1.getText();
                String Ogretmen_AdSoyad = textField2.getText();
                List<String> OgretmenDersler = item; // Make sure to add items to 'item'

                String Ogretmenbolum = textField4.getText();
                OgretimGorevlisi ogretimGorevlisi = new OgretimGorevlisi(Ogretmen_No, Ogretmen_AdSoyad, OgretmenDersler, Ogretmenbolum);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("OgretmenKayit.txt", true))) {
                    // Join the list elements with a delimiter
                    String content = String.join(",", OgretmenDersler);

                    if (content != null && !content.isEmpty()) {
                        writer.write(content);
                        writer.newLine();
                        System.out.println("Ders information is successfully written to the file.");
                    } else {
                        System.out.println("Content is empty. Nothing written to the file.");
                    }
                } catch (IOException ex) {
                    System.err.println("Error writing to the file: " + ex.getMessage());
                    ex.printStackTrace(); // Print the stack trace for debugging
                }
            }
        });





        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selecteditem=comboBox1.getSelectedItem().toString();
                item.add(selecteditem);


            }
        });
    }
}
