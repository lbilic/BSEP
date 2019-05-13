package com.kits.project.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kits.project.model.CertificateNode;
import com.kits.project.services.implementations.CertificateService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api/cert")
public class CertificateController {

	@Autowired
	CertificateService certificateService;
	
	@PreAuthorize("hasAuthority('generateCertificate')")
    @RequestMapping(
    		value = "/{alias}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity generateCertificate(@PathVariable String alias, @RequestBody CertificateNode certNode) {
    	return new ResponseEntity<String>(certificateService.generateCert(alias,certNode),HttpStatus.OK);
    }

	@PreAuthorize("hasAuthority('removeCertificate')")
	@RequestMapping(
			value = "/{alias}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity revoke(@PathVariable String alias) {
		return new ResponseEntity<String>(certificateService.revokeCert(alias),HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('getAllCertData')")
    @RequestMapping(
			value = "/all-data",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List> getAllData() {
		return new ResponseEntity(certificateService.getAllData(), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('getCertificate')")
    @RequestMapping(
			value = "/{alias}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List> getCertificate(@PathVariable("alias") String alias) {
		return new ResponseEntity(certificateService.getCertificate(alias), HttpStatus.OK);
	}
    
	@PreAuthorize("hasAuthority('downloadStorage')")
    @RequestMapping(value = "/download/{alias}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody void downloadFiles(@PathVariable("alias") String alias, HttpServletResponse response) throws IOException {
    	
    	// Pre ovoga treba da generises keyStore i trustStore i upises ih u
    	// storage/keystore.jks i storage/truststore.jks
    	
        FileOutputStream out_file = new FileOutputStream("storage/stores.zip");
        ZipOutputStream out = new ZipOutputStream(out_file);

        // Update-uj truststore
        //certificateService.updateTrustStore(alias);

        this.writeToZipFile(String.format("src/main/resources/certs/%s.jks", alias), out);
        //this.writeToZipFile("storage/truststore.jks", out);

        out.close();
        out_file.close();
        
        File file = new File("storage/stores.zip");
        InputStream in = new FileInputStream(file);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
    }

    public void writeToZipFile(String path, ZipOutputStream zipStream)
            throws FileNotFoundException, IOException {

        File aFile = new File(path);
        FileInputStream fis = new FileInputStream(aFile);
        int size = path.split("/").length;
        ZipEntry zipEntry = new ZipEntry(path.split("/")[size-1]);
        zipStream.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipStream.write(bytes, 0, length);
        }

        zipStream.closeEntry();
        fis.close();
    }
}
