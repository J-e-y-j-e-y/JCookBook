package document_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentManagementSystem {

    private final List<Document> docs = new ArrayList<>();

    private final Map<String, Importer> importExtensions = new HashMap<>();

    public DocumentManagementSystem() {
        importExtensions.put("jpg", new ImageImporter());
        importExtensions.put("report", new ReportImporter());
        importExtensions.put("letter", new LetterImporter());
    }

    public void importFile(String filaname){

    }

    public List<Document> contents(){
        return docs;
    }
}
