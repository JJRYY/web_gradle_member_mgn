package web_gradle_member_mgn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web_gradle_member_mgn.dto.Member;
import web_gradle_member_mgn.service.MemberService;

@WebServlet("/joinProcess")
public class JoinProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service = new MemberService();
       
    public JoinProcess() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Member joinMember = getMember(request);
//			System.out.println("joinMember : " + joinMember);
			service.addMember(joinMember);
			HttpSession session = request.getSession();
			Member member = (Member) session.getAttribute("member");
//			if (member.getId().equals("admin")) {
//				request.getRequestDispatcher("memberList").forward(request, response);
//			}
			response.sendRedirect("loginForm.jsp");
		} catch (RuntimeException e) {
			e.printStackTrace();
			request.getRequestDispatcher("joinForm.jsp").forward(request, response);
		}
		
	}

	private Member getMember(HttpServletRequest request) {
		Member member = null;
		try {
			String id = request.getParameter("id").trim();
			String passwd = request.getParameter("pass").trim();
			String name = request.getParameter("name").trim();
			int age = Integer.parseInt(request.getParameter("age").trim());
			String gender = request.getParameter("gender").trim();
			String email = request.getParameter("email").trim();
			member = new Member(id, passwd, name, age, gender, email);
		} catch(Exception e) {
			throw new RuntimeException();
		}
		
		return member;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
