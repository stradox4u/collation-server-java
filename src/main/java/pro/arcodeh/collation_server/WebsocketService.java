package pro.arcodeh.collation_server;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import pro.arcodeh.collation_server.result.ResultUpdateDto;

@Service
public class WebsocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public WebsocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendResultsUpdate(ResultUpdateDto resultUpdateDto) {
        this.messagingTemplate.convertAndSend("/topic/update-result", resultUpdateDto);
    }
}
