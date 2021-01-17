package com.example.chatSocket.controllers;

import com.example.chatSocket.models.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ApiController {
    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping("/upload")
    public void file(@RequestParam MultipartFile file,@RequestParam String user) throws IOException {
        String UPLOAD_DIR = "C:\\Users\\DELL\\IdeaProjects\\chatSocket\\src\\main\\resources\\static\\files";
        Path path = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
        Files.write(path, file.getBytes());
        this.template.convertAndSend("/topic/greetings",new Greeting("<a href='C:\\Users\\DELL\\IdeaProjects\\chatSocket\\src\\main\\resources\\static\\files\\"+file.getOriginalFilename()+"'>"+user+" : "+file.getOriginalFilename()+"</a>"));
    }
}
