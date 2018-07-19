package edu.stanford.nlp.sempre;


import edu.stanford.nlp.sempre.roboy.utils.NLULoggerController;

import java.io.IOException;
import java.io.PrintWriter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.Socket;

import edu.stanford.nlp.sempre.Master.*;


public class SocketConnectionHandler implements Runnable {

	private Socket clientSocket;
	private Session session;
	private Master master;

	public SocketConnectionHandler(Socket clientSocket, Session session, Master master) {
		this.clientSocket = clientSocket;
		this.session = session;
		this.master = master;
	}

  public void run() {
  	try {
  	BufferedReader input = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
        try {
          String line;
          while (clientSocket.isConnected()) {
            line = input.readLine();
            if (line != null) {
              NLULoggerController.logs("%s", line);
              int indent = NLULoggerController.getIndLevel();
              try {
                Response res = master.processQuery(session, line);
                System.out.println(res.getAll());
                output.println(res.getAll());
              } catch (Throwable t) {
                while (NLULoggerController.getIndLevel() > indent)
                  NLULoggerController.end_track();
                t.printStackTrace();
              }
            }
          }
        } catch (IOException e) {
          e.printStackTrace();
          throw new RuntimeException(e);
        }
    }
    catch (Exception e) {
    	e.printStackTrace();
    }
  }



}