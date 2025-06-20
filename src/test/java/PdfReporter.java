

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;

public class PdfReporter {

    public static void generateReport(List<String> screenshots) throws IOException {
        PDDocument doc = new PDDocument();
        for (String imgPath : screenshots) {
            PDPage page = new PDPage();
            doc.addPage(page);
            try (PDPageContentStream content = new PDPageContentStream(doc, page)) {
                PDImageXObject image = PDImageXObject.createFromFile(imgPath, doc);
                content.drawImage(image, 50, 300, 500, 300); // Позиция и размер
                content.beginText();
                content.setFont(new PDType1Font(HELVETICA_BOLD), 12);
                content.newLineAtOffset(50, 250);
                content.showText("Step: " + Paths.get(imgPath).getFileName());
                content.endText();
            }
        }
        doc.save("test-report.pdf");
        doc.close();
    }

}
