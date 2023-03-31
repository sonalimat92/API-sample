package com.demo.bluejay.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.hibernate.boot.model.source.internal.hbm.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bluejay.dao.MoviesDao;
import com.demo.bluejay.dao.RecordDao;
import com.demo.bluejay.dao.SettlementDao;
import com.demo.bluejay.dao.TransactionDao;
import com.demo.bluejay.entities.MovieDetail;
import com.demo.bluejay.entities.Movies;
import com.demo.bluejay.entities.Products;
import com.demo.bluejay.entities.Record;
import com.demo.bluejay.entities.SettlementFile;
import com.demo.bluejay.entities.TransactionFile;
import com.demo.bluejay.helper.HelperSuite;
import com.demo.bluejay.services.ProductService;
import com.demo.bluejay.services.ReconciliationService;

@RestController
public class MyController {
	
	@Autowired
	private HelperSuite helperSuite;

	@Autowired
	private ProductService productServ;
	
	
	@Autowired
    private RecordDao recordRepository;
	
	@Autowired
    private TransactionDao transactionRepository;
	
     @Autowired
	 private MoviesDao movieRepository;

	

	@Autowired
    private SettlementDao settlementepository;
	
	@GetMapping("/home")
	public String Home() {
		return "Test First API Creation";
	}
	
	@GetMapping("/")
	public String HomePage() {
		return "\n"
				+ "Welocome to Bluejay\n"
				+ "Sonali’s API Collection for test\n"
				+ "\n"
				+ "/upload Post \n"
				+ "/products. Get\n"
				+ "/products/{productId}  Get\n"
				+ "/downlaod-csv. Get\n"
				+ "/reconcile Post";
	}
	
	//get the courses
	@GetMapping("/products")
	public List<Products> getProducts(){
		return productServ.getProducts();
	}
	
	@GetMapping("/products/{productId}")
	public Products getProduct(@PathVariable String productId){
		return productServ.getProduct(Long.parseLong(productId));
	}
	
	//post the courses
	@PostMapping("/products")
		public Products addProducts(@RequestBody Products product){
			
			return productServ.addProducts(product);
		}
	//////movie api's ////////////////
	
	@GetMapping("/movie")
    public List<Movies> getAllMovies() {
        return movieRepository.findAll();
    }
	
	

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movies> getMovieById(@PathVariable(value = "id") Long id) {
        Optional<Movies> optionalMovie = movieRepository.findById(id);
       
            return ResponseEntity.ok().body(optionalMovie.get());
        
    }

    @PostMapping("/movie")
    public Movies createMovie(@RequestBody Movies movie) {
        return movieRepository.save(movie);
    }

    

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable(value = "id") Long id) {
        Optional<Movies> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            movieRepository.deleteById(id);
            return ResponseEntity.ok().body("Movie with id " + id + " deleted successfully");
        } else {
        	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("movie not found");
        }
    }

 
    
    
    
	    ////////////Recon app////////////
	   @PostMapping("/upload")
	    public ResponseEntity<String> handleCsvUpload(@RequestParam("file") MultipartFile file)  {
	        try {
	            if (file == null) {
	                throw new IllegalArgumentException("File cannot be null");
	            }

	            // read CSV file into a list of Records
				
	        	InputStreamReader isr = new InputStreamReader(file.getInputStream());
	             CSVReader csvReader = new CSVReader(isr);

	            String[] headerRow = csvReader.readNext();

	            if (!Arrays.equals(headerRow, new String[] { "Id", "Name", "Email"})) {
	                throw new IllegalArgumentException("Invalid CSV file format");
	            }

	            List<Record> records = new ArrayList<>();

	            String[] row;

	            while ((row = csvReader.readNext()) != null) {
	                Record record = new Record();
	                record.setId(Long.parseLong(row[0]));
	                record.setEmail(row[1]);
	                record.setName(row[2]);
	 
	                records.add(record);
	                
	            }
	            recordRepository.saveAll(records);

	            return ResponseEntity.ok("CSV file uploaded successfully");

	             } catch (Exception e) {
	                 throw new RuntimeException("Failed to upload CSV file", e);
	             }
	         }
		
	   @PostMapping("/transactionupload")
	    public ResponseEntity<String> transactionUpload(@RequestParam("file") MultipartFile file)  {
	        try {
	            if (file == null) {
		               return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("File cannot be null");
	            }
	            System.out.println(file.getOriginalFilename());
	            if(!file.getContentType().equals("text/csv")) {
		               return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid file format");
	            }
	            
	            helperSuite.uploadFile(file);

	            // read CSV file into a list of Records
				
	        	InputStreamReader isr = new InputStreamReader(file.getInputStream());
	             CSVReader csvReader = new CSVReader(isr);

	            String[] headerRow = csvReader.readNext();

	            if (!Arrays.equals(headerRow, new String[] { "Id","Amount", "Network","Mode","TransactionDate","transactionId"})) {
	               return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid CSV file format");
	            	//throw new IllegalArgumentException("Invalid CSV file format");
	                
	                
	            }

	            List<TransactionFile> transactionFiles = new ArrayList<>();

	            String[] row;

	            while ((row = csvReader.readNext()) != null) {
	                TransactionFile transactionFile = new TransactionFile();
	               // transactionFile.setId(Long.parseLong(row[0]));
	                transactionFile.setAmount(BigDecimal.valueOf(Double.valueOf(row[1])));
	                transactionFile.setNetwork(row[2]);
	                transactionFile.setMode(row[3]);
	                transactionFile.setTransactionDate(row[4]);
	                transactionFile.setTransactionId(Long.parseLong(row[5]));

	 
	                transactionFiles.add(transactionFile);
	                
	            }
	            
	            transactionRepository.saveAll(transactionFiles);

	            return ResponseEntity.ok("CSV file uploaded successfully");
	            
	            } catch (Exception e) {
	                 throw new RuntimeException("Failed to upload CSV file", e);
	             }
	         }
	   @GetMapping("/download-csv")
	   public ResponseEntity<Resource> downloadCsv(@RequestParam("filename") String fileName) throws IOException {
	       File csvFile = new File("./src/main/resources/static/" + fileName); // replace with your file path
	       if (!csvFile.exists()) {
	           throw new FileNotFoundException("CSV file not found");
	       }
	       Path csvFilePath = csvFile.toPath();
	       ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(csvFilePath));
	       return ResponseEntity.ok()
	               .contentLength(csvFile.length())
	               .contentType(MediaType.parseMediaType("text/csv"))
	               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + csvFile.getName() + "\"")
	               .body(resource);
	   }
	   @PostMapping("/settlementupload")
	    public ResponseEntity<String> settlementUpload(@RequestParam("file") MultipartFile file)  {
	        try {
	            if (file == null) {
	                throw new IllegalArgumentException("File cannot be null");
	            }
                
	            System.out.println(file.getOriginalFilename());
	            if(!file.getContentType().equals("text/csv")) {
		               return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid file format");
	            }
	            
	            helperSuite.uploadFile(file);
	            // read CSV file into a list of Records
				
	        	InputStreamReader isr = new InputStreamReader(file.getInputStream());
	             CSVReader csvReader = new CSVReader(isr);

	            String[] headerRow = csvReader.readNext();

	            if (!Arrays.equals(headerRow, new String[] { "Id","Amount", "Network","Mode","TransactionDate","transactionId","settlementId"})) {
	                throw new IllegalArgumentException("Invalid CSV file format");
	            }

	            List<SettlementFile> settlementFiles = new ArrayList<>();

	            String[] row;

	            while ((row = csvReader.readNext()) != null) {
	            	SettlementFile settlementFile = new SettlementFile();
	               // transactionFile.setId(Long.parseLong(row[0]));
	            	settlementFile.setNetamount(BigDecimal.valueOf(Double.valueOf(row[1])));
	            	settlementFile.setNetwork(row[2]);
	            	settlementFile.setMode(row[3]);
	            	settlementFile.setSettlementfiledate(row[4]);
	            	settlementFile.setTransactionId(Long.parseLong(row[5]));

	 
	            	settlementFiles.add(settlementFile);
	                
	            }
	            
	            settlementepository.saveAll(settlementFiles);

	            return ResponseEntity.ok("CSV file uploaded successfully");

	             } catch (Exception e) {
	                 throw new RuntimeException("Failed to upload CSV file", e);
	             }
	         }
	   @Autowired
	    private ReconciliationService reconciliationService;

	    @PostMapping("/reconcile")
	    public ResponseEntity<String> reconcile()
	                                         {
	        try {
	           

	            // Process the uploaded files for reconciliation
	            reconciliationService.reconcile();

	            return ResponseEntity.ok("Reconciliation completed successfully!");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reconciling files: " + e.getMessage());
	        }
	    }
		//update the courses
			//	@PutMapping("/products")
			//	public ResponseEntity<HttpStatus> updateProducts(@RequestBody Products course){
			//	try {
				//	this.productServ.updateCourse(course);
      				//return new ResponseEntity<>(HttpStatus.OK);
			//	}
			//	catch (Exception e) {
					// TODO: handle exception
				//	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				//}
			//	}
	
}
