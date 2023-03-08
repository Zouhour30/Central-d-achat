package com.example.testspring.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor

    public class FileResponse {
        private String fileName;
        private String message;
    @Id
    private Long id;

    public FileResponse(String fileName, String message){
            this.fileName = fileName;
            this.message = message;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


}

