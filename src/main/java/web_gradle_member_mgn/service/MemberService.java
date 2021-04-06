package web_gradle_member_mgn.service;

import java.sql.Connection;
import java.util.List;

import web_gradle_member_mgn.dao.impl.MemberDaoImpl;
import web_gradle_member_mgn.ds.JndiDS;
import web_gradle_member_mgn.dto.Member;

public class MemberService {
	private Connection con = JndiDS.getConnection();
	private MemberDaoImpl dao;

	public MemberService() {
		dao = MemberDaoImpl.getInstance();
		dao.setCon(con);
	}
	
	public Member searchMember(Member member) {
		return dao.selectMember(member);
	}
	
	public Member loginMember(Member member) {
		return dao.selectMemberById(member);
	}
	
	public List<Member> showMembers() {
		return dao.selectMemberByAll();
	}
	
	public int addMember(Member member) {
		return dao.insertMember(member);
	}
	
	public void modifyMember(Member member) {
		dao.updateMember(member);
	}
	
	public void removeMember(Member member) {
		dao.deleteMember(member);
	}
}
