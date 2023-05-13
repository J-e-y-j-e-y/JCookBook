package document_management;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DocumentManagementSystemTest {
    private final DocumentManagementSystem docSystem = new DocumentManagementSystem();

    private final String RESOURCES = "src" + File.separator + "test" + File.separator
            + "resources" + File.separator + "document_management" + File.separator;
    private final String INVOICE = RESOURCES + "patient.invoice";
    private final String LETTER = RESOURCES + "patient.letter";
    private final String REPORT = RESOURCES + "patient.report";
    private final String UNKNOWN_FILE = RESOURCES + "unknown.txt";
    private final String IMAGE_JPG = RESOURCES + "xray.jpg";
    private final String JOHN_DOE = "John Doe";

    private Document onlyDocument(){
        List<Document> docs = docSystem.contents();
        assertThat(docs, org.hamcrest.Matchers.hasSize(1));
        return docs.get(0);
    }

    private void assertAttrbuteEqual(Document document, String attribute, String expectedValue){
        assertEquals(
                "Document has the wrong value for " + attribute,
                expectedValue,
                document.getAttribute(attribute)
        );
    }

    private void assertTypeIs(String type, Document document){
        assertAttrbuteEqual(document, Attributes.TYPE, type);
    }

    private void assertIsReport(final Document document)
    {
        assertAttrbuteEqual(document, Attributes.PATIENT, JOHN_DOE);
        assertAttrbuteEqual(document, Attributes.BODY,
                "On 5th June 2023 I examined John's teeth.\n" +
                        "No new problems were noted with his teeth.");
        assertTypeIs("REPORT", document);
    }

    @Test
    public void shouldImportFile() throws IOException {
        docSystem.importFile(INVOICE);

        final Document document = onlyDocument();

        assertAttrbuteEqual(document, Attributes.PATH, INVOICE);
    }

    @Test
    public void shouldImportLetterAttributes() throws IOException {
        docSystem.importFile(LETTER);
        //assertThat(docSystem.contents(), hasSize(1));
        Document document = onlyDocument();

        assertAttrbuteEqual(document, Attributes.PATIENT, JOHN_DOE);
        assertAttrbuteEqual(document, Attributes.ADDRESS,
                "123 Cool Street\n" +
                "Moscow\n" +
                "Russia");
        assertAttrbuteEqual(document, Attributes.BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                "with Dr. Javac from 11th June 2023 to 5th June 2023.");
        assertTypeIs("LETTER", document);
    }

    @Test
    public void shouldImportImageAttributes() throws IOException {
        docSystem.importFile(IMAGE_JPG);
        Document image = onlyDocument();

        assertAttrbuteEqual(image, Attributes.WIDTH, "320");
        assertAttrbuteEqual(image, Attributes.HEIGHT, "179");
        assertTypeIs("IMAGE", image);
    }

    @Test
    public void shouldImportReportAttributes() throws Exception
    {
        docSystem.importFile(REPORT);

        assertIsReport(onlyDocument());
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldNotImportMissingFile() throws IOException {
        docSystem.importFile("asd.log");
    }

    @Test(expected = UnknownFileTypeException.class)
    public void shouldNotImportFileOfUnknownType() throws IOException {
        docSystem.importFile(UNKNOWN_FILE);
    }

    @Test
    public void shouldBeAbleToSearchFilesByAttributes() throws Exception
    {
        docSystem.importFile(LETTER);
        docSystem.importFile(REPORT);
        docSystem.importFile(IMAGE_JPG);

        final List<Document> documents = docSystem.search("patient:John,body:teeth");
        assertThat(documents, hasSize(1));

        assertIsReport(documents.get(0));
    }
}
