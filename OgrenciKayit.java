import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import java.io.BufferedReader;
import java.io.FileReader;

public class OgrenciKayit extends JFrame {




    public void setDers(DersKayit.Ders ders ) {
        // Use the Ders instance in OgrenciKayit as needed


        System.out.println("Received Ders instance in OgrenciKayit: " + ders);
        // Additional processing...
    }
    public class Ogrenci {
        private String ogrenciNo;
        private String ogrenciAd;
        private String ogrenciSoyad;
        private String ogrenciBolum;
        private List<String> ogrenciDersler; // Öğrencinin aldığı derslerin listesi

        public Ogrenci(String ogrenciNo, String ogrenciAd, String ogrenciSoyad, String ogrenciBolum) {
            this.ogrenciNo = ogrenciNo;
            this.ogrenciAd = ogrenciAd;
            this.ogrenciSoyad = ogrenciSoyad;
            this.ogrenciBolum = ogrenciBolum;
            this.ogrenciDersler = new ArrayList<>();
        }

        // Getter ve setter metotları
        public String getOgrenciNo() {
            return ogrenciNo;
        }

        public void setOgrenciNo(String ogrenciNo) {
            this.ogrenciNo = ogrenciNo;
        }

        public String getOgrenciAd() {
            return ogrenciAd;
        }

        public void setOgrenciAd(String ogrenciAd) {
            this.ogrenciAd = ogrenciAd;
        }

        public String getOgrenciSoyad() {
            return ogrenciSoyad;
        }

        public void setOgrenciSoyad(String ogrenciSoyad) {
            this.ogrenciSoyad = ogrenciSoyad;
        }

        public String getOgrenciBolum() {
            return ogrenciBolum;
        }

        public void setOgrenciBolum(String ogrenciBolum) {
            this.ogrenciBolum = ogrenciBolum;
        }

        public List<String> getOgrenciDersler() {
            return ogrenciDersler;
        }

        public void setOgrenciDersler(List<String> ogrenciDersler) {
            this.ogrenciDersler = ogrenciDersler;
        }

        // Ders ekleme metodu
        public void dersEkle(String ders) {
            this.ogrenciDersler.add(ders);
        }


        @Override
        public String toString() {
            return "Ogrenci{" +
                    "ogrenciNo='" + ogrenciNo + '\'' +
                    ", ogrenciAd='" + ogrenciAd + '\'' +
                    ", ogrenciSoyad='" + ogrenciSoyad + '\'' +
                    ", ogrenciBolum='" + ogrenciBolum + '\'' +
                    ", ogrenciDersler=" + ogrenciDersler +
                    '}';
        }
    }


    private JButton dersButton;
    private JPanel panel1;
    private JButton genelformButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;


    private JButton kaydetButton;
    private JComboBox comboBox1;
    private JTextField textField5;
    private JList list1;
    private JButton kayıtAraButton;
    private JButton ogretimGorevliButton;

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




    public OgrenciKayit() {
        setTitle("Genel Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200,200);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(panel1);


        dersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DersKayit dersKayit=new DersKayit();
                dersKayit.setVisible(true);
                setVisible(false);




            }
        });

        genelformButton.addActionListener(new ActionListener() {
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

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Defter.txt"))) {
                    writer.write(textField1.getText()+textField2.getText()+textField3.getText()+textField4.getText()+"\n");
                    System.out.println("Not defterine yazma işlemi başarılı!");
                } catch (IOException b) {
                    System.err.println("Hata: " + b.getMessage());
                }

            }
        });
        panel1.addComponentListener(new ComponentAdapter() {
        });
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ograd=textField1.getText();
                String Ogrsoyad=textField2.getText();
                String ogrbolum=textField3.getText();
                String ogrNo=textField4.getText();
                Ogrenci ogrenci=new Ogrenci(ogrNo,ograd,Ogrsoyad,ogrbolum);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("OgrKayit.txt", true))) {
                    String content = ogrenci.toString();
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
        kayıtAraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = textField5.getText();
                DefaultListModel<String> listModel = (DefaultListModel<String>) resultList.getModel();

                // Read content from the file and search for the text
                try (BufferedReader reader = new BufferedReader(new FileReader("OgrKayit.txt"))) {
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
            }
        });
        ogretimGorevliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        ogretimGorevliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgretimGorevlisi ogretimGorevlisi=new OgretimGorevlisi();
            }
        });
    }

    public static void main(String[] args) {

        OgrenciKayit ogrenciform = new OgrenciKayit();
        ogrenciform.setVisible(true);
	setVisible=(false);



    }
}
