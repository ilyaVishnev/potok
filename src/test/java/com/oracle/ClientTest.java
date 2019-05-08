package com.oracle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayInputStream in = new ByteArrayInputStream("Hello, dear friend, I'm a oracle".getBytes());
    private final ByteArrayInputStream inTwo = new ByteArrayInputStream("sorry, no answer".getBytes());
    private final PrintStream outOrigin = System.out;

    @Before
    public void setStream() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void setOrigin() {
        System.setOut(outOrigin);
    }

    @Test
    public void whenHelloThenGreeting() throws IOException {
        System.setIn(in);
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        new Client(socket);
        assertEquals("Hello, dear friend, I'm a oracle" + System.getProperty("line.separator"), out.toString());
    }

    @Test
    public void whenAskThenAntwort() throws IOException {
        System.setIn(inTwo);
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        new Client(socket);
        assertEquals("sorry, no answer" + System.getProperty("line.separator"), out.toString());
    }
}
