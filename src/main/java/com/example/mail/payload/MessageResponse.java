package com.example.mail.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс для вывода сообщения в конторллерах
 * Используется зависимость Lombok
 */
@Data
@AllArgsConstructor
public class MessageResponse {
    private String message;
}
