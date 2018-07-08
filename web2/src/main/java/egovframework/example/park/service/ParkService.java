package egovframework.example.park.service;

import java.util.List;


public interface ParkService {

	/**
	 * 글 목록 조회
	 * @param parkSearchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @throws Exception
	 */
	List<?> selectParkList(ParkSearchVO parkSearchVO) throws Exception;
	
	/**
	 * 글 총 개수 조회
	 * @param parkVO - 조회할 정보가 담긴 VO
	 * @return 글 총 개수
	 */
	int selectParkListTotCnt(ParkSearchVO parkSearchVO);
	
	/**
	 * 등록
	 * @param vo - 등록할 정보가 담긴 ParkVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertPark(ParkVO vo) throws Exception;

	/**
	 * 글 조회
	 * @param vo - 조회할 정보가 담긴 ParkVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	ParkVO selectPark(ParkVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo - 수정할 정보가 담긴 ParkVO
	 * @return void형
	 * @exception Exception
	 */
	void updatePark(ParkVO vo) throws Exception;

	/**
	 * 삭제
	 * @param vo - 삭제할 정보가 담긴 ParkVO
	 * @return void형
	 * @exception Exception
	 */
	void deletePark(ParkVO vo) throws Exception;

	
}
