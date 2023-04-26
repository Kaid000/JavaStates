package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class Main extends JFrame {
    private static final int WIDTH = 330;
    private static final int HEIGHT = 420;
    private JTextField InputText;
    private JButton Check;
    private JButton Clear;
    private String Capital;
    private JLabel info;
    private JLabel GameOver;
    private int Ran;
    private JLabel abc;
    private Image img;

    private  JLabel States_out;
    private  String[] States = {"������", "�����", "�������", "������", "�������", "��������",
            "��������", "���������", "�������", "���������", "���������", "������", "�������", "��������", "�������� ��������", "��������","�������",
            "����������", "������", "��������", "��������", "�����������","��������","�����������", "���������", "���������",
            "�������", "�������", "�������", "���", "��������", "��������", "������", "���-�������", "���-������", "���-����",
            "���-�������", "�����", "��������", "������", "������������", "���-������", "�������� ������", "�������� ��������",
            "��������", "�����", "�������", "����� ������", "����� ��������", "���"};
    private ArrayList<String> Array_States;
    private ArrayList<String> Capitallist;
    private BufferedImage ImageOfState;
    private JLabel show;
    private String[] Capis = {"�����", "��-����", "����������", "�����", "������", "����-���", "������", "�������",
            "����������","�������", "�������", "��������", "�����", "�������", "��������", "����������", "������������",
            "����������", "������", "���������", "������", "��������", "�����-���", "������", "����-���", "�������", "����������-����",
            "�������", "������", "������", "���������", "��������", "������-����", "�������", "�������", "������", "�����-��",
            "��������", "��������-����", "������", "����������", "���������", "�������", "����", "�������", "�����", "����������",
            "����", "��������", "����-����-����"};




    public Main() throws IOException {
        super("�����");
        img = getToolkit().getImage("src/Icon.png");
        setIconImage(img);
        show = new JLabel();

        Array_States = new ArrayList<String>();
        for (int i =0; i < States.length; i++)
        {
            Array_States.add(States[i]);
        }
        Capitallist = new ArrayList<String>();
        for (int i =0; i < Capis.length;i++)
        {
            Capitallist.add(Capis[i]);
        }
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);
        final Box Hboxone = Box.createHorizontalBox();


        Ran = (int) (Math.random() * Array_States.size());
        Capital = Capitallist.get(Ran);
        ImageOfState = ImageIO.read(new File( "src/" +Array_States.get(Ran)+".png"));

        abc = new JLabel(new ImageIcon(ImageOfState));
        GameOver = new JLabel("");

        info = new JLabel("0");
        Check = new JButton("���������");
        Clear = new JButton("�������� ����");
        States_out = new JLabel(Array_States.get(Ran));

        Array_States.remove(Ran);
        Capitallist.remove(Ran);
        final Box Dynmap = Box.createHorizontalBox();
        final JTextField InputText = new JTextField("", 10);
        InputText.setMaximumSize(InputText.getPreferredSize());
        States_out = new JLabel(States[Ran]);
        Dynmap.add(abc);
        Hboxone.add(GameOver);
        Hboxone.add(States_out);

        Hboxone.add(Box.createHorizontalStrut(10));

        Hboxone.add(InputText);
        Box am = Box.createVerticalBox();

        final Box ButtBox = Box.createHorizontalBox();
        ButtBox.add(Check);
        ButtBox.add(Clear);
        Check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {

                    String text = InputText.getText();
                    System.out.println(text + "  " + Capital); //text.equals(sam));

                    InputText.setText("");

                    if (text.equals(Capital)) {



                        info = new JLabel("�����");



                        if (Array_States.size() ==0)
                        {
                            GameOver.setText("���� ���������!");
                            States_out.setText("");}
                        Ran = (int) (Math.random() * Array_States.size());
                        Dynmap.remove(abc);
                        ImageOfState = ImageIO.read(new File("src/" +Array_States.get(Ran)+".png"));
                        abc = new JLabel(new ImageIcon(ImageOfState));
                        Dynmap.add(abc);

                       Capital = Capitallist.get(Ran);
                        info = new JLabel("0");
                        Check = new JButton("���������");
                        Clear = new JButton("�������� ����");
                        States_out.setText(Array_States.get(Ran));


                    } else {

                        ButtBox.remove(info);
                        info = new JLabel("�������");
                        ButtBox.add(info);

                        if (Array_States.size() ==0)
                        {
                            GameOver.setText("���� ���������!");
                            States_out.setText("");}

                        Ran = (int) (Math.random() * Array_States.size());


                        Capital = Capitallist.get(Ran);
                        Dynmap.remove(abc);
                        ImageOfState = ImageIO.read(new File("src/" +Array_States.get(Ran)+".png"));
                        abc = new JLabel(new ImageIcon(ImageOfState));
                        Dynmap.add(abc);



                        States_out.setText(Array_States.get(Ran));


                    }
                    Array_States.remove(Ran);
                    Capitallist.remove(Ran);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this, "������ � ������� ������ ����� � ��������� ������", "��������� ������ �����",
                            JOptionPane.WARNING_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputText.setText("");
            }
        });
        ButtBox.add(info);
        am.add(Dynmap);
        am.add(Hboxone);
        am.add(ButtBox);
        getContentPane().add(am, BorderLayout.CENTER);
        InputText.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = InputText.getText();
                    System.out.println(text + "  " + Capital); //text.equals(sam));
                    InputText.setText("");

                    if (text.equals(Capital)) {

                       info.setText("�����");

                        if (Array_States.size() == 0) {
                            GameOver.setText("���� ���������!");
                            States_out.setText("");
                        }
                        Ran = (int) (Math.random() * Array_States.size());
                        Dynmap.remove(abc);
                        try {
                            ImageOfState = ImageIO.read(new File("src/" +Array_States.get(Ran) + ".png"));
                        } catch (IOException R) {
                            R.printStackTrace();
                        }
                        abc = new JLabel(new ImageIcon(ImageOfState));
                        Dynmap.add(abc);

                        Capital = Capitallist.get(Ran);

                        States_out.setText(Array_States.get(Ran));
                    } else {
                        info.setText("�������");
                        ButtBox.remove(info);

                        ButtBox.add(info);
                        if (Array_States.size() == 0) {
                            GameOver.setText("���� ���������!");
                            States_out.setText("");
                        }


                        Ran = (int) (Math.random() * Array_States.size());


                        Capital = Capitallist.get(Ran);
                        Dynmap.remove(abc);
                        try {
                            ImageOfState = ImageIO.read(new File("src/" +Array_States.get(Ran) + ".png"));
                        } catch (IOException O) {
                            O.printStackTrace();
                        }
                        abc = new JLabel(new ImageIcon(ImageOfState ));
                        Dynmap.add(abc);



                        States_out.setText(Array_States.get(Ran));


                    }
                    Array_States.remove(Ran);
                    Capitallist.remove(Ran);

                }
            }
        });
    }

    public static void main(String[] args) throws IOException {

        Main A;

         A = new Main();
         A.setDefaultCloseOperation(A.EXIT_ON_CLOSE);
         A.setVisible(true);


    }
}

