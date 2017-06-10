package edu.unq.desapp.groupA.backend.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.dto.ArchivoDTO;
import edu.unq.desapp.groupA.backend.utils.FileUtil;

@Service
public class FileUploadService {

	private SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy");
	
	@Autowired
    private FileUtil fileUtil;
 
    public ArchivoDTO saveFile(Attachment attachment, String directory, String subDirectory) throws Exception {
    	InputStream inputStream = attachment.getDataHandler().getInputStream();
        return saveFile(inputStream, directory, subDirectory);
    }

    public ArchivoDTO saveFile(InputStream is, String directory, String subDirectory) throws Exception {
        String todayDate = simpleDate.format(new Date());
        String fileName = "batch_" + todayDate + ".csv";
        fileUtil.saveToFile(directory + "/" + subDirectory, fileName, is);
        ArchivoDTO archivo = new ArchivoDTO(null, fileName, directory + "/" + subDirectory);
        return archivo;
    }

}
