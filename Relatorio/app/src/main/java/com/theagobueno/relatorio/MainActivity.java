package com.theagobueno.relatorio;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void criarpdf(View view) {
        EditText txt = (EditText) findViewById(R.id.txtTest);
        Document doc = new Document();
        //String outPath = Environment.getExternalStorageDirectory()+"/funcionario.pdf";

            try {
                PdfWriter.getInstance(doc, new FileOutputStream("C:\\funcionario.pdf"));
                doc.open();
                doc.add(new Paragraph(txt.getText().toString()));
                doc.close();
                Toast.makeText(this, "gerou pdf", Toast.LENGTH_LONG).show();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


    }
}
