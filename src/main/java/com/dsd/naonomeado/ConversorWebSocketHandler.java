package com.dsd.naonomeado;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ConversorWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessoes = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessoes.add(session);
        System.out.println("Cliente conectado: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Mensagem de " + session.getId() + ": " + payload);

        String[] partes = payload.split(":");
        String escala = partes[0];
        double valor = Double.parseDouble(partes[1]);
        String resposta;

        if (escala.equals("C")) {
            if (valor < -273.15) {
                resposta = "[Cliente " + session.getId() + "] Erro: Abaixo do zero absoluto.";
            } else {
                double f = (valor * 9.0 / 5.0) + 32.0;
                resposta = String.format("[Cliente %s] %.2f°C = %.2f°F", session.getId(), valor, f);
            }
        } else {
            if (valor < -459.67) {
                resposta = "[Cliente " + session.getId() + "] Erro: Abaixo do zero absoluto.";
            } else {
                double c = (valor - 32.0) * 5.0 / 9.0;
                resposta = String.format("[Cliente %s] %.2f°F = %.2f°C", session.getId(), valor, c);
            }
        }

        for (WebSocketSession s : sessoes) {
            if (s.isOpen()) {
                s.sendMessage(new TextMessage(resposta));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessoes.remove(session);
        System.out.println("Cliente desconectado: " + session.getId());
    }
}