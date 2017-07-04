package pl.andyshon.andy_pc;

import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.*;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class PdfCreator {

    public void createPDF(){
        String[] labels = {"Название документа: "};
        int numPairs = labels.length;

        JPanel p = new JPanel(new SpringLayout());
        JLabel labName = new JLabel("Оглавление таблицы:", JLabel.TRAILING);
        final JTextField tfName = new JTextField(15);

        JButton btnSend = new JButton("Сохранить");

        p.add(labName);
        labName.setLabelFor(tfName);
        p.add(tfName);

        SpringUtilities.makeCompactGrid(p, numPairs, 2,6, 6,6, 6);

        final JFrame framePdfSettings = new JFrame("Сохранить как pdf");
        framePdfSettings.setResizable(true);

        p.setOpaque(true);
        framePdfSettings.getContentPane().add(p);

        framePdfSettings.getContentPane().add(btnSend, BorderLayout.SOUTH);

        framePdfSettings.setResizable(false);
        framePdfSettings.pack();
        framePdfSettings.setLocationRelativeTo(null);
        framePdfSettings.setVisible(true);

        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                framePdfSettings.setVisible(false);

                FileNamesCreator fileNamesCreator = new FileNamesCreator();

                final String DEST = "./children_list_" + fileNamesCreator.getFileName() + ".pdf";
                File file = new File(DEST);
                file.getParentFile().mkdirs();

                PdfDocument pdfDoc = null;
                try {
                    pdfDoc = new PdfDocument(new PdfWriter(DEST));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Закройте этот документ!",
                            "Внимание", JOptionPane.INFORMATION_MESSAGE);
                }
                Document doc = new Document(pdfDoc, new PageSize(PageSize.A4));

                float[] columnWidths = {0.5f, 5, 3, 2};
                Table table = new Table(columnWidths);
                table.setWidthPercent(100);

                PdfFont font = null;
                try {
                    FontProgramFactory.registerFont("C:/Windows/Fonts/calibri.ttf", "font");
                    font = PdfFontFactory.createRegisteredFont("font", PdfEncodings.IDENTITY_H, true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Ошибка1",
                            "Внимание", JOptionPane.INFORMATION_MESSAGE);
                }

                Paragraph paragraph = new Paragraph(new String(tfName.getText()));
                paragraph.setBackgroundColor(com.itextpdf.kernel.color.Color.WHITE);
                paragraph.setBorder(new SolidBorder(com.itextpdf.kernel.color.Color.WHITE,1));

                Cell cell = new Cell(1, 4)
                        .add(paragraph)
                        .setBorderTop(new SolidBorder(com.itextpdf.kernel.color.Color.BLACK,0))
                        .setBorderBottom(new SolidBorder(com.itextpdf.kernel.color.Color.BLACK,0))
                        .setBorderLeft(new SolidBorder(com.itextpdf.kernel.color.Color.BLACK,0))
                        .setBorderRight(new SolidBorder(com.itextpdf.kernel.color.Color.BLACK,0))
                        .setFont(font)
                        .setFontSize(13)
                        .setFontColor(DeviceGray.BLACK)
                        .setTextAlignment(TextAlignment.CENTER);
                table.addHeaderCell(cell).setFont(font);

                for (int i = 0; i < 2; i++) {
                    Cell[] headerFooter = new Cell[] {
                            new Cell().setBackgroundColor(new DeviceGray(0.75f)).add("№").setBackgroundColor(com.itextpdf.kernel.color.Color.LIGHT_GRAY),
                            new Cell().setBackgroundColor(new DeviceGray(0.75f)).add("Ф.И.О.").setBackgroundColor(com.itextpdf.kernel.color.Color.LIGHT_GRAY),
                            new Cell().setBackgroundColor(new DeviceGray(0.75f)).add("Возраст").setBackgroundColor(com.itextpdf.kernel.color.Color.LIGHT_GRAY),
                            new Cell().setBackgroundColor(new DeviceGray(0.75f)).add("Дата рождения").setBackgroundColor(com.itextpdf.kernel.color.Color.LIGHT_GRAY)
                    };
                    for (Cell hfCell : headerFooter) {
                        if (i == 0) {
                            table.addHeaderCell(hfCell);
                        } else {
                            table.addFooterCell(hfCell);
                        }
                    }
                }

                ArrayList names = new ArrayList();
                ArrayList deltaDate = new ArrayList();
                ArrayList dates = new ArrayList();

                ReadFromFile readFromFile = new ReadFromFile();

                String someString = readFromFile.getStrFromFile();
                System.out.println("special = " + someString);
                String[] st = someString.split("\\)");
                String stPart="";

                for (int i=0, k=1; i<st.length; i++, k++){
                    System.out.println(".. = " + st[i]);
                    String[] st_mas2 = st[i].split("\\:");
                    stPart = st_mas2[0].substring(0, st_mas2[0].length());
                    names.add(stPart);

                    stPart = st_mas2[1].substring(1, st_mas2[1].length()-11);
                    System.out.println("stPart:" + stPart);
                    String s11 = stPart;
                    String[] s22 = s11.split("\\D+");
                    stPart = s22[0] + "," + s22[1];
                    System.out.println("разница = " + s22[0] + "," + s22[1]);

                    deltaDate.add(stPart);
                    stPart = st_mas2[1].substring(st_mas2[1].length() - 10, st_mas2[1].length());
                    dates.add(stPart);
                }

                for (int counter = 0, k=1; counter < names.size(); counter++, k++) {
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(String.valueOf(k)));
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(names.get(counter).toString()));
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(deltaDate.get(counter).toString()));
                    table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(dates.get(counter).toString()));
                }

                doc.add(table);
                doc.close();

                File file1 = new File("./children_list_" + fileNamesCreator.getFileName() + ".pdf");
                try {
                    Desktop.getDesktop().open(file1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "У вас нет программ, способных открыть файл .pdf расширения!",
                            "Внимание", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}