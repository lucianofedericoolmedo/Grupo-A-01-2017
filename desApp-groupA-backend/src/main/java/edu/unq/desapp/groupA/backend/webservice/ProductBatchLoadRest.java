package edu.unq.desapp.groupA.backend.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	
//	@Value("${documents.dir}")
    private final String directoryForDocs = "files";

//	@Value("${csv.dir}")
    private final String directoryForCsv = "csv";	

    @POST
    @Path("/product-batch-uploading")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(Attachment file) throws Exception {
		ArchivoDTO archivo = fileUploadService.saveFile(file, directoryForDocs, directoryForCsv);
    	productService.updateProductsViaCSVFile(archivo.getPath() + "/" + archivo.getNombre());
		return responseGenerator.buildResponse(Status.OK);
    }
	
}
