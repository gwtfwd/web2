package egovframework.example.parkType.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.park.service.ParkSearchVO;
import egovframework.example.park.service.ParkVO;
import egovframework.example.parkType.service.ParkTypeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("parkTypeDAO")
public class ParkTypeDAO extends EgovAbstractDAO {

	/**
	 * 글 목록 조회
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 목록
	 * @throws Exception
	 */
	public List<?> selectParkTypeList(ParkSearchVO parkSearchVO) throws Exception {
		return list("parkTypeDAO.selectParkTypeList", parkSearchVO);
	}
	
	/**
	 * 글 총 개수 조회
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 총 개수
	 */
	public int selectParkTypeListTotCnt(ParkSearchVO parkSearchVO){
		return (Integer) select("parkTypeDAO.selectParkTypeListTotCnt", parkSearchVO);
	}
	
	/**
	 * 등록
	 * @param vo - 등록할 정보가 담긴 ParkTypeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertParkType(ParkTypeVO vo) throws Exception {
		return (String) insert("parkTypeDAO.insertParkType", vo);
	}
	

	/**
	 * 글 조회
	 * @param vo - 조회할 정보가 담긴 ParkTypeVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public ParkTypeVO selectParkType(ParkTypeVO vo) throws Exception {
		return (ParkTypeVO) select("parkTypeDAO.selectParkType", vo);
	}
	
	/**
	 * 수정
	 * @param vo - 수정할 정보가 담긴 ParkTypeVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateParkType(ParkTypeVO vo) throws Exception {
		update("parkTypeDAO.updateParkType", vo);
	}
	
	/**
	 * 삭제
	 * @param vo - 삭제할 정보가 담긴 ParkTypeVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteParkType(ParkTypeVO vo) throws Exception {
		delete("parkTypeDAO.deleteParkType", vo);
	}
	
	
	
	
	
}


