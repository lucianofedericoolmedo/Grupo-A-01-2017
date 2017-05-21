package edu.unq.desapp.groupA.backend.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class FileUtil {

	private static final Logger LOGGER = Logger.getLogger(FileUtil.class);

    public byte[] openFile(final String path) {
        Path p = FileSystems.getDefault().getPath(path);
        byte[] fileData;
        try {
            fileData = Files.readAllBytes(p);
        } catch (IOException e) {
            return null;
        }
        return fileData;
    }

    public String saveToFile(String absolutePath, String fileName, InputStream is) {

        String file = fileName;//getCompleteFileName(absolutePath, fileName);

        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);

            IOUtils.copy(is, outputStream);

        } catch (Exception e) {
            LOGGER.error(MessageFormat.format("Ocurrio un error al intentar guardar el archivo {0}", file), e);
            throw new RuntimeException(e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return file;
    }

    private String getCompleteFileName(String absolutePath, String fileName) {
        return absolutePath + File.separator + fileName;
    }
    
    public boolean existsFile(final String path) {
        Path p = FileSystems.getDefault().getPath(path);
        boolean result = Files.exists(p);
        if (!result) {
            LOGGER.error(MessageFormat.format("No se encontro el archivo  {0}", path));
        }
        return result;

    }
    
    /**
     * crea un archivo vacio en el path dado
     * @param path
     */
    public void createEmptyFile(String path){
    	File f = new File(path);

    	f.getParentFile().mkdirs(); 
    	try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * escribe el content en el archivo del path 
     * @param path
     * @param content
     * @return
     */
	public File writeFile(String path, String content) {
		File file = new File(path);
    	
    	BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(path);
		
			bw = new BufferedWriter(fw);
			bw.write(content);
	
			if (bw != null)
				bw.close();
	
			if (fw != null)
				fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
    
	/**
	 * A partir de un attachment de tipo txt se genera una lista con el string de cada linea
	 * @param attachment
	 * @return
	 */
	public List<String> extraerLineas(Attachment attachment) {
		List<String> lineas = new LinkedList<String>();
        try {
        	InputStream is = attachment.getDataHandler().getInputStream();
            String line;
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( is ) );
            while( (line = bufferedReader.readLine()) != null )
            { 
            	lineas.add(line);
            }  
        } 
        catch( IOException e ){
            System.err.println( "Error: " + e );
        }
        
        return lineas;
	}
	
}
