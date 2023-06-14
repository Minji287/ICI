package com.festicket.dao;

import java.util.Date;
import java.util.List;

import com.festicket.dto.CSanswerDto;
import com.festicket.dto.CSboardDto;
import com.festicket.dto.EventDto;
import com.festicket.dto.MemberDto;
import com.festicket.dto.QABoardDto;
import com.festicket.dto.ReserveDto;
import com.festicket.dto.ReviewDto;

public interface IDao {
	
	// 검색
	public List<EventDto> getSearchResult(String keyword, int countList, int countPage); // 검색 결과 가져오기
	public int totalSearchResultCount(String keyword); // 검색 결과 개수
	
	// 랭킹
	public List<EventDto> eventListDao(int countList, int countPage); // 모든 행사 리스트
	public int totalEventCountDao(); // 행사의 총 개수
	public List<EventDto> getTopFiveEventsDao(); // 행사 탑5 리스트 
	public List<EventDto> getOngoingEventDao(); // 진행중인 행사 리스트
	
	// 행사 선택
	public EventDto getEventDao(int eventNum); // 행사 하나만 가져오기
	
	// 페스티벌
	public List<EventDto> festivalListDao(int countList, int countPage); // 페스티벌 리스트
// 정렬
	public List<EventDto> festivalOrderByStartRecent(int countList, int countPage); // 페스티벌 리스트 시작일순
	public List<EventDto> festivalOrderByStartLate(int countList, int countPage); // 페스티벌 리스트 시작일순
	public List<EventDto> festivalOrderByEndRecent(int countList, int countPage); // 페스티벌 리스트 종료일순
	public List<EventDto> festivalOrderByEndLate(int countList, int countPage); // 페스티벌 리스트 종료일순
//
	public int totalFestivalCountDao();
	public List<EventDto> top5FestivalListDao(); // 페스티벌 탑5 리스트
	
	// 전시
	public List<EventDto> exhibitionListDao(int countList, int countPage); // 전시 리스트
// 정렬
	public List<EventDto> exhibitionOrderByStartRecent(int countList, int countPage); // 페스티벌 리스트 시작일순
	public List<EventDto> exhibitionOrderByStartLate(int countList, int countPage); // 페스티벌 리스트 시작일순
	public List<EventDto> exhibitionOrderByEndRecent(int countList, int countPage); // 페스티벌 리스트 종료일순
	public List<EventDto> exhibitionOrderByEndLate(int countList, int countPage); // 페스티벌 리스트 종료일순
//
	public int totalExhibitionCountDao();
	public List<EventDto> top5ExhibitionListDao(); // 전시 탑5 리스트
	
	// 리뷰
	public List<ReviewDto> getReviewListDao(int eventNum); // 리뷰 글 리스트 가져오기
	public void reviewWriteDao(String c_userId, String rw_eventNum, String rw_rating, String rw_content); // 리뷰 쓰기
	public int reviewLiker(int reviewIdx, String userId); // 리뷰 좋아요
	public void cancelReviewLiker(int reviewIdx, String userId); // 리뷰 좋아요 취소
	
	// QA
	public List<QABoardDto> getQAListDao(int eventNum); // QA 글 리스트 가져오기
	public QABoardDto getQaDao(int qaNum); // qa 글 하나 가져오기
	public void qaHitDao(int q_idx); // 조회수 증가
	
	// 예약확인
	public ReserveDto getReservationDao(int eventNum, String userId); // 예약 디테일 하나 가져오기
	public int reservationConfirmedDao(String userId, int eventNum, String price, Date today, int ticketCount, Date ticketDate); // 예약 디테일 db에 넣어주기
	public int checkDupRevDao(int eventNum); // 같은 행사, 같은 날에 예약된게 있는지 확인
	
	//회원관리	
	public int joinDao(String userId, String userPassword, String userPhone, String email, String name, Date signupDate); //회원가입
	public int checkIdDao(String userId); //가입하려는 id의 존재여부 체크
	public int checkIdPwDao(String userId, String userPassword);//아이디와 비밀번호의 일치여부 체크
	public MemberDto getMemberInfo(String userId);//아이디로 조회하여 회원 정보 모두 가져오기
	public int modifyMemberDao(String userId, String userPassword, String name, String email);//회원정보 수정
	
	// 고객센터 게시판 기능
	public List<CSboardDto> csListDao(int countList, int countPage); // 게시글 목록 모두 가져오기
	public void csWriteDao(String c_userId, String c_title, String c_content); // 게시글 쓰기
	public CSboardDto csViewDao(String c_idx); // 클릭한 게시글 내용 보기
	public void csModifyDao(String c_idx, String c_userId, String c_title, String c_content); // 게시글 수정
	public void csDeleteDao(String c_idx); // 게시글 삭제
	public void csHitDao(String c_idx); // 조회수 증가
	public int csListTotalCountDao(); // 총 게시글 개수 반환
	
	// 고객센터 검색 기능
	public int totalcsSearch_TitleCount(String keyword); // 타이틀로 CS 검색결과
	public int totalcsSearch_IdCount(String keyword); // 아이디로 CS 검색결과
	public int totalcsSearch_ContentCount(String keyword); // 내용으로 CS 검색결과
	public List<CSboardDto> csSearchTitleDao(String keyword, int countList, int countPage); // 제목으로 검색
	public List<CSboardDto> csSearchContentDao(String keyword, int countList, int countPage); // 내용으로 검색
	public List<CSboardDto> csSearchWriterDao(String keyword, int countList, int countPage); // 아이디로 검색
	
	// 댓글 기능
	public int replyWriteDao(String ca_content, String ca_boardNum); // 댓글 입력
	public void replyCountDao(String ca_boardNum); // 댓글이 달린 원글의 댓글 필드 값 +1
	public List<CSanswerDto> replyListDao(String ca_boardNum); // 해당 원글에 달린 댓글의 리스트 가져오기
	public void replyDeleteDao(String ca_idx); // 댓글 삭제
	public void replyCountMinusDao(String ca_boardNum); // 댓글이 달린 원글의 댓글 필드 값 -1
	public void boardReplyDeleteDao(String ca_boardNum); // 삭제한 게시글 댓글 모두 삭제
	
}
