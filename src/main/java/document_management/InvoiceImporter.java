package document_management;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class InvoiceImporter implements Importer{
    private final String NAME_PREFIX = "Dear ";
    private final String AMOUNT_PREFIX = "Amount: ";

    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, Attributes.PATIENT);
        textFile.addLineSuffix(AMOUNT_PREFIX, Attributes.AMOUNT);

        Map<String, String> attributes = textFile.getAttributes();
        attributes.put(Attributes.TYPE, "INVOICE");
        return new Document(attributes);
    }
}
