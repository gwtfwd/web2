package egovframework.example.parkType.service;

import java.util.List;

import egovframework.example.park.service.ParkSearchVO;
import egovframework.example.park.service.ParkVO;

public interface ParkTypeService {
	/**
	 * 글 목록 조회
	 * @param parkSearchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @throws Exception
	 */
	List<?> selectParkTypeList(ParkSearchVO parkSearchVO) throws Exception;
	
	/**
	 * 글 총 개수 조회
	 * @param parkSearchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 개수
	 */
	int selectParkTypeListTotCnt(ParkSearchVO parkSearchVO);
	
	/**
	 * 등록
	 * @param vo - 등록할 정보가 담긴 ParkTypeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertParkType(ParkTypeVO vo) throws Exception;
	
	/**
	 * 글 조회
	 * @param vo - 조회할 정보가 담긴 ParkTypeVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	ParkTypeVO selectParkType(ParkTypeVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo - 수정할 정보가 담긴 ParkTypeVO
	 * @return void형
	 * @exception Exception
	 */
	void updateParkType(ParkTypeVO vo) throws Exception;
	
	/**
	 * 삭제
	 * @param vo - 삭제할 정보가 담긴 ParkTypeVO
	 * @return void형
	 * @exception Exception
	 */
	void deleteParkType(ParkTypeVO vo) throws Exception;

}
