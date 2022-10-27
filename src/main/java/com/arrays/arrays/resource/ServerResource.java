package com.arrays.arrays.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arrays.arrays.enumeration.Status;
import com.arrays.arrays.model.Response;
import com.arrays.arrays.model.Server;
import com.arrays.arrays.service.ServerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

	private static final HttpStatus CREATED = null;
	private static final String IMAGE_PNG_VALUE = "image/png";
	private final ServerService serverService;
	
	
	@GetMapping("/list")
	public ResponseEntity<Response> getServers() throws InterruptedException{
		TimeUnit.SECONDS.sleep(3);
		return ResponseEntity.ok(
				Response.builder()
				        .timeStamp(now())
				        .data(Map.of("servers", serverService.list(30)))
				        .message("Servers retrieved")
				        .status(HttpStatus.OK)
				        .statusCode(HttpStatus.OK.value())
				        .build()
				);
	}
	
	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException{
		Server server = ServerService.ping(ipAddress);
		return ResponseEntity.ok(
				Response.builder()
				        .timeStamp(now())
				        .data(Map.of("server", server))
				        .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
				        .status(HttpStatus.OK)
				        .statusCode(HttpStatus.OK.value())
				        .build()
				);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server){
		return ResponseEntity.ok(
				Response.builder()
				        .timeStamp(now())
				        .data(Map.of("server", serverService.create(server)))
				        .message("server created")
				        .status(HttpStatus.CREATED)
				        .statusCode(CREATED.value())
				        .build()
				);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable("id") Long id){
		
		return ResponseEntity.ok(
				Response.builder()
				        .timeStamp(now())
				        .data(Map.of("server", serverService.get(id)))
				        .message("server retrieved")
				        .status(HttpStatus.OK)
				        .statusCode(HttpStatus.OK.value())
				        .build()
				);
	}
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id){
		
		return ResponseEntity.ok(
				Response.builder()
				        .timeStamp(now())
				        .data(Map.of("deleted", serverService.delete(id)))
				        .message("server deleted")
				        .status(HttpStatus.OK)
				        .statusCode(HttpStatus.OK.value())
				        .build()
				);
	}
	
	@GetMapping(path ="/image/{fileName}", produces = IMAGE_PNG_VALUE)
	public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException{
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images/" + fileName));
	}

	private LocalDateTime now() {
		// TODO Auto-generated method stub
		return null;
	}


}
