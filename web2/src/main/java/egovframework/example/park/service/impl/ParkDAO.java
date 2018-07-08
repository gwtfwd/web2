package egovframework.example.park.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.park.service.ParkSearchVO;
import egovframework.example.park.service.ParkVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("parkDAO")
public class ParkDAO extends EgovAbstractDAO {

	/**
	 * 글 목록 조회
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 목록
	 * @throws Exception
	 */
	public List<?> selectParkList(ParkSearchVO parkSearchVO) throws Exception {
		return list("parkDAO.selectParkList", parkSearchVO);
	}
	
	/**
	 * 글 총 개수 조회
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 총 개수
	 */
	public int selectParkListTotCnt(ParkSearchVO parkSearchVO){
		return (Integer) select("parkDAO.selectParkListTotCnt", parkSearchVO);
	}
	
	/**
	 * 등록
	 * @param vo - 등록할 정보가 담긴 ParkVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertPark(ParkVO vo) throws Exception {
		return (String) insert("parkDAO.insertPark", vo);
	}

	/**
	 * 글 조회
	 * @param vo - 조회할 정보가 담긴 ParkVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public ParkVO selectPark(ParkVO vo) throws Exception {
		return (ParkVO) select("parkDAO.selectPark", vo);
	}


	/**
	 * 수정
	 * @param vo - 수정할 정보가 담긴 ParkVO
	 * @return void형
	 * @exception Exception
	 */
	public void updatePark(ParkVO vo) throws Exception {
		update("parkDAO.updatePark", vo);
	}

	/**
	 * 삭제
	 * @param vo - 삭제할 정보가 담긴 ParkVO
	 * @return void형
	 * @exception Exception
	 */
	public void deletePark(ParkVO vo) throws Exception {
		delete("parkDAO.deletePark", vo);
	}

	
	
	
	
}

