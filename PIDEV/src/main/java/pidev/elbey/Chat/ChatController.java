package pidev.elbey.Chat;


import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import pidev.elbey.Entities.Message;


import java.util.ArrayList;
import java.util.List;


@RestController

public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final List<String> users = new ArrayList<>();

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    @MessageMapping("/chat")
    public void sendMessage(@Payload Message messageDto, SimpMessageHeaderAccessor headerAccessor) {
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        messageDto.setUsername(username);
        messagingTemplate.convertAndSend("/topic/chat", messageDto);
    }

    @EventListener

    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        // Extract the username from the WebSocket session headers
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = accessor.getUser().getName();
        // Add the username to the list of connected users
        users.add(username);
        // Broadcast the updated list of users to all clients
        messagingTemplate.convertAndSend("/topic/userList", users);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        // Extract the session ID and username from the WebSocket session headers
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        String username = accessor.getUser().getName();
        // Remove the user from the list of connected users
        users.remove(username);
        // Broadcast the updated list of users to all clients
        messagingTemplate.convertAndSend("/topic/userList", users);
        // Broadcast a notification message to all clients
        ChatMessage message = new ChatMessage();
        //message.setType(ChatMessage.MessageType.LEAVE);
        message.setSender(username);
        messagingTemplate.convertAndSend("/topic/chat/" + sessionId, message);
    }



    @MessageMapping("/join")
    public void joinChat(@Payload String username, SimpMessageHeaderAccessor headerAccessor) {
        // Add the username to the WebSocket session attributes
        headerAccessor.getSessionAttributes().put("username", username);
        // Broadcast the updated list of users to all clients
        messagingTemplate.convertAndSend("/topic/userList", users);
    }
}

