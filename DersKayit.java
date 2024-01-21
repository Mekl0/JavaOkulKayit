import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.*;

public class DersKayit extends JFrame {


    public class Ders extends JFrame {
        String dersKodu;
        String dersAdi;
        String dersDonemi;
        String ogretmen;


        public Ders(String dersKodu, String dersAdi, String dersDonemi, String ogretmen) {
            this.dersKodu = dersKodu;
            this.dersAdi = dersAdi;
            this.dersDonemi = dersDonemi;
            this.ogretmen = ogretmen;
        }

        // Getter ve setter metotları
        public String getDersKodu() {
            return dersKodu;
        }

        public void setDersKodu(String dersKodu) {
            this.dersKodu = dersKodu;
        }

        public String getDersAdi() {
            return dersAdi;
        }

        public void setDersAdi(String dersAdi) {
            this.dersAdi = dersAdi;
        }

        public String getDersDonemi() {
            return dersDonemi;
        }

        public void setDersDonemi(String dersDonemi) {
            this.dersDonemi = dersDonemi;
        }

        public String getOgretmen() {
            return ogretmen;
        }

        public void setOgretmen(String ogretmen) {
            this.ogretmen = ogretmen;
        }

        @Override
        public String toString() {
            return "Ders{" +
                    "dersKodu='" + dersKodu + '\'' +
                    ", dersAdi='" + dersAdi + '\'' +
                    ", dersDonemi='" + dersDonemi + '\'' +
                    ", ogretmen='" + ogretmen + '\'' +
                    '}';
        }
    }


    private JPanel panel1;
    private JButton ogrenciButton;
    private JButton anaformButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton kaydetButton;
    private JTextField textField5;
    private JButton AraButton;
    private JList list1;
    private JButton ogretimGorevliButton;


    public DersKayit() {

        setTitle("Genel Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,200);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(panel1);
        ogrenciButton.addActionListener(new ActionListener() {
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
                GnlForm gnlForm=new GnlForm();
                gnlForm.setVisible(true);
                setVisible(false);
            }
        });


        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String dersKodu = textField1.getText();
                String dersAdi = textField2.getText();
                String dersDonemi = textField3.getText();
                String ogretmen = textField4.getText();
                Ders ders=new Ders(dersKodu,dersAdi,dersDonemi,ogretmen);

                System.out.println("Ders instance: " + ders);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Defter.txt", true))) {
                    String content = ders.toString();
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
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> resultList = new JList<>(listModel);

        AraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = textField5.getText();
                DefaultListModel<String> listModel = (DefaultListModel<String>) resultList.getModel();

                // Read content from the file and search for the text
                try (BufferedReader reader = new BufferedReader(new FileReader("Defter.txt"))) {
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
                JOptionPane.showMessageDialog(null, "Value not found in Defter.txt");
            }
        });

        ogretimGorevliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                OgretimGorevlisi ogretimGorevlisi=new OgretimGorevlisi();
                ogretimGorevlisi.setVisible(true);
                setVisible(false);
            }
        });
    }
    public static void main(String[] args) {
        DersKayit dersKayit = new DersKayit();
        dersKayit.setVisible(true);

    }

}
