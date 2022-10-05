package br.com.alura.ecommerce;

import org.eclipse.jetty.server.HttpChannel;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HttpEcommerceService {

    public static void main(String[] args) throws Exception {
        var server = new Server(8080);

        //antes de startar,configuremos para quando chamar a requisicao, setHandle através de um contexto  que lida com as reuisicoes
        var context = new ServletContextHandler();
        context.setContextPath("/");//seta padrão do caminho
        context.addServlet(new ServletHolder(new NewOrderServlet()), "/new");

        server.setHandler(context);
        server.start();
        server.join();//aguarda o servidor terminar para eu terminar a minha aplicação e joga Exception

    }
}

