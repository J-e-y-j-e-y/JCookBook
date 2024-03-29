package document_management;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class LetterImporter implements Importer{
    private final String NAME_PREFIX = "Dear ";

    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, Attributes.PATIENT);

        final int lineNumber = textFile.addLines(2, String::isEmpty, Attributes.ADDRESS);
        textFile.addLines(lineNumber + 1, (line) -> line.startsWith("regards"), Attributes.BODY);

        Map<String, String> attributes = textFile.getAttributes();
        attributes.put(Attributes.TYPE, "LETTER");
        return new Document(attributes);
    }
}
