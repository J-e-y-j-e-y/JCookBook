package document_management;

import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DocumentManagementSystem {

    private final List<Document> docs = new ArrayList<>();

    private final Map<String, Importer> importExtensions = new HashMap<>();

    public DocumentManagementSystem() {
        importExtensions.put("jpg", new ImageImporter());
        importExtensions.put("report", new ReportImporter());
        importExtensions.put("letter", new LetterImporter());
        importExtensions.put("invoice", new InvoiceImporter());
    }

    public void importFile(String path) throws IOException {
        File file = new File(path);

        if(!file.exists())
            throw new FileNotFoundException(path);

        int separator = path.lastIndexOf('.');
        if(separator != -1){
            String extension = path.substring(separator + 1);
            Importer importer = importExtensions.get(extension);
            if(importer == null)
                throw new UnknownFileTypeException("Cannot import " + path);

            Document doc = importer.importFile(file);
            docs.add(doc);
        }else throw new UnknownFileTypeException("No extension found for file " + path);
    }

    public List<Document> contents(){
        return docs;
    }

    public List<Document> search(String query){
        System.out.println(Query.parse(query).getClauses());
        for(Document document : docs){
            System.out.println(document);
            System.out.println(document.getAttributes());
        }
        return docs.stream()
                .filter(Query.parse(query))
                .collect(Collectors.toList());
    }
}
