package web.crawler.servlet;

import web.crawler.model.ResultDto;
import web.crawler.model.SearchDto;
import web.crawler.service.SearchProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "search", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    private final SearchParamsBinder searchParamsBinder = new SearchParamsBinder();
    private final SearchProcessor searchProcessor = new SearchProcessor();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchDto searchDto = searchParamsBinder.bind(req);

        ResultDto resultDto = searchProcessor.search(searchDto);

        req.setAttribute("terms", searchDto.getTerms());
        req.setAttribute("result", resultDto);
        req.getRequestDispatcher("/result.jsp").forward(req, resp);

    }
}