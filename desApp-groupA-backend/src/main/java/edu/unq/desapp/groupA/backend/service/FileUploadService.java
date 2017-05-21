package edu.unq.desapp.groupA.backend.service;

import java.io.InputStream;
import java.util.UUID;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.dto.ArchivoDTO;
import edu.unq.desapp.groupA.backend.utils.FileUtil;

@Service
public class FileUploadService {

	@Autowired
    private FileUtil fileUtil;
 
    public ArchivoDTO saveFile(Attachment attachment, String directory, String subDirectory) throws Exception {
        InputStream is = attachment.getDataHandler().getInputStream();
        String fileName = UUID.randomUUID() + ".csv" ;
        fileUtil.saveToFile(directory + "/" + subDirectory, fileName, is);
        ArchivoDTO archivo = new ArchivoDTO(null, fileName, subDirectory);
        return archivo;
    }

    public ArchivoDTO saveFile(InputStream inputStream, String directory, String subDirectory) throws Exception {
        String fileName = UUID.randomUUID() + ".csv" ;
        fileUtil.saveToFile(directory + "/" + subDirectory, fileName, inputStream);
        ArchivoDTO archivo = new ArchivoDTO(null, fileName, subDirectory);
        return archivo;
    }

}
