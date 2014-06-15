package matuszek.typer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import matuszek.typer.dao.MatchDao;
import matuszek.typer.dao.UserDao;
import matuszek.typer.dao.WinnerDao;
import matuszek.typer.dao.model.UserStanding;
import matuszek.typer.dao.model.Winner;
import matuszek.typer.model.StandingEntry;
import matuszek.typer.model.factory.StandingEntryFactory;

@WebServlet("/ranking")
public class StandingsSiteController extends HttpServlet {

	private static final long serialVersionUID = -1132472635899799228L;

	@EJB
	private WinnerDao winnerDao;
	@EJB
	private UserDao userDao;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<StandingEntry> list = new ArrayList<>();
		
		List<UserStanding> userList = userDao.getUsers();
		Winner winner = winnerDao.getWinner();
		
		for (UserStanding u : userList) {			
			list.add(StandingEntryFactory.createStandingEntry(u, winner));			
		}
		
		Collections.sort(list, new Comparator<StandingEntry>() {

			@Override
			public int compare(StandingEntry o1, StandingEntry o2) {
				return o2.getTotalPoints() - o1.getTotalPoints();
			}
			
		});

		request.setAttribute("ranking", list);

		RequestDispatcher dispatcher = request.getRequestDispatcher("ranking.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}

	}

}
