package com.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/fileService")
public class FileService {


	@GET
	@Path("/download/zip")
	@Produces("application/zip")
	public Response downloadZippedFile() {

		File file = new File("");

		ResponseBuilder responseBuilder = Response.ok((Object) file);
		responseBuilder.header("Content-Disposition", "attachment; filename=\"file.zip\"");
		return responseBuilder.build();
	}


	@POST
	@Path("/upload/zip")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadZippedFile(
			@FormDataParam("uploadFile") InputStream fileInputStream, 
			@FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition) {

		String fileName = null;
		String uploadFilePath = null;

		try {
			fileName = fileFormDataContentDisposition.getFileName();
			uploadFilePath = writeToFileServer(fileInputStream, fileName);
		} 
		catch(IOException ioe){
			ioe.printStackTrace();
		}

		return Response.ok("File uploaded successfully at " + uploadFilePath).build();
	}


	private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {


		String qualifiedUploadFilePath = fileName;

		try (OutputStream outputStream = new FileOutputStream(new File(qualifiedUploadFilePath))){

			int read = 0;
			byte[] bytes = new byte[20480];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.flush();

		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return qualifiedUploadFilePath;
	}
}