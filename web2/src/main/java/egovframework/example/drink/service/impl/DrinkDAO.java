package egovframework.example.drink.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.drink.service.DrinkDefaultVO;
import egovframework.example.drink.service.DrinkVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("drinkDAO")

public class DrinkDAO extends EgovAbstractDAO {

	/**
	 * 글 목록 조회
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 목록
	 * @throws Exception
	 */
	public List<?> selectDrinkList(DrinkDefaultVO findVO) throws Exception {
		return list("drinkDAO.selectDrinkList", findVO);
	}
	
	/**
	 * 글 총 개수 조회
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 총 개수
	 */
	public int selectDrinkListTotCnt(DrinkDefaultVO findVO){
		return (Integer) select("drinkDAO.selectDrinkListTotCnt", findVO);
	}
	
	/**
	 * 등록
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertDrink(DrinkVO vo) throws Exception {
		return (String) insert("drinkDAO.insertDrink", vo);
	}

	/**
	 * 수정
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateDrink(DrinkVO vo) throws Exception {
		update("drinkDAO.updateDrink", vo);
	}

	/**
	 * 삭제
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteDrink(DrinkVO vo) throws Exception {
		delete("drinkDAO.deleteDrink", vo);
	}

	/**
	 * 글 조회
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public DrinkVO selectDrink(DrinkVO vo) throws Exception {
		return (DrinkVO) select("drinkDAO.selectDrink", vo);
	}
	
	
	
}

