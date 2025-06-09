package com.dashapp.test;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.IOException;
import java.io.InputStream;

public class TestPdfBox {

    public static void main(String[] args) {
        String templatePath = "/com/dashapp/fileCredenziali/template.pdf";
        String outputFileName = "Diary_credenziali_TeoRossi.pdf";  // nome output in cartella corrente

        String username = "teo.rossi@example.com";
        String password = "abcde";

        try {
            creaFileCredenziali(templatePath, outputFileName, username, password);
            System.out.println("PDF creato con successo: " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore nella creazione del PDF");
        }
    }

    public static void creaFileCredenziali(String templateResourcePath, String outputFileName, String username, String password) throws IOException {
        InputStream templateStream = TestPdfBox.class.getResourceAsStream(templateResourcePath);
        if (templateStream == null) {
            throw new IOException("Template PDF non trovato: " + templateResourcePath);
        }

        try (PDDocument pdfDocument = PDDocument.load(templateStream)) {
            PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

            if (acroForm != null) {
                PDField usernameField = acroForm.getField("EmailField");
                PDField passwordField = acroForm.getField("PasswordField");

                if (usernameField != null) {
                    usernameField.setValue(username);
                } else {
                    System.err.println("Campo 'username' non trovato nel PDF");
                }
                if (passwordField != null) {
                    passwordField.setValue(password);
                } else {
                    System.err.println("Campo 'password' non trovato nel PDF");
                }

                acroForm.flatten();  // rende il form non modificabile
            } else {
                System.err.println("Il PDF non contiene un modulo interattivo");
            }

            pdfDocument.save(outputFileName);
        }
    }
}
