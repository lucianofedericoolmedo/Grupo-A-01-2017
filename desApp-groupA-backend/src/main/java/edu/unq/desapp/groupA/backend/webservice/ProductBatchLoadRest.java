package edu.unq.desapp.groupA.backend.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.dto.ArchivoDTO;
import edu.unq.desapp.groupA.backend.service.FileUploadService;
import edu.unq.desapp.groupA.backend.service.ProductService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;

@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/upload")
public class ProductBatchLoadRest {

	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ResponseGenerator responseGenerator;

	/*
	@POST
	@Path("/upload")
	public Response loadProductsViaBatchFile(@RequestParam("file") MultipartFile file){
        String name = "test11";
	    if (!file.isEmpty()) {
	        try {
	            byte[] bytes = file.getBytes();
	            BufferedOutputStream stream = 
	                    new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
	            stream.write(bytes);
	            stream.close();
	            return responseGenerator.responseOK("You successfully uploaded " + name + " into " + name + "-uploaded !");
	        } catch (Exception e) {
	            return responseGenerator.responseBadRequest("You failed to upload " + name + " => " + e.getMessage());
	        }
	    } else {
	        return responseGenerator.responseBadRequest("You failed to upload " + name + " because the file was empty.");
	    }
	}
	*/
	
	
//	@Value("${documents.dir}")
    private final String directoryForDocs = "/home/files";	

//	@Value("${csv.dir}")
    private final String directoryForCsv = "csv";	

    @POST
    @Path("/product-batch-uploading")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(Attachment file) throws Exception { //Attachment attachment
		ArchivoDTO archivo = fileUploadService.saveFile(file, directoryForDocs, directoryForCsv);
    	productService.updateProductsViaCSVFile(archivo.getNombre());
		return responseGenerator.buildSuccessResponse("coso");
    }
    
	/*
    @POST
    @Path("/product-batch-uploading")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@RequestParam("file") MultipartFile file) throws Exception { //Attachment attachment
//        try {
    		//System.out.println("------------------ archivo " + files);
            byte[] bytes = file.getBytes();
            InputStream is = file.getInputStream();
            /*
            is.read(bytes);
            is.close();
            /*
            BufferedOutputStream stream = 
                    new BufferedOutputStream(new FileOutputStream(new File("csv" + "-uploaded")));
            stream.write(bytes);
            stream.close();
            */
    		/*
        	ArchivoDTO archivo = fileUploadService.saveFile(is, directoryForDocs, directoryForCsv);
        	productService.updateProductsViaCSVFile(archivo.getNombre());
            return responseGenerator.buildSuccessResponse(archivo);
//        } catch (Exception e) {
//            return responseGenerator.responseBadRequest(e.getMessage());
//        }
    		return responseGenerator.buildSuccessResponse("coso");
    }
    		 */
	
}
