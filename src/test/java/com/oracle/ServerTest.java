package com.oracle;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    @Test
    public void whenHelloThenGreeting() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(String.format("Hello oracle%sexit",System.getProperty("line.separator")).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        new Server(socket);
        assertThat(out.toString().replace("\r","").replace("\n",""), is("Hello, dear friend, I'm a oracle.exit"));
    }

    @Test
    public void whenAskThenAntwort() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(String.format("first question%sexit",System.getProperty("line.separator")).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        new Server(socket);
        assertThat(out.toString().replace("\r","").replace("\n",""), is("hieiiiiiiiieeeeeeeeeeeeeeeeeeeeee" +
                "eeeeeeeeeeeeeeeeeeeeeriiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiisssssssssssthhhhhhhhhhhhhhheeeeeeeeeeeeeeeeeeeeeeeaaaaa" +
                "aaaaaaaannnnnnnnnnnnnnnnnnnnnssssssssssswwwwwwwwwwweerrrrrrrrfirstexit"));
    }
}
